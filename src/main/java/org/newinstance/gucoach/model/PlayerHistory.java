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
 * The history or development of a player's statistics.
 *
 * @author mwalter
 */
public final class PlayerHistory {

    /** Primary key. */
    private Long id;
    /** Player foreign key */
    private Long playerId;
    /** Average strength. */
    private Float averageStrength;
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
    /** Date the statistics were imported into the database. */
    private Date importDate;

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

    public Long getId() {
        return id;
    }

    public Date getImportDate() {
        return importDate;
    }

    public Long getPlayerId() {
        return playerId;
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

    public void setId(final Long id) {
        this.id = id;
    }

    public void setImportDate(final Date importDate) {
        this.importDate = importDate;
    }

    public void setPlayerId(final Long playerId) {
        this.playerId = playerId;
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

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("PlayerHistory [");
        builder.append("id=").append(id).append(", ");
        builder.append("playerId=").append(playerId).append(", ");
        builder.append("averageStrength=").append(averageStrength).append(", ");
        builder.append("form=").append(form).append(", ");
        builder.append("energy=").append(energy).append(", ");
        builder.append("endurance=").append(endurance).append(", ");
        builder.append("experience=").append(experience).append(", ");
        builder.append("skillGoalkeeping=").append(skillGoalkeeping).append(", ");
        builder.append("skillTackling=").append(skillTackling).append(", ");
        builder.append("skillPlaymaking=").append(skillPlaymaking).append(", ");
        builder.append("skillPassing=").append(skillPassing).append(", ");
        builder.append("skillScoring=").append(skillScoring).append(", ");
        builder.append("importDate=").append(DateHelper.formatDate(importDate));
        builder.append("]");
        return builder.toString();
    }
}
