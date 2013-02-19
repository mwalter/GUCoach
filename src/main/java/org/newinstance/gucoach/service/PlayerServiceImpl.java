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
import org.newinstance.gucoach.model.PlayerHistory;
import org.newinstance.gucoach.persistence.PersistenceService;
import org.newinstance.gucoach.utility.NamedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implements all service methods related to entity {@link Player}.
 *
 * @author mwalter
 */
@Transactional
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PersistenceService persistenceService;

    public List<Player> findAllPlayers() {
        return persistenceService.findByNamedQuery(NamedQuery.FIND_ALL_PLAYER.name(), Player.class);
    }

    public Player findPlayerById(final Long id) {
        return persistenceService.find(Player.class, id);
    }

    public void insertPlayer(final Player player) {
        persistenceService.save(player);
    }

    public void insertPlayers(final List<Player> players) {
        for (final Player player : players) {
            persistenceService.save(player);
        }
    }

    public void updatePlayer(final Player player) {
        persistenceService.update(player);
    }

    public void removePlayer(final Player player) {
        final Player playerToRemove = persistenceService.find(Player.class, player.getId());
        // do we have player history records? if so remove them as well
        // this is done here and not in the player entity because the player does not need to know about history records
        final Map<String, Object> params = new HashMap <String, Object>();
        params.put("player", playerToRemove);
        final List<PlayerHistory> phResult = persistenceService.findByNamedQuery(NamedQuery.FIND_PLAYER_HISTORY_BY_PLAYER.name(), PlayerHistory.class, params);
        for (final PlayerHistory playerHistory : phResult) {
            persistenceService.delete(playerHistory);
        }

        persistenceService.delete(playerToRemove);
    }
}
