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

import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Test;
import org.newinstance.gucoach.base.BaseTest;
import org.newinstance.gucoach.entity.Player;
import org.newinstance.gucoach.entity.PlayerStats;
import org.newinstance.gucoach.gui.PlayerDataRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests methods of class {@link PlayerModel}.
 *
 * @author mwalter
 */
public class PlayerModelTest extends BaseTest {

    @Test
    public void getPlayerData() {
        createSomePlayers();
        final ObservableList<PlayerDataRow> playerList = playerModel.getPlayers();
        Assert.assertNotNull(playerList);
        Assert.assertFalse(playerList.isEmpty());
        for (final PlayerDataRow playerDataRow : playerList) {
            Assert.assertNotNull(playerDataRow.getHeight());
            Assert.assertNotNull(playerDataRow.getFullName());
        }
    }

    /** Creates some test players. */
    private void createSomePlayers() {
        final List<Player> newPlayers = new ArrayList<Player>();
        final Player player1 = new Player();
        player1.setId(1L);
        player1.setLastName("Daniels");
        player1.setFirstName("Jack");
        player1.setHeight(176);
        newPlayers.add(player1);
        final Player player2 = new Player();
        player2.setId(2L);
        player2.setLastName("Burton");
        player2.setFirstName("Tom");
        player2.setHeight(183);
        newPlayers.add(player2);
        final Player player3 = new Player();
        player3.setId(3L);
        player3.setLastName("Letter");
        player3.setFirstName("Jamie");
        player3.setHeight(186);
        newPlayers.add(player3);

        // create some player statistics
        final PlayerStats stats1 = new PlayerStats();
        stats1.setAge(23);
        stats1.setEnergy(88);
        stats1.setGoalsSeason(2);
        stats1.setPlayer(player1);
        player1.setPlayerStats(stats1);
        final PlayerStats stats2 = new PlayerStats();
        stats2.setAge(19);
        stats2.setEnergy(80);
        stats2.setGoalsSeason(5);
        stats2.setPlayer(player2);
        player2.setPlayerStats(stats2);
        final PlayerStats stats3 = new PlayerStats();
        stats3.setAge(27);
        stats3.setEnergy(98);
        stats3.setGoalsSeason(0);
        stats3.setPlayer(player3);
        player3.setPlayerStats(stats3);

        // insert players and set into model
        playerService.insertPlayers(newPlayers);
        playerModel.setPlayers(playerService.findAllPlayers());
    }
}
