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
 * A fixture of two teams.
 *
 * @author mwalter
 */
public final class Fixture {

    /** Primary key. */
    private Long id;
    /** Primary key of home team. */
    private Long homeTeamId;
    /** Primary key of away team. */
    private Long awayTeamId;
    /** Fixture result. */
    private String matchResult;
    /** Day of the match. */
    private Date matchDay;

    public Long getHomeTeamId() {
        return homeTeamId;
    }

    public Long getId() {
        return id;
    }

    public Date getMatchDay() {
        return matchDay;
    }

    public String getMatchResult() {
        return matchResult;
    }

    public Long getAwayTeamId() {
        return awayTeamId;
    }

    public void setHomeTeamId(final Long homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setMatchDay(final Date matchDay) {
        this.matchDay = matchDay;
    }

    public void setMatchResult(final String matchResult) {
        this.matchResult = matchResult;
    }

    public void setAwayTeamId(final Long awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Fixture [");
        builder.append("id=").append(id).append(", ");
        builder.append("homeTeamId=").append(homeTeamId).append(", ");
        builder.append("awayTeamId=").append(awayTeamId).append(", ");
        builder.append("matchResult=").append(matchResult).append(", ");
        builder.append("matchDay=").append(matchDay);
        builder.append("]");
        return builder.toString();
    }
}
