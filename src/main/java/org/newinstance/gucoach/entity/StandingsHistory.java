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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import java.util.Date;

/**
 * A standings record of a team at a certain date. Used to retain the development of each team in the league.
 *
 * @author mwalter
 */
@Entity
@NamedQueries({@NamedQuery(name = "FIND_ALL_STANDINGS_HISTORY", query = "SELECT sh FROM StandingsHistory sh"),
        @NamedQuery(name = "FIND_STANDINGS_HISTORY_BY_TEAM_AND_DATE", query = "SELECT sh FROM StandingsHistory sh WHERE sh.team = :team AND sh.matchDay = :matchDay")})
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"team_id", "match_day"}))
public final class StandingsHistory {

    /** Primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** The date this standings record is valid from. */
    @Temporal(TemporalType.DATE)
    @Column(name = "match_day")
    private Date matchDay;

    /** Number of matches won. */
    @Column(name = "matches_won")
    private Integer matchesWon;

    /** Number of matches drawn. */
    @Column(name = "matches_drawn")
    private Integer matchesDrawn;

    /** Number of matches lost. */
    @Column(name = "matches_lost")
    private Integer matchesLost;

    /** Number of goals scored. */
    @Column(name = "goals_for")
    private Integer goalsFor;

    /** Number of goals received. */
    @Column(name = "goals_against")
    private Integer goalsAgainst;

    /** Current position in standings. */
    private Integer position;

    /** The team's primary key. */
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Date getMatchDay() {
        return matchDay;
    }

    public void setMatchDay(final Date matchDay) {
        this.matchDay = matchDay;
    }

    public Integer getMatchesPlayed() {
        return matchesWon + matchesDrawn + matchesLost;
    }

    public Integer getMatchesWon() {
        return matchesWon;
    }

    public void setMatchesWon(final Integer matchesWon) {
        this.matchesWon = matchesWon;
    }

    public Integer getMatchesDrawn() {
        return matchesDrawn;
    }

    public void setMatchesDrawn(final Integer matchesDrawn) {
        this.matchesDrawn = matchesDrawn;
    }

    public Integer getMatchesLost() {
        return matchesLost;
    }

    public void setMatchesLost(final Integer matchesLost) {
        this.matchesLost = matchesLost;
    }

    public Integer getGoalsDiff() {
        return goalsFor - goalsAgainst;
    }

    public Integer getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(final Integer goalsFor) {
        this.goalsFor = goalsFor;
    }

    public Integer getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(final Integer goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public Integer getPoints() {
        return matchesWon * 3 + matchesDrawn;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(final Integer position) {
        this.position = position;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(final Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("StandingsHistory [");
        builder.append("id=").append(id).append(", ");
        builder.append("teamId=").append(team.getId()).append(", ");
        builder.append("position=").append(position).append(", ");
        builder.append("matchDay=").append(matchDay).append(", ");
        builder.append("matchesWon=").append(matchesWon).append(", ");
        builder.append("matchesDrawn=").append(matchesDrawn).append(", ");
        builder.append("matchesLost=").append(matchesLost).append(", ");
        builder.append("goalsFor=").append(goalsFor).append(", ");
        builder.append("goalsAgainst=").append(goalsAgainst);
        builder.append("]");
        return builder.toString();
    }
}
