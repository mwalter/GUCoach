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

import org.newinstance.gucoach.model.Country;
import org.newinstance.gucoach.model.Position;
import org.newinstance.gucoach.model.StrongFoot;
import org.newinstance.gucoach.utility.DateHelper;

import java.util.Date;

/**
 * Represents a row in the table view containing all data of a player.
 *
 * @author mwalter
 */
public final class PlayerDataRow {

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
    /** Preferred foot. */
    private StrongFoot strongFoot;
    /** Number in team. */
    private Integer number;
    /** Training - not used. */
    private String training;
    /** Strength. */
    private Float strength;
    /** Position in line-up. */
    private Position position;
    /** Form. */
    private Integer form;
    /** Energy. */
    private Integer energy;
    /** Endurance. */
    private Integer endurance;
    /** Experience. */
    private Integer experience;
    /** Goalkeeping skill. */
    private Integer skillGoalkeeping;
    /** Tackling skill. */
    private Integer skillTackling;
    /** Playmaking skill. */
    private Integer skillPlaymaking;
    /** Passing skill. */
    private Integer skillPassing;
    /** Scoring skill. */
    private Integer skillScoring;
    /** Talent. */
    private String talent;
    /** Talent level. */
    private Integer talentLevel;
    /** Age. */
    private Integer age;
    /** Salary. */
    private Integer salary;
    /** Number of assignments. */
    private Integer assignments;
    /** Goals scored in current season. */
    private Integer goalsSeason;
    /** Goals scored in total. */
    private Integer goalsTotal;
    /** Market value. */
    private Integer marketValue;
    /** Yellow cards seen in current season. */
    private Integer yellowCardsSeason;
    /** Yellow cards seen in total. */
    private Integer yellowCardsTotal;
    /** Red cards seen in current season. */
    private Integer redCardsSeason;
    /** Red cards seen in total. */
    private Integer redCardsTotal;
    /** Date the player was imported into the database. */
    private Date importDate;

    public Integer getAge() {
        return age;
    }

    public Integer getAssignments() {
        return assignments;
    }

    public String getBirthday() {
        return birthday;
    }

    public Country getCountry() {
        return country;
    }

    public Integer getEndurance() {
        return endurance;
    }

    public Integer getEnergy() {
        return energy;
    }

    public Integer getExperience() {
        return experience;
    }

    public String getFirstName() {
        return firstName;
    }

    public Integer getForm() {
        return form;
    }

    public Integer getGoalsSeason() {
        return goalsSeason;
    }

