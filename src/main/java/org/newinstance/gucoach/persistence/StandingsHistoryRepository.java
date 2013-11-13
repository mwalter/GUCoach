/*
 * GUCoach - your personal coach for Goalunited (tm).
 * Licensed under General Public License v3 (GPLv3)
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

package org.newinstance.gucoach.persistence;

import java.util.Date;

import org.newinstance.gucoach.entity.StandingsHistory;
import org.newinstance.gucoach.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provides all repository methods related to entity {@link StandingsHistory}.
 *
 * @author mwalter
 */
@Repository
public interface StandingsHistoryRepository extends JpaRepository<StandingsHistory, Long> {

    /**
     * Returns the standings history for the given team and date.
     *
     * @param team the team
     * @param matchDay the date ot the match
     * @return the standings history
     */
    public StandingsHistory findByTeamAndMatchDay(final Team team, final Date matchDay);

}
