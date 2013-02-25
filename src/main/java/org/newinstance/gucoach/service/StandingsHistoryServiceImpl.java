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

import org.newinstance.gucoach.entity.StandingsHistory;
import org.newinstance.gucoach.entity.Team;
import org.newinstance.gucoach.persistence.PersistenceService;
import org.newinstance.gucoach.utility.NamedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implements all service methods related to entity {@link StandingsHistory}.
 *
 * @author mwalter
 */
@Component
@Transactional
public class StandingsHistoryServiceImpl implements StandingsHistoryService {

    @Autowired
    private PersistenceService persistenceService;

    /**
     * Persists standings history to the database.
     *
     * @param standingsHistory the standings history to persist
     */
    public void insertStandingsHistory(final StandingsHistory standingsHistory) {
        persistenceService.save(standingsHistory);
    }

    /**
     * Returns the standings history for the given team and date.
     *
     * @param team the team
     * @param date the date
     * @return the standings history
     */
    public StandingsHistory findStandingsHistoryByTeamAndDate(final Team team, final Date date) {
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("team", team);
        params.put("matchDay", date);
        return persistenceService.findUniqueByNamedQuery(NamedQuery.FIND_STANDINGS_HISTORY_BY_TEAM_AND_DATE.name(), StandingsHistory.class, params);
    }

    /**
     * Updates the standings history.
     *
     * @param standingsHistory the standings history to update
     */
    public void updateStandingsHistory(final StandingsHistory standingsHistory) {
        persistenceService.update(standingsHistory);
    }

    /** Removes all standings history from the database. */
    public void removeAllStandingsHistory() {
        final List<StandingsHistory> shToDeleteList = persistenceService.findByNamedQuery(NamedQuery.FIND_ALL_STANDINGS_HISTORY.name(), StandingsHistory.class);
        for (StandingsHistory standingsHistory : shToDeleteList) {
            persistenceService.delete(standingsHistory);
        }
    }
}