    public Integer getGoalsTotal() {
        return goalsTotal;
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

    public Integer getMarketValue() {
        return marketValue;
    }

    public Integer getNumber() {
        return number;
    }

    public String getPersonality() {
        return personality;
    }

    public Position getPosition() {
        return position;
    }

    public Integer getRedCardsSeason() {
        return redCardsSeason;
    }

    public Integer getRedCardsTotal() {
        return redCardsTotal;
    }

    public Integer getSalary() {
        return salary;
    }

    public Integer getSkillGoalkeeping() {
        return skillGoalkeeping;
    }

    public Integer getSkillPassing() {
        return skillPassing;
    }

    public Integer getSkillPlaymaking() {
        return skillPlaymaking;
    }

    public Integer getSkillScoring() {
        return skillScoring;
    }

    public Integer getSkillTackling() {
        return skillTackling;
    }

    public Float getStrength() {
        return strength;
    }

    public StrongFoot getStrongFoot() {
        return strongFoot;
    }

    public String getTalent() {
        return talent;
    }

    public Integer getTalentLevel() {
        return talentLevel;
    }

    public String getTraining() {
        return training;
    }

    public Integer getYellowCardsSeason() {
        return yellowCardsSeason;
    }

    public Integer getYellowCardsTotal() {
        return yellowCardsTotal;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    public void setAssignments(final Integer assignments) {
        this.assignments = assignments;
    }

    public void setBirthday(final String birthday) {
        this.birthday = birthday;
    }

    public void setCountry(final Country country) {
        this.country = country;
    }

    public void setEndurance(final Integer endurance) {
        this.endurance = endurance;
    }

    public void setEnergy(final Integer energy) {
        this.energy = energy;
    }

    public void setExperience(final Integer experience) {
        this.experience = experience;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public void setForm(final Integer form) {
        this.form = form;
    }

    public void setGoalsSeason(final Integer goalsSeason) {
        this.goalsSeason = goalsSeason;
    }

    public void setGoalsTotal(final Integer goalsTotal) {
        this.goalsTotal = goalsTotal;
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

    public void setMarketValue(final Integer marketValue) {
        this.marketValue = marketValue;
    }

    public void setNumber(final Integer number) {
        this.number = number;
    }

    public void setPersonality(final String personality) {
        this.personality = personality;
    }

    public void setPosition(final Position position) {
        this.position = position;
    }

    public void setRedCardsSeason(final Integer redCardsSeason) {
        this.redCardsSeason = redCardsSeason;
    }

    public void setRedCardsTotal(final Integer redCardsTotal) {
        this.redCardsTotal = redCardsTotal;
    }

    public void setSalary(final Integer salary) {
        this.salary = salary;
    }

    public void setSkillGoalkeeping(final Integer skillGoalkeeping) {
        this.skillGoalkeeping = skillGoalkeeping;
    }

    public void setSkillPassing(final Integer skillPassing) {
        this.skillPassing = skillPassing;
    }

    public void setSkillPlaymaking(final Integer skillPlaymaking) {
        this.skillPlaymaking = skillPlaymaking;
    }

    public void setSkillScoring(final Integer skillScoring) {
        this.skillScoring = skillScoring;
    }

    public void setSkillTackling(final Integer skillTackling) {
        this.skillTackling = skillTackling;
    }

    public void setStrength(final Float strength) {
        this.strength = strength;
    }

    public void setStrongFoot(final StrongFoot strongFoot) {
        this.strongFoot = strongFoot;
    }

    public void setTalent(final String talent) {
        this.talent = talent;
    }

    public void setTalentLevel(final Integer talentLevel) {
        this.talentLevel = talentLevel;
    }

    public void setTraining(final String training) {
        this.training = training;
    }

    public void setYellowCardsSeason(final Integer yellowCardsSeason) {
        this.yellowCardsSeason = yellowCardsSeason;
    }

    public void setYellowCardsTotal(final Integer yellowCardsTotal) {
        this.yellowCardsTotal = yellowCardsTotal;
    }

    public String getFullName() {
        return lastName + ", " + firstName;
    }

    public String getGoalsSeasonAndTotal() {
        return getGoalsSeason().toString() + " (" + getGoalsTotal().toString() + ")";
    }

    public String getRedCardsSeasonAndTotal() {
        return getRedCardsSeason().toString() + " (" + getRedCardsTotal().toString() + ")";
    }

    public String getYellowCardsSeasonAndTotal() {
        return getYellowCardsSeason().toString() + " (" + getYellowCardsTotal().toString() + ")";
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("PlayerDataRow [");
        builder.append("id=").append(id).append(", ");
        builder.append("firstName=").append(firstName).append(", ");
        builder.append("lastName=").append(lastName).append(", ");
        builder.append("country=").append(country).append(", ");
        builder.append("height=").append(height).append(", ");
        builder.append("personality=").append(personality).append(", ");
        builder.append("birthday=").append(birthday).append(", ");
        if (strongFoot != null) {
            builder.append("strongFoot=").append(strongFoot.name()).append(", ");
        } else {
            builder.append("strongFoot=null").append(", ");
        }
        builder.append("number=").append(number).append(", ");
        builder.append("training=").append(training).append(", ");
        builder.append("strength=").append(strength).append(", ");
        if (position != null) {
            builder.append("position=").append(position.name()).append(", ");
        } else {
            builder.append("position=null").append(", ");
        }
        builder.append("form=").append(form).append(", ");
        builder.append("energy=").append(energy).append(", ");
        builder.append("endurance=").append(endurance).append(", ");
        builder.append("experience=").append(experience).append(", ");
        builder.append("skillGoalkeeping=").append(skillGoalkeeping).append(", ");
        builder.append("skillTackling=").append(skillTackling).append(", ");
        builder.append("skillPlaymaking=").append(skillPlaymaking).append(", ");
        builder.append("skillPassing=").append(skillPassing).append(", ");
        builder.append("skillScoring=").append(skillScoring).append(", ");
        builder.append("talent=").append(talent).append(", ");
        builder.append("talentLevel=").append(talentLevel).append(", ");
        builder.append("age=").append(age).append(", ");
        builder.append("salary=").append(salary).append(", ");
        builder.append("assignments=").append(assignments).append(", ");
        builder.append("goalsSeason=").append(goalsSeason).append(", ");
        builder.append("goalsTotal=").append(goalsTotal).append(", ");
        builder.append("marketValue=").append(marketValue).append(", ");
        builder.append("yellowCardsSeason=").append(yellowCardsSeason).append(", ");
        builder.append("yellowCardsTotal=").append(yellowCardsTotal).append(", ");
        builder.append("redCardsSeason=").append(redCardsSeason).append(", ");
        builder.append("redCardsTotal=").append(redCardsTotal).append(", ");
        builder.append("importDate=").append(DateHelper.formatDate(importDate));
        builder.append("]");
        return builder.toString();
    }

}
