/*
 * GUCoach - your personal coach for Goalunited (tm).
 * Licensed under General Public Licence v3 (GPLv3)
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

package org.newinstance.gucoach.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newinstance.gucoach.entity.Player;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains all player data.
 *
 * @author mwalter
 */
@Component
public class PlayerModel {

    private static final Logger logger = LogManager.getLogger(PlayerModel.class.getName());

    /** Holds all players. */
    private ObservableList<Player> players = FXCollections.observableArrayList(new ArrayList<Player>());

    /**
     * Returns all players.
     *
     * @return the observable list of players
     */
    public ObservableList<Player> getPlayers() {
        return players;
    }

    /**
     * Sets all players. Player entities are converted to player data rows.
     *
     * @param players the players to set
     */
    public void setPlayers(final List<Player> players) {
        // first remove all players then add new player data
        this.players.removeAll(this.players);
        for (final Player player : players) {
            this.players.add(player);
        }
        logger.info("Player model has been set with {} players.", players.size());
    }

}
