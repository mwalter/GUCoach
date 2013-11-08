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

package org.newinstance.gucoach.service;

import java.util.Date;

import org.newinstance.gucoach.entity.StandingsHistory;
import org.newinstance.gucoach.entity.Team;

/**
 * Provides all service methods related to entity {@link StandingsHistory}.
 *
 * @author mwalter
 */
public interface StandingsHistoryService {

    /**
     * Persists standings history to the database.
     *
     * @param standingsHistory the standings history to persist
     */
    public void insertStandingsHistory(final StandingsHistory standingsHistory);

    /**
     * Returns the standings history for the given team and date.
     *
     * @param team the team
     * @param date the date
     * @return the standings history
     */
    public StandingsHistory findStandingsHistoryByTeamAndDate(final Team team, final Date date);

    /**
     * Updates the standings history.
     *
     * @param standingsHistory the standings history to update
     */
    public void updateStandingsHistory(final StandingsHistory standingsHistory);

    /** Removes all standings history from the database. */
    public void removeAllStandingsHistory();
}
