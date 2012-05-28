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
    /** Team name. */
    private String name;
    /** The starting position of the team in the league. */ 
    private Integer startingPosition;
    /** Strength. */
    private Float strength;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getStartingPosition() {
        return startingPosition;
    }

    public Float getStrength() {
        return strength;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setStartingPosition(final Integer startingPosition) {
        this.startingPosition = startingPosition;
    }

    public void setStrength(final Float strength) {
        this.strength = strength;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Team [");
        builder.append("id=").append(id).append(", ");
        builder.append("name=").append(name).append(", ");
        builder.append("startingPosition=").append(startingPosition).append(", ");
        builder.append("strength=").append(strength);
        builder.append("]");
        return builder.toString();
    }
}
