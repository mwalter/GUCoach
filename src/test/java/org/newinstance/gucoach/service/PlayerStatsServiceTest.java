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

import org.junit.Assert;
import org.junit.Test;
import org.newinstance.gucoach.base.BaseTest;
import org.newinstance.gucoach.model.Player;
import org.newinstance.gucoach.model.PlayerStats;
import org.newinstance.gucoach.model.Position;

/**
 * Tests the methods of the {@link PlayerStatsService}.
 *
 * @author mwalter
 */
public class PlayerStatsServiceTest extends BaseTest {

    @Test
    public void insertAndDeletePlayerStats() {
        final Player player = createPlayer();
        player.setPlayerStats(createPlayerStats(player));

        playerService.insertPlayer(player);

        Player result = playerService.findPlayerById(player.getId());
        Assert.assertNotNull(result);

        PlayerStats playerStats = result.getPlayerStats();
        Assert.assertNotNull(playerStats);
        Assert.assertEquals("Player statistics does not match to player.", player, playerStats.getPlayer());

        // delete player (and related player statistics records)
        playerService.removePlayer(player);

        result = playerService.findPlayerById(player.getId());
        Assert.assertNull(result);
    }

    @Test
    public void insertPlayerStats() {
        final Player player = createPlayer();
        player.setPlayerStats(createPlayerStats(player));

        playerService.insertPlayer(player);

        final Player result = playerService.findPlayerById(player.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(90, result.getPlayerStats().getForm().intValue());
        Assert.assertEquals(Position.DEF, result.getPlayerStats().getPosition());
    }

    @Test
    public void insertUpdateAndDeletePlayerStats() {
        final Player player = createPlayer();
        player.setPlayerStats(createPlayerStats(player));

        playerService.insertPlayer(player);

        Player result = playerService.findPlayerById(player.getId());
        Assert.assertNotNull(result);

        PlayerStats oldPlayerStats = result.getPlayerStats();
        Assert.assertNotNull(oldPlayerStats);
        Assert.assertEquals("Player statistics do not match to player.", player, oldPlayerStats.getPlayer());

        // UPDATE
        final PlayerStats playerStats1 = result.getPlayerStats();
        playerStats1.setEndurance(65);
        playerStats1.setEnergy(71);
        playerStats1.setForm(85);
        result.setPlayerStats(playerStats1);

        playerService.updatePlayer(playerStats1.getPlayer());

        result = playerService.findPlayerById(player.getId());
        Assert.assertNotNull(player);

        // compare updated values
        Assert.assertEquals(playerStats1.getEndurance(), result.getPlayerStats().getEndurance());
        Assert.assertEquals(playerStats1.getEnergy(), result.getPlayerStats().getEnergy());
        Assert.assertEquals(playerStats1.getForm(), result.getPlayerStats().getForm());

        // DELETE
        playerService.removePlayer(player);

        result = playerService.findPlayerById(player.getId());
        Assert.assertNull(result);
    }

    @Test
    public void updatePlayerStats() {
        final Player player = createPlayer();
        player.setPlayerStats(createPlayerStats(player));

        playerService.insertPlayer(player);

        // update player statistics
        player.getPlayerStats().setAge(30);

        playerService.updatePlayer(player);

        final Player result = playerService.findPlayerById(player.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(30, result.getPlayerStats().getAge().intValue());
    }
}
