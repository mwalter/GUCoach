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

import java.util.List;

import org.newinstance.gucoach.entity.Player;

/**
 * Provides all service methods related to entity {@link Player}.
 *
 * @author mwalter
 */
public interface PlayerService {

    /**
     * Finds all players.
     *
     * @return all players
     */
    public List<Player> findAll();

    /**
     * Finds the player with id.
     *
     * @param id the id of the player to find
     * @return the player with the id
     */
    public Player findOne(final Long id);

    /**
     * Deletes a player. All history records of this player are deleted as well.<br/> The player does not need to know
     * about history records so there is no need to configure a relation to history records belonging to a player in the
     * player entity.
     *
     * @param player the player to delete
     */
    public void delete(final Player player);

    /**
     * Saves a player.
     *
     * @param player the player to save
     */
    public void save(final Player player);

    /**
     * Saves a list of players.
     *
     * @param players the players to save
     */
    public void save(final List<Player> players);

}
