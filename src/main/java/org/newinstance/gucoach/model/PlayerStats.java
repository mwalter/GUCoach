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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * The statistics (mutable data) of a player.
 *
 * @author mwalter
 */
@Entity
public final class PlayerStats {

    /** Primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** Player. */
    @OneToOne
    @JoinColumn(name = "player_id")
    private Player player;

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
    @Column(name = "skill_goalkeeping")
    private Integer skillGoalkeeping;

    /** Tackling skill. */
    @Column(name = "skill_tackling")
    private Integer skillTackling;

    /** Playmaking skill. */
    @Column(name = "skill_playmaking")
    private Integer skillPlaymaking;

    /** Passing skill. */
    @Column(name = "skill_passing")
    private Integer skillPassing;

    /** Scoring skill. */
    @Column(name = "skill_scoring")
    private Integer skillScoring;

    /** Talent. */
    private String talent;

    /** Talent level. */
    @Column(name = "talent_level")
    private Integer talentLevel;

    /** Age. */
    private Integer age;

    /** Salary. */
    private Integer salary;

    /** Number of assignments. */
    private Integer assignments;

    /** Goals scored in current season. */
    @Column(name = "goals_season")
    private Integer goalsSeason;

    /** Goals scored in total. */
    @Column(name = "goals_total")
    private Integer goalsTotal;

    /** Market value. */
    @Column(name = "market_value")
    private Integer marketValue;

    /** Yellow cards seen in current season. */
    @Column(name = "yellow_cards_season")
    private Integer yellowCardsSeason;

    /** Yellow cards seen in total. */
    @Column(name = "yellow_cards_total")
    private Integer yellowCardsTotal;

    /** Red cards seen in current season. */
    @Column(name = "red_cards_season")
    private Integer redCardsSeason;

    /** Red cards seen in total. */
    @Column(name = "red_cards_total")
    private Integer redCardsTotal;

    /** Date the statistics were imported into the database. */
    @Temporal(TemporalType.DATE)
    @Column(name = "import_date")
    private Date importDate;

    public Integer getAge() {
        return age;
    }

    public Integer getAssignments() {
        return assignments;
    }

    public Float getAverageStrength() {
        return averageStrength;
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

    public Integer getForm() {
        return form;
    }

    public Integer getGoalsSeason() {
        return goalsSeason;
    }

    public Integer getGoalsTotal() {
        return goalsTotal;
    }

    public Long getId() {
        return id;
    }

    public Date getImportDate() {
        return importDate;
    }

    public Integer getMarketValue() {
        return marketValue;
    }

    public Integer getNumber() {
        return number;
    }

    public Player getPlayer() {
        return player;
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

    public void setAverageStrength(final Float averageStrength) {
        this.averageStrength = averageStrength;
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

    public void setForm(final Integer form) {
        this.form = form;
    }

    public void setGoalsSeason(final Integer goalsSeason) {
        this.goalsSeason = goalsSeason;
    }

    public void setGoalsTotal(final Integer goalsTotal) {
        this.goalsTotal = goalsTotal;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setImportDate(final Date importDate) {
        this.importDate = importDate;
    }

    public void setMarketValue(final Integer marketValue) {
        this.marketValue = marketValue;
    }

    public void setNumber(final Integer number) {
        this.number = number;
    }

    public void setPlayer(final Player player) {
        this.player = player;
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

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("PlayerStats [");
        builder.append("id=").append(id).append(", ");
        builder.append("playerId=").append(player.getId()).append(", ");
        builder.append("number=").append(number).append(", ");
        builder.append("training=").append(training).append(", ");
        builder.append("averageStrength=").append(averageStrength).append(", ");
        builder.append("position=").append(position).append(", ");
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

    /**
     * Compares a player statistics record with this instance. Does not override method {@code equals} for class {@code Object}.
     *
     * @param ps the player statistics to compare
     * @return {@code true} if there aren't any differences between the player statistics, {@code false} else
     */
    public boolean equals(final PlayerStats ps) {
        return ps.getAge().equals(age) &&
                ps.getAssignments().equals(assignments) &&
                ps.getAverageStrength().equals(averageStrength) &&
                ps.getEndurance().equals(endurance) &&
                ps.getEnergy().equals(energy) &&
                ps.getExperience().equals(experience) &&
                ps.getForm().equals(form) &&
                ps.getGoalsSeason().equals(goalsSeason) &&
                ps.getGoalsTotal().equals(goalsTotal) &&
                // ps.getMarketValue().equals(marketValue) &&
                ps.getNumber().equals(number) &&
                ps.getPlayer().getId().equals(player.getId()) &&
                ps.getPosition().equals(position) &&
                ps.getRedCardsSeason().equals(redCardsSeason) &&
                ps.getRedCardsTotal().equals(redCardsTotal) &&
                ps.getSalary().equals(salary) &&
                ps.getSkillGoalkeeping().equals(skillGoalkeeping) &&
                ps.getSkillPassing().equals(skillPassing) &&
                ps.getSkillPlaymaking().equals(skillPlaymaking) &&
                ps.getSkillScoring().equals(skillScoring) &&
                ps.getSkillTackling().equals(skillTackling) &&
                ps.getTalent().equals(talent) &&
                ps.getTalentLevel().equals(talentLevel) &&
                // ps.getTraining().equals(training) &&
                ps.getYellowCardsSeason().equals(yellowCardsSeason) &&
                ps.getYellowCardsTotal().equals(yellowCardsTotal);
    }
}
