/*
 * GUCoach - your personal coach for Goalunited (tm).
 * Licenced under General Public Licence v3 (GPLv3)
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

import org.junit.Assert;
import org.junit.Test;
import org.newinstance.gucoach.base.BaseTest;
import org.newinstance.gucoach.entity.Player;
import org.newinstance.gucoach.entity.PlayerStats;
import org.newinstance.gucoach.entity.Position;

/**
 * Tests the methods of the {@link PlayerStatsService}.
 *
 * @author mwalter
 */
public class PlayerStatsServiceTest extends BaseTest {

    @Test
    public void findByPlayer() {
        final Player player = createPlayer();
        player.setPlayerStats(createPlayerStats(player));

        playerService.save(player);

        final PlayerStats playerStats = playerStatsService.findByPlayer(player);
        Assert.assertNotNull(playerStats);
        Assert.assertEquals(player.getPlayerStats().getAverageStrength(), playerStats.getAverageStrength());
        Assert.assertEquals(player.getPlayerStats().getForm(), playerStats.getForm());
    }

    @Test
    public void insertAndDeletePlayerStats() {
        final Player player = createPlayer();
        player.setPlayerStats(createPlayerStats(player));

        playerService.save(player);

        Player result = playerService.findOne(player.getId());
        Assert.assertNotNull(result);

        PlayerStats playerStats = result.getPlayerStats();
        Assert.assertNotNull(playerStats);
        Assert.assertEquals("Player statistics does not match to player.", player, playerStats.getPlayer());

        // delete player (and related player statistics records)
        playerService.delete(player);

        result = playerService.findOne(player.getId());
        Assert.assertNull(result);
    }

    @Test
    public void insertPlayerStats() {
        final Player player = createPlayer();
        player.setPlayerStats(createPlayerStats(player));

        playerService.save(player);

        final Player result = playerService.findOne(player.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(90, result.getPlayerStats().getForm().intValue());
        Assert.assertEquals(Position.DEF, result.getPlayerStats().getPosition());
    }

    @Test
    public void insertUpdateAndDeletePlayerStats() {
        final Player player = createPlayer();
        player.setPlayerStats(createPlayerStats(player));

        playerService.save(player);

        Player result = playerService.findOne(player.getId());
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

        playerService.save(playerStats1.getPlayer());

        result = playerService.findOne(player.getId());
        Assert.assertNotNull(player);

        // compare updated values
        Assert.assertEquals(playerStats1.getEndurance(), result.getPlayerStats().getEndurance());
        Assert.assertEquals(playerStats1.getEnergy(), result.getPlayerStats().getEnergy());
        Assert.assertEquals(playerStats1.getForm(), result.getPlayerStats().getForm());

        // DELETE
        playerService.delete(player);

        result = playerService.findOne(player.getId());
        Assert.assertNull(result);
    }

    @Test
    public void updatePlayerStats() {
        final Player player = createPlayer();
        player.setPlayerStats(createPlayerStats(player));

        playerService.save(player);

        // update player statistics
        player.getPlayerStats().setAge(30);

        playerService.save(player);

        final Player result = playerService.findOne(player.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(30, result.getPlayerStats().getAge().intValue());
    }
}
