/*
 * GUCoach - your personal coach for Goalunited (tm).
 * Licenced under General Public Licence v3 (GPLv3)
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

package org.newinstance.gucoach.entity;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.newinstance.gucoach.utility.DateHelper;

/**
 * The immutable data of a player.
 *
 * @author mwalter
 */
@Entity
public final class Player {

    /** The primary key is provided by the game. */
    @Id
    private Long id;

    /** First name. */
    @Column(name = "first_name")
    private String firstName;

    /** Last name. */
    @Column(name = "last_name")
    private String lastName;

    /** Country. */
    @Enumerated(EnumType.STRING)
    private Country country;

    /** Height. */
    private Integer height;

    /** Personality. */
    private String personality;

    /** Birthday in format dd.mm. */
    private String birthday;

    /** Strong foot. */
    @Column(name = "strong_foot")
    @Enumerated(EnumType.STRING)
    private StrongFoot strongFoot;

    /** Date the player was imported into the database. */
    @Temporal(TemporalType.DATE)
    @Column(name = "import_date")
    private Date importDate;

    /** Player statistics. */
    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL)
    private PlayerStats playerStats;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(final String birthday) {
        this.birthday = birthday;
    }

    public Country getCountry() {
        return country;
    }

    public String getCountryProperty() {
        return new SimpleStringProperty(getCountry().name()).get();
    }

    public void setCountry(final Country country) {
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getFullName() {
        return lastName + ", " + firstName;
    }

    public String getFullNameProperty() {
        return new SimpleStringProperty(lastName + ", " + firstName).get();
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(final Integer height) {
        this.height = height;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(final Date importDate) {
        this.importDate = importDate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getPersonality() {
        return personality;
    }

    public void setPersonality(final String personality) {
        this.personality = personality;
    }

    public PlayerStats getPlayerStats() {
        return playerStats;
    }

    public void setPlayerStats(final PlayerStats playerStats) {
        this.playerStats = playerStats;
    }

    public StrongFoot getStrongFoot() {
        return strongFoot;
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
        if (playerStats != null) {
            builder.append(", ").append("playerStats=").append(playerStats.toString());
        }
        builder.append("]");
        return builder.toString();
    }

    // -- convenience methods for statistic attributes (because ${playerStats.number} doesn't work

    public Integer getNumber() {
        return getPlayerStats().getNumber();
    }

    public Integer getNumberProperty() {
        return new SimpleIntegerProperty(getPlayerStats().getNumber()).get();
    }

    public Integer getAge() {
        return getPlayerStats().getAge();
    }

    public Integer getAgeProperty() {
        return new SimpleIntegerProperty(getPlayerStats().getAge()).get();
    }

    public Float getAverageStrength() {
        return getPlayerStats().getAverageStrength();
    }

    public Float getAverageStrengthProperty() {
        return new SimpleFloatProperty(getPlayerStats().getAverageStrength()).get();
    }

    public String getPosition() {
        return getPlayerStats().getPosition().name();
    }

    public String getPositionProperty() {
        return new SimpleStringProperty(getPlayerStats().getPosition().name()).get();
    }

    public Integer getExperience() {
        return getPlayerStats().getExperience();
    }

    public Integer getExperienceProperty() {
        return new SimpleIntegerProperty(getPlayerStats().getExperience()).get();
    }

    public Integer getSkillGoalkeeping() {
        return getPlayerStats().getSkillGoalkeeping();
    }

    public Integer getSkillGoalkeepingProperty() {
        return new SimpleIntegerProperty(getPlayerStats().getSkillGoalkeeping()).get();
    }

    public Integer getSkillTackling() {
        return getPlayerStats().getSkillTackling();
    }

    public Integer getSkillTacklingProperty() {
        return new SimpleIntegerProperty(getPlayerStats().getSkillTackling()).get();
    }

    public Integer getSkillPlaymaking() {
        return getPlayerStats().getSkillPlaymaking();
    }

    public Integer getSkillPlaymakingProperty() {
        return new SimpleIntegerProperty(getPlayerStats().getSkillPlaymaking()).get();
    }

    public Integer getSkillPassing() {
        return getPlayerStats().getSkillPassing();
    }

    public Integer getSkillPassingProperty() {
        return new SimpleIntegerProperty(getPlayerStats().getSkillPassing()).get();
    }

    public Integer getSkillScoring() {
        return getPlayerStats().getSkillScoring();
    }

    public Integer getSkillScoringProperty() {
        return new SimpleIntegerProperty(getPlayerStats().getSkillScoring()).get();
    }

    public Integer getForm() {
        return getPlayerStats().getForm();
    }

    public Integer getFormProperty() {
        return new SimpleIntegerProperty(getPlayerStats().getForm()).get();
    }

    public Integer getEnergy() {
        return getPlayerStats().getEnergy();
    }

    public Integer getEnergyProperty() {
        return new SimpleIntegerProperty(getPlayerStats().getEnergy()).get();
    }

    public Integer getEndurance() {
        return getPlayerStats().getEndurance();
    }

    public Integer getEnduranceProperty() {
        return new SimpleIntegerProperty(getPlayerStats().getEndurance()).get();
    }

}
