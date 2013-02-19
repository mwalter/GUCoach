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

import org.newinstance.gucoach.model.Player;
import org.newinstance.gucoach.model.PlayerStats;
import org.newinstance.gucoach.persistence.PersistenceService;
import org.newinstance.gucoach.utility.NamedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Implements all service methods related to entity {@link PlayerStats}.
 *
 * @author mwalter
 */
@Transactional
public class PlayerStatsServiceImpl implements PlayerStatsService {

    @Autowired
    private PersistenceService persistenceService;

    public PlayerStats findPlayerStatsByPlayer(final Player player) {
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("player", player);
        return persistenceService.findUniqueByNamedQuery(NamedQuery.FIND_PLAYER_STATS_BY_PLAYER.name(), PlayerStats.class, params);
    }

    public void insertPlayerStats(final PlayerStats playerStats) {
        persistenceService.save(playerStats);
    }

    public void updatePlayerStats(final PlayerStats playerStats) {
        persistenceService.update(playerStats);
    }
}
