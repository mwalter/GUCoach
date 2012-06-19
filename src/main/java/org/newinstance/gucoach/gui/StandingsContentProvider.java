/*
 * GUCoach - your personal coach for Goalunited (tm).
 * Copyright (C) 2012 newInstance.org
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
import org.newinstance.gucoach.service.DatabaseService;
import org.newinstance.gucoach.service.DatabaseServiceImpl;
import org.newinstance.gucoach.utility.StandingsComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Calculates and provides all standings data.
 * TODO improve code
 *
 * @author mwalter
 */
public final class StandingsContentProvider {

    private static final int POINTS_MATCH_DRAWN = 1;
    private static final int POINTS_MATCH_LOST = 0;
    private static final int POINTS_MATCH_WON = 3;
    /** The database service. */
    private static DatabaseService databaseService = new DatabaseServiceImpl();

    private StandingsContentProvider() {
        // hide constructor
    }

    /**
     * Returns all standings data from the database.
     *
     * @return the standings data
     */
    public static ObservableList<StandingsDataRow> getStandingsData() {
        // make sure to initialise tables
        // TODO should not be called here
        databaseService.createTables();
        final List<StandingsDataRow> standingsDataRows = new ArrayList<StandingsDataRow>();
        final List<Team> teams = databaseService.findAllTeams();
        final List<Fixture> fixtures = databaseService.findAllFixtures();

        // collect all data for every team in fixture list
        for (final Team team : teams) {
            standingsDataRows.add(calculateStandingsDataForTeam(team, fixtures));
        }

        // sort standings
        Collections.sort(standingsDataRows, new StandingsComparator());
        return FXCollections.observableList(standingsDataRows);
    }

    private static StandingsDataRow calculateStandingsDataForTeam(final Team team, final List<Fixture> fixtures) {
        int matchesPlayed = 0;
        int matchesWon = 0;
        int matchesDrawn = 0;
        int matchesLost = 0;
        int goalsFor = 0;
        int goalsAgainst = 0;
        int points = 0;

        for (final Fixture fixture : fixtures) {
            // is this team part of this fixture?
            if (fixture.getHomeTeamId().equals(team.getId()) || fixture.getAwayTeamId().equals(team.getId())) {
                matchesPlayed++;
                final String[] results = fixture.getMatchResult().split(":");
                final int goalsHomeTeam = Integer.parseInt(results[0]);
                final int goalsAwayTeam = Integer.parseInt(results[1]);
                // is this the home or away team?
                if (fixture.getHomeTeamId().equals(team.getId())) {
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
    private static int calculatePoints(final int goalsTeamOne, final int goalsTeamTwo) {
        if (goalsTeamOne > goalsTeamTwo) {
            return POINTS_MATCH_WON;
        } else if (goalsTeamTwo > goalsTeamOne) {
            return POINTS_MATCH_LOST;
        } else {
            return POINTS_MATCH_DRAWN;
        }
    }

    /**
     * Represents a row in the standings table.
     *
     * @author mwalter
     */
    public static class StandingsDataRow {

        private int goalsFor;
        private int goalsAgainst;
        private int matchesPlayed;
        private int matchesWon;
        private int matchesDrawn;
        private int matchesLost;
        private int points;
        private String teamName;

        public StandingsDataRow(final String teamName, final int matchesPlayed, final int matchesWon, final int matchesDrawn, final int matchesLost,
                                final int goalsFor, final int goalsAgainst, final int points) {
            this.goalsFor = goalsFor;
            this.goalsAgainst = goalsAgainst;
            this.matchesPlayed = matchesPlayed;
            this.matchesWon = matchesWon;
            this.matchesDrawn = matchesDrawn;
            this.matchesLost = matchesLost;
            this.points = points;
            this.teamName = teamName;
        }

        public int getMatchesPlayed() {
            return matchesPlayed;
        }

        public int getMatchesWon() {
            return matchesWon;
        }

        public int getMatchesDrawn() {
            return matchesDrawn;
        }

        public int getMatchesLost() {
            return matchesLost;
        }

        public int getGoalsFor() {
            return goalsFor;
        }

        public int getGoalsAgainst() {
            return goalsAgainst;
        }

        public int getGoalsDiff() {
            return goalsFor - goalsAgainst;
        }

        public int getPoints() {
            return points;
        }

        public String getTeamName() {
            return teamName;
        }

        @Override
        public String toString() {
            final StringBuilder builder = new StringBuilder();
            builder.append("StandingsDataRow [");
            builder.append("teamName=").append(teamName).append(", ");
            builder.append("matchesPlayed=").append(matchesPlayed).append(", ");
            builder.append("matchesWon=").append(matchesWon).append(", ");
            builder.append("matchesDrawn=").append(matchesDrawn).append(", ");
            builder.append("matchesLost=").append(matchesLost).append(", ");
            builder.append("goalsFor=").append(goalsFor).append(", ");
            builder.append("goalsAgainst=").append(goalsAgainst).append(", ");
            builder.append("goalsDiff=").append(getGoalsDiff()).append(", ");
            builder.append("points=").append(points);
            builder.append("]");
            return builder.toString();
        }
    }
}
