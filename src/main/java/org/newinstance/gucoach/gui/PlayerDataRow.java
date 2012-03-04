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

import org.newinstance.gucoach.model.Country;
import org.newinstance.gucoach.model.Position;
import org.newinstance.gucoach.model.StrongFoot;

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
    /** Average strength. */
    private Float averageStrength;
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

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(final Country country) {
        this.country = country;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(final Integer height) {
        this.height = height;
    }

    public String getPersonality() {
        return personality;
    }

    public void setPersonality(final String personality) {
        this.personality = personality;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(final String birthday) {
        this.birthday = birthday;
    }

    public StrongFoot getStrongFoot() {
        return strongFoot;
    }

    public void setStrongFoot(final StrongFoot strongFoot) {
        this.strongFoot = strongFoot;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(final Date importDate) {
        this.importDate = importDate;
    }

    public Integer getAssignments() {
        return assignments;
    }

    public void setAssignments(final Integer assignments) {
        this.assignments = assignments;
    }

    public Integer getGoalsSeason() {
        return goalsSeason;
    }

    public void setGoalsSeason(final Integer goalsSeason) {
        this.goalsSeason = goalsSeason;
    }

    public Integer getGoalsTotal() {
        return goalsTotal;
    }

    public void setGoalsTotal(final Integer goalsTotal) {
        this.goalsTotal = goalsTotal;
    }

    public Integer getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(final Integer marketValue) {
        this.marketValue = marketValue;
    }

    public Integer getYellowCardsSeason() {
        return yellowCardsSeason;
    }

    public void setYellowCardsSeason(final Integer yellowCardsSeason) {
        this.yellowCardsSeason = yellowCardsSeason;
    }

    public Integer getYellowCardsTotal() {
        return yellowCardsTotal;
    }

    public void setYellowCardsTotal(final Integer yellowCardsTotal) {
        this.yellowCardsTotal = yellowCardsTotal;
    }

    public Integer getRedCardsSeason() {
        return redCardsSeason;
    }

    public void setRedCardsSeason(final Integer redCardsSeason) {
        this.redCardsSeason = redCardsSeason;
    }

    public Integer getRedCardsTotal() {
        return redCardsTotal;
    }

    public void setRedCardsTotal(final Integer redCardsTotal) {
        this.redCardsTotal = redCardsTotal;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(final Integer number) {
        this.number = number;
    }

    public String getTraining() {
        return training;
    }

    public void setTraining(final String training) {
        this.training = training;
    }

    public Float getAverageStrength() {
        return averageStrength;
    }

    public void setAverageStrength(final Float averageStrength) {
        this.averageStrength = averageStrength;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(final Position position) {
        this.position = position;
    }

    public Integer getForm() {
        return form;
    }

    public void setForm(final Integer form) {
        this.form = form;
    }

    public Integer getEnergy() {
        return energy;
    }

    public void setEnergy(final Integer energy) {
        this.energy = energy;
    }

    public Integer getEndurance() {
        return endurance;
    }

    public void setEndurance(final Integer endurance) {
        this.endurance = endurance;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(final Integer experience) {
        this.experience = experience;
    }

    public Integer getSkillGoalkeeping() {
        return skillGoalkeeping;
    }

    public void setSkillGoalkeeping(final Integer skillGoalkeeping) {
        this.skillGoalkeeping = skillGoalkeeping;
    }

    public Integer getSkillTackling() {
        return skillTackling;
    }

    public void setSkillTackling(final Integer skillTackling) {
        this.skillTackling = skillTackling;
    }

    public Integer getSkillPlaymaking() {
        return skillPlaymaking;
    }

    public void setSkillPlaymaking(final Integer skillPlaymaking) {
        this.skillPlaymaking = skillPlaymaking;
    }

    public Integer getSkillPassing() {
        return skillPassing;
    }

    public void setSkillPassing(final Integer skillPassing) {
        this.skillPassing = skillPassing;
    }

    public Integer getSkillScoring() {
        return skillScoring;
    }

    public void setSkillScoring(final Integer skillScoring) {
        this.skillScoring = skillScoring;
    }

    public String getTalent() {
        return talent;
    }

    public void setTalent(final String talent) {
        this.talent = talent;
    }

    public Integer getTalentLevel() {
        return talentLevel;
    }

    public void setTalentLevel(final Integer talentLevel) {
        this.talentLevel = talentLevel;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(final Integer salary) {
        this.salary = salary;
    }
}
