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

import org.newinstance.gucoach.utility.DateHelper;

import java.util.Date;

/**
 * The immutable data of a player.
 *
 * @author mwalter
 */
public final class Player {
    /** Primary key. */
    private Long id;
    /** First name. */
    private String firstName;
    /** Last name. */
    private String lastName;
    /** Country. */
    private Country country;
    /** Height. */
    private Integer height;
    /** Personality. */
    private String personality;
    /** Birthday in format dd.mm. */
    private String birthday;
    /** Strong foot. */
    private StrongFoot strongFoot;
    /** Date the player was imported into the database. */
    private Date importDate;

    public String getBirthday() {
        return birthday;
    }

    public Country getCountry() {
        return country;
    }

    public String getFirstName() {
        return firstName;
    }

    public Integer getHeight() {
        return height;
    }

    public Long getId() {
        return id;
    }

    public Date getImportDate() {
        return importDate;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPersonality() {
        return personality;
    }

    public StrongFoot getStrongFoot() {
        return strongFoot;
    }

    public void setBirthday(final String birthday) {
        this.birthday = birthday;
    }

    public void setCountry(final Country country) {
        this.country = country;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public void setHeight(final Integer height) {
        this.height = height;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setImportDate(final Date importDate) {
        this.importDate = importDate;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public void setPersonality(final String personality) {
        this.personality = personality;
    }

    public void setStrongFoot(final StrongFoot strongFoot) {
        this.strongFoot = strongFoot;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        return ((Player) obj).getId().equals(id);
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 31 + id.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Player [");
        builder.append("id=").append(id).append(", ");
        builder.append("firstName=").append(firstName).append(", ");
        builder.append("lastName=").append(lastName).append(", ");
        builder.append("country=").append(country).append(", ");
        builder.append("height=").append(height).append(", ");
        builder.append("personality=").append(personality).append(", ");
        builder.append("birthday=").append(birthday).append(", ");
        builder.append("strongFoot=").append(strongFoot.getDescription()).append(", ");
        builder.append("importDate=").append(DateHelper.formatDate(importDate));
        builder.append("]");
        return builder.toString();
    }
}
