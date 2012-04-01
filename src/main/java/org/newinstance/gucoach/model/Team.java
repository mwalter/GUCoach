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

/**
 * A team playing in the league.
 *
 * @author mwalter
 */
public final class Team {

    /** Primary key. */
    private Long id;
    /** Position in league. */
    private Integer position;
    /** Team name. */
    private String name;
    /** Strength. */
    private Float strength;
    /** Matches won so far. */
    private Integer matchesWon;
    /** Matches drawn so far. */
    private Integer matchesDrawn;
    /** Matches lost so far. */
    private Integer matchesLost;
    /** Points achieved. */
    private Integer points;
    /** Goals scored. */
    private Integer goalsFor;
    /** Goals collected. */
    private Integer goalsAgainst;

    public Integer getGoalsAgainst() {
        return goalsAgainst;
    }

    public Integer getGoalsFor() {
        return goalsFor;
    }

    public Long getId() {
        return id;
    }

    public Integer getMatchesDrawn() {
        return matchesDrawn;
    }

    public Integer getMatchesLost() {
        return matchesLost;
    }

    public Integer getMatchesWon() {
        return matchesWon;
    }

    public String getName() {
        return name;
    }

    public Integer getPoints() {
        return points;
    }

    public Integer getPosition() {
        return position;
    }

    public Float getStrength() {
        return strength;
    }

    public void setGoalsAgainst(final Integer goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public void setGoalsFor(final Integer goalsFor) {
        this.goalsFor = goalsFor;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setMatchesDrawn(final Integer matchesDrawn) {
        this.matchesDrawn = matchesDrawn;
    }

    public void setMatchesLost(final Integer matchesLost) {
        this.matchesLost = matchesLost;
    }

    public void setMatchesWon(final Integer matchesWon) {
        this.matchesWon = matchesWon;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setPoints(final Integer points) {
        this.points = points;
    }

    public void setPosition(final Integer position) {
        this.position = position;
    }

    public void setStrength(final Float strength) {
        this.strength = strength;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Team [");
        builder.append("id=").append(id).append(", ");
        builder.append("position=").append(position).append(", ");
        builder.append("name=").append(name).append(", ");
        builder.append("strength=").append(strength).append(", ");
        builder.append("matchesWon=").append(matchesWon).append(", ");
        builder.append("matchesDrawn=").append(matchesDrawn).append(", ");
        builder.append("matchesLost=").append(matchesLost).append(", ");
        builder.append("points=").append(points).append(", ");
        builder.append("goalsFor=").append(goalsFor).append(", ");
        builder.append("goalsAgainst=").append(goalsAgainst);
        builder.append("]");
        return builder.toString();
    }
}
