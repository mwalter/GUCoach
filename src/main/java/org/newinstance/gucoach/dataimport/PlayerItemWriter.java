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

package org.newinstance.gucoach.dataimport;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newinstance.gucoach.entity.Player;
import org.newinstance.gucoach.service.PlayerService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Writes {@link Player}s to the database.
 *
 * @author mwalter
 */
@Component
public class PlayerItemWriter implements ItemWriter<Player> {

    /** The log4j logger. */
    private static final Logger LOGGER = LogManager.getLogger(PlayerItemWriter.class.getName());

    @Autowired
    private PlayerService playerService;

    /**
     * Writes {@link Player}s to the database. If the player already exists the player will be updated. If the import
     * file did not contain a player anymore this player is deleted from the database.
     *
     * @param players the list of {@link Player}s to write.
     * @throws Exception if an error occurs
     */
    @Override
    public void write(List<? extends Player> players) throws Exception {
        final List<Player> playersInDatabase = playerService.findAll();
        for (final Player player : playersInDatabase) {
            // DELETE
            // if player exists in database but not in import list anymore delete player's data from database
            if (!players.contains(player)) {
                LOGGER.info("Deleting Player [{}] with Id [{}].", player.getFullName(), player.getId());
                playerService.delete(player);
            }
        }

        for (final Player player : players) {
            playerService.save(player);
        }
    }

}
