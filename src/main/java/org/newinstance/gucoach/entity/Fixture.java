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

package org.newinstance.gucoach.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * A fixture of two teams.
 *
 * @author mwalter
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"home_team_id", "away_team_id"}))
public final class Fixture {

    /** Primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** Home team. */
    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;

    /** Away team. */
    @ManyToOne
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;

    /** Fixture result. */
    @Column(name = "match_result")
    private String matchResult;

    /** Day of the match. */
    @Temporal(TemporalType.DATE)
    @Column(name = "match_day")
    private Date matchDay;

    public Team getAwayTeam() {
        return awayTeam;
    }

    public Team getHomeTeam() {
        return homeTeam;
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

    public void setAwayTeam(final Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setHomeTeam(final Team homeTeam) {
        this.homeTeam = homeTeam;
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

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Fixture [");
        builder.append("id=").append(id).append(", ");
        builder.append("homeTeamId=").append(homeTeam.getId()).append(", ");
        builder.append("awayTeamId=").append(awayTeam.getId()).append(", ");
        builder.append("matchResult=").append(matchResult).append(", ");
        builder.append("matchDay=").append(matchDay);
        builder.append("]");
        return builder.toString();
    }
}
