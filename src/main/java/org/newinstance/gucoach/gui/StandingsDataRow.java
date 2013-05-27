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

package org.newinstance.gucoach.gui;

/**
 * Represents a row in the standings (league) table.
 *
 * @author mwalter
 */
public class StandingsDataRow {

    private int goalsFor;

    private int goalsAgainst;

    private int matchesPlayed;

    private int matchesWon;

    private int matchesDrawn;

    private int matchesLost;

    private int points;

    private int rank;

    private String teamName;

    public StandingsDataRow(final String teamName, final int matchesPlayed, final int matchesWon, final int matchesDrawn, final int matchesLost, final int goalsFor, final int goalsAgainst, final int points) {
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

    public int getRank() {
        return rank;
    }

    public void setRank(final int rank) {
        this.rank = rank;
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
        builder.append("points=").append(points).append(", ");
        builder.append("rank=").append(rank);
        builder.append("]");
        return builder.toString();
    }
}