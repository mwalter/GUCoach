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

package org.newinstance.gucoach.model;

import org.newinstance.gucoach.utility.DateHelper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * The history or development of a player's statistics.
 *
 * @author mwalter
 */
@Entity
public final class PlayerHistory {

    /** Primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** Player. */
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    /** Average strength. */
    @Column(name = "avg_strength")
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

    /** Date the statistics were imported into the database. */
    @Temporal(TemporalType.DATE)
    @Column(name = "import_date")
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

    public Player getPlayer() {
        return player;
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

    public void setPlayer(final Player player) {
        this.player = player;
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
        builder.append("playerId=").append(player.getId()).append(", ");
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
