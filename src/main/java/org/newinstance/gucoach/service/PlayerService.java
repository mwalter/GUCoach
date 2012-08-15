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
import org.newinstance.gucoach.model.PlayerStats;
import org.newinstance.gucoach.utility.NamedQuery;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Provides all service methods related to entity {@link Player}.
 *
 * @author mwalter
 */
public class PlayerService extends PersistenceService {

    /**
     * The service needs an {@link EntityManager}.
     *
     * @param entityManager the entity manager
     */
    public PlayerService(final EntityManager entityManager) {
        super(entityManager);
    }

    /**
     * Returns all players in the database.
     *
     * @return the list of players
     */
    public List<Player> findAllPlayers() {
        return em.createNamedQuery(NamedQuery.FIND_ALL_PLAYER.name(), Player.class).getResultList();
    }

    /**
     * Returns the player with the given id.
     *
     * @param id the primary key of the player
     * @return the player
     */
    public Player findPlayerById(final Long id) {
        return em.find(Player.class, id);
    }

    /**
     * Persists a player to the database.
     *
     * @param player the player to persist
     */
    public void insertPlayer(final Player player) {
        em.persist(player);
    }

    /**
     * Persists players to the database.
     *
     * @param players the list of players to persist
     */
    public void insertPlayers(final List<Player> players) {
        for (final Player player : players) {
            em.persist(player);
        }
    }

    /**
     * Removes a player from the database.
     *
     * @param player the player to remove
     */
    public void removePlayer(final Player player) {
        // do we have player history records? if so remove them as well
        // this is done here and not in the player entity because the player does not need to know about history records
        final TypedQuery<PlayerHistory> phQquery = em.createNamedQuery(NamedQuery.FIND_PLAYER_HISTORY_BY_PLAYER.name(), PlayerHistory.class);
        phQquery.setParameter("player", player);
        final List<PlayerHistory> phResult = phQquery.getResultList();
        for (final PlayerHistory playerHistory : phResult) {
            em.remove(playerHistory);
        }

        // do we have player statistics records? if so remove them as well
        // this is done here and not in the player entity because the player does not need to know about statistics records
        final TypedQuery<PlayerStats> psQuery = em.createNamedQuery(NamedQuery.FIND_PLAYER_STATS_BY_PLAYER.name(), PlayerStats.class);
        psQuery.setParameter("player", player);
        final List<PlayerStats> psResult = psQuery.getResultList();
        for (final PlayerStats playerStats : psResult) {
            em.remove(playerStats);
        }

        em.remove(player);
    }
}
