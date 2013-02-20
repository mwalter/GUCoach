/*
 * GUCoach - your personal coach for Goalunited (tm).
 * Licenced under General Public Licence v3 (GPLv3)
 * newInstance.org, 2012
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

package org.newinstance.gucoach.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.newinstance.gucoach.model.Fixture;
import org.newinstance.gucoach.model.Team;
import org.newinstance.gucoach.service.FixtureService;
import org.newinstance.gucoach.service.TeamService;
import org.newinstance.gucoach.utility.StandingsComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Calculates and provides all standings data.
 * TODO improve code
 *
 * @author mwalter
 */
@Component
public final class StandingsContentProvider {

    /** The team service. */
    @Autowired
    private TeamService teamService;

    /** The fixture service. */
    @Autowired
    private FixtureService fixtureService;

    private static final int POINTS_MATCH_DRAWN = 1;
    private static final int POINTS_MATCH_LOST = 0;
    private static final int POINTS_MATCH_WON = 3;

    /**
     * Returns all standings data from the database.
     *
     * @return the standings data
     */
    public ObservableList<StandingsDataRow> getStandingsData() {
        // make sure to initialise tables
        final List<StandingsDataRow> standingsDataRows = new ArrayList<StandingsDataRow>();
        final List<Team> teams = teamService.findAllTeams();
        final List<Fixture> fixtures = fixtureService.findAllFixtures();

        // collect all data for every team in fixture list
        for (final Team team : teams) {
            standingsDataRows.add(calculateStandingsDataForTeam(team, fixtures));
        }

        // sort standings
        Collections.sort(standingsDataRows, new StandingsComparator<StandingsDataRow>());
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
