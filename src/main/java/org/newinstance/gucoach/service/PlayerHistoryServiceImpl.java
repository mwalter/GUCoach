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

package org.newinstance.gucoach.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.newinstance.gucoach.entity.Player;
import org.newinstance.gucoach.entity.PlayerHistory;
import org.newinstance.gucoach.persistence.PersistenceService;
import org.newinstance.gucoach.utility.NamedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implements all service methods related to entity {@link PlayerHistory}.
 *
 * @author mwalter
 */
@Service
@Transactional
public class PlayerHistoryServiceImpl implements PlayerHistoryService {

    @Autowired
    private PersistenceService persistenceService;

    public List<Date> findAllImportDates() {
        return persistenceService.findByNamedQuery(NamedQuery.FIND_ALL_IMPORT_DATE.name(), Date.class);
    }

    public Date findLatestImportDate() {
        return persistenceService.findUniqueByNamedQuery(NamedQuery.FIND_LATEST_IMPORT_DATE.name(), Date.class);
    }

    public PlayerHistory findPlayerHistoryById(final Long id) {
        return persistenceService.find(PlayerHistory.class, id);
    }

    public List<PlayerHistory> findPlayerHistoryByPlayer(final Player player) {
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("player", player);
        return persistenceService.findByNamedQuery(NamedQuery.FIND_PLAYER_HISTORY_BY_PLAYER.name(), PlayerHistory.class, params);
    }

    public void insertPlayerHistory(final PlayerHistory playerHistory) {
        persistenceService.save(playerHistory);
    }
}
