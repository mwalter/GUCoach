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

import java.util.List;

/**
 * Provides all service methods related to entity {@link Player}.
 *
 * @author mwalter
 */
public interface PlayerService {

    /**
     * Returns all players in the database.
     *
     * @return the list of players
     */
    public List<Player> findAllPlayers();

    /**
     * Returns the player with the given id.
     *
     * @param id the primary key of the player
     * @return the player
     */
    public Player findPlayerById(final Long id);

    /**
     * Persists a player to the database.
     *
     * @param player the player to persist
     */
    public void insertPlayer(final Player player);

    /**
     * Persists players to the database.
     *
     * @param players the list of players to persist
     */
    public void insertPlayers(final List<Player> players);

    /**
     * Updates the player.
     *
     * @param player the player to update
     */
    public void updatePlayer(final Player player);

    /**
     * Removes a player from the database.
     *
     * @param player the player to remove
     */
    public void removePlayer(final Player player);

}
