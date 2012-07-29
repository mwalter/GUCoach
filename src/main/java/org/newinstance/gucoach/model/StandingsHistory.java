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

package org.newinstance.gucoach.model;

import java.util.Date;

/**
 * A standings record of a team at a certain date. Used to retain the development of each team in the league.
 *
 * @author mwalter
 */
public final class StandingsHistory {

    /** Primary key. */
    private Long id;
    /** The date this standings record is valid from. */
    private Date matchDay;
    /** Number of matches won. */
    private Integer matchesWon;
    /** Number of matches drawn. */
    private Integer matchesDrawn;
    /** Number of matches lost. */
    private Integer matchesLost;
    /** Number of goals scored. */
    private Integer goalsFor;
    /** Number of goals received. */
    private Integer goalsAgainst;
    /** Current position in standings. */
    private Integer position;
    /** The team's primary key. */
    private Long teamId;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Date getMatchDay() {
        return matchDay;
    }

    public void setMatchDay(final Date matchDay) {
        this.matchDay = matchDay;
    }

    public Integer getMatchesPlayed() {
        return matchesWon + matchesDrawn + matchesLost;
    }

    public Integer getMatchesWon() {
        return matchesWon;
    }

    public void setMatchesWon(final Integer matchesWon) {
        this.matchesWon = matchesWon;
    }

    public Integer getMatchesDrawn() {
        return matchesDrawn;
    }

    public void setMatchesDrawn(final Integer matchesDrawn) {
        this.matchesDrawn = matchesDrawn;
    }

    public Integer getMatchesLost() {
        return matchesLost;
    }

    public void setMatchesLost(final Integer matchesLost) {
        this.matchesLost = matchesLost;
    }

    public Integer getGoalsDiff() {
        return goalsFor - goalsAgainst;
    }

    public Integer getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(final Integer goalsFor) {
        this.goalsFor = goalsFor;
    }

    public Integer getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(final Integer goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public Integer getPoints() {
        return matchesWon * 3 + matchesDrawn;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(final Integer position) {
        this.position = position;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(final Long teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("StandingsHistory [");
        builder.append("id=").append(id).append(", ");
        builder.append("teamId=").append(teamId).append(", ");
        builder.append("position=").append(position).append(", ");
        builder.append("matchDay=").append(matchDay).append(", ");
        builder.append("matchesWon=").append(matchesWon).append(", ");
        builder.append("matchesDrawn=").append(matchesDrawn).append(", ");
        builder.append("matchesLost=").append(matchesLost).append(", ");
        builder.append("goalsFor=").append(goalsFor).append(", ");
        builder.append("goalsAgainst=").append(goalsAgainst);
        builder.append("]");
        return builder.toString();
    }
}
