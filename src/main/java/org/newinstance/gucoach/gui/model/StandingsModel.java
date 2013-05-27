/*
 * GUCoach - your personal coach for Goalunited (tm).
 * Licensed under General Public License v3 (GPLv3)
 * newInstance.org, 2012-2013
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.newinstance.gucoach.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newinstance.gucoach.entity.Fixture;
import org.newinstance.gucoach.entity.Team;
import org.newinstance.gucoach.gui.StandingsDataRow;
import org.newinstance.gucoach.utility.StandingsComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Calculates and provides all standings data.
 *
 * @author mwalter
 */
@Component
public final class StandingsModel {

    /** The log4j logger. */
    private static final Logger LOGGER = LogManager.getLogger(StandingsModel.class.getName());

    /** Points for a draw. */
    private static final int POINTS_MATCH_DRAWN = 1;

    /** Points for losing a match. */
    private static final int POINTS_MATCH_LOST = 0;

    /** Points for winning a match. */
    private static final int POINTS_MATCH_WON = 3;

    /** The league model. */
    @Autowired
    private LeagueModel leagueModel;

    /**
     * Calculates standings data, creates data rows and returns them.
     *
     * @return the list of standings data rows
     */
    public ObservableList<StandingsDataRow> getStandingsData() {
        LOGGER.info("Retrieving standings data from model.");
        final List<StandingsDataRow> standingsDataRows = new ArrayList<StandingsDataRow>();
        final List<Team> teams = leagueModel.getTeams();
        final List<Fixture> fixtures = leagueModel.getFixtures();

        // collect all data for every team in fixture list
        for (final Team team : teams) {
            standingsDataRows.add(calculateStandingsDataForTeam(team, fixtures));
        }

        // sort standings
        Collections.sort(standingsDataRows, new StandingsComparator<StandingsDataRow>());
        // if there are no matches played yet use starting rank of team
        if (standingsDataRows.get(0).getMatchesPlayed() == 0) {
            for (final Team team : teams) {
                for (final StandingsDataRow standingsDataRow : standingsDataRows) {
                    if (team.getName().equals(standingsDataRow.getTeamName())) {
                        standingsDataRow.setRank(team.getStartPos());
                    }
                }
            }
        } else {
            // set rank
            int rank = 1;
            for (final StandingsDataRow standingsDataRow : standingsDataRows) {
                standingsDataRow.setRank(rank);
                rank++;
            }
        }
        return FXCollections.observableList(standingsDataRows);
    }

    private StandingsDataRow calculateStandingsDataForTeam(final Team team, final List<Fixture> fixtures) {
        int matchesPlayed = 0;
        int matchesWon = 0;
        int matchesDrawn = 0;
        int matchesLost = 0;
        int goalsFor = 0;
        int goalsAgainst = 0;
        int points = 0;

        for (final Fixture fixture : fixtures) {
            // is this team part of this fixture?
            if (fixture.getHomeTeam().equals(team) || fixture.getAwayTeam().equals(team)) {
                matchesPlayed++;
                final String[] results = fixture.getMatchResult().split(":");
                final int goalsHomeTeam = Integer.parseInt(results[0]);
                final int goalsAwayTeam = Integer.parseInt(results[1]);
                // is this the home or away team?
                if (fixture.getHomeTeam().equals(team)) {
                    goalsFor += goalsHomeTeam;
                    goalsAgainst += goalsAwayTeam;
                    switch (calculatePoints(goalsHomeTeam, goalsAwayTeam)) {
                        case POINTS_MATCH_WON:
                            matchesWon++;
                            points += POINTS_MATCH_WON;
                            break;
                        case POINTS_MATCH_DRAWN:
                            matchesDrawn++;
                            points += POINTS_MATCH_DRAWN;
                            break;
                        case POINTS_MATCH_LOST:
                            matchesLost++;
                    }
                } else {
                    goalsFor += goalsAwayTeam;
                    goalsAgainst += goalsHomeTeam;
                    switch (calculatePoints(goalsAwayTeam, goalsHomeTeam)) {
                        case POINTS_MATCH_WON:
                            matchesWon++;
                            points += POINTS_MATCH_WON;
                            break;
                        case POINTS_MATCH_DRAWN:
                            matchesDrawn++;
                            points += POINTS_MATCH_DRAWN;
                            break;
                        case POINTS_MATCH_LOST:
                            matchesLost++;
                    }
                }
            }
        }

        return new StandingsDataRow(team.getName(), matchesPlayed, matchesWon, matchesDrawn, matchesLost, goalsFor, goalsAgainst, points);
    }

    /**
     * Calculates the points achieved by a team based on the match result.
     *
     * @param goalsTeamOne the goals of the team in the match to calculate the points for
     * @param goalsTeamTwo the goals of the opponent
     * @return the points achieved in the match
     */
    private int calculatePoints(final int goalsTeamOne, final int goalsTeamTwo) {
        if (goalsTeamOne > goalsTeamTwo) {
            return POINTS_MATCH_WON;
        } else if (goalsTeamTwo > goalsTeamOne) {
            return POINTS_MATCH_LOST;
        } else {
            return POINTS_MATCH_DRAWN;
        }
    }

}
