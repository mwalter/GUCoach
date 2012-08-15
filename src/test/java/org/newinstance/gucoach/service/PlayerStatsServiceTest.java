/*
 * GUCoach - your personal coach for Goalunited (tm).
 * Copyright (C) 2012 newInstance.org
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
import org.junit.Before;
import org.junit.Test;
import org.newinstance.gucoach.base.PersistenceTest;
import org.newinstance.gucoach.model.Player;
import org.newinstance.gucoach.model.PlayerStats;
import org.newinstance.gucoach.model.Position;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Tests the methods of the {@link PlayerStatsService}.
 *
 * @author mwalter
 */
public class PlayerStatsServiceTest extends PersistenceTest {

    private PlayerService playerService;
    private PlayerStatsService playerStatsService;

    @Before
    public void init() {
        playerService = new PlayerService(em);
        playerStatsService = new PlayerStatsService(em);
    }

    @Test(expected = NoResultException.class)
    public void insertAndDeletePlayerStatsTest() {
        final Player player = createPlayer();
        final PlayerStats playerStats1 = createPlayerStats(player);

        em.getTransaction().begin();
        playerService.insertPlayer(player);
        playerStatsService.insertPlayerStats(playerStats1);
        em.getTransaction().commit();

        List<Player> playerList = playerService.findAllPlayers();
        Assert.assertNotNull(playerList);
        Assert.assertFalse(playerList.isEmpty());

        PlayerStats playerStats = playerStatsService.findPlayerStatsByPlayer(player);
        Assert.assertNotNull(playerStats);
        Assert.assertEquals("Player statistics does not match to player.", player, playerStats.getPlayer());

        // delete player (and related player statistics records)
        em.getTransaction().begin();
        playerService.removePlayer(player);
        em.getTransaction().commit();

        playerList = playerService.findAllPlayers();
        Assert.assertNotNull(playerList);
        Assert.assertTrue(playerList.isEmpty());

        playerStatsService.findPlayerStatsByPlayer(player);
    }

    @Test
    public void insertPlayerStatsTest() {
        final Player player = createPlayer();
        final PlayerStats playerStats = createPlayerStats(player);

        em.getTransaction().begin();
        playerService.insertPlayer(player);
        playerStatsService.insertPlayerStats(playerStats);
        em.getTransaction().commit();

        final PlayerStats result = em.find(PlayerStats.class, playerStats.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(90, result.getForm().intValue());
        Assert.assertEquals(Position.DEF, result.getPosition());
    }

    @Test(expected = NoResultException.class)
    public void insertUpdateAndDeletePlayerStatsTest() {
        final Player player = createPlayer();
        final PlayerStats playerStats1 = createPlayerStats(player);

        em.getTransaction().begin();
        playerService.insertPlayer(player);
        playerStatsService.insertPlayerStats(playerStats1);
        em.getTransaction().commit();

        List<Player> playerList = playerService.findAllPlayers();
        Assert.assertNotNull(playerList);
        Assert.assertFalse(playerList.isEmpty());

        PlayerStats playerStats = playerStatsService.findPlayerStatsByPlayer(player);
        Assert.assertNotNull(playerStats);
        Assert.assertEquals("Player statistics do not match to player.", player, playerStats.getPlayer());

        // UPDATE
        playerStats1.setPlayer(player);
        playerStats1.setEndurance(65);
        playerStats1.setEnergy(71);
        playerStats1.setForm(85);

        em.getTransaction().begin();
        playerStatsService.updatePlayerStats(playerStats1);
        em.getTransaction().commit();

        playerStats = playerStatsService.findPlayerStatsByPlayer(player);
        Assert.assertNotNull(playerStats);

        // compare updated values
        Assert.assertEquals(playerStats1.getEndurance(), playerStats.getEndurance());
        Assert.assertEquals(playerStats1.getEnergy(), playerStats.getEnergy());
        Assert.assertEquals(playerStats1.getForm(), playerStats.getForm());

        // DELETE
        em.getTransaction().begin();
        playerService.removePlayer(player);
        em.getTransaction().commit();

        playerList = playerService.findAllPlayers();
        Assert.assertNotNull(playerList);
        Assert.assertTrue(playerList.isEmpty());

        playerStatsService.findPlayerStatsByPlayer(player);
    }

    @Test
    public void updatePlayerStatsTest() {
        final Player player = createPlayer();
        final PlayerStats playerStats = createPlayerStats(player);

        em.getTransaction().begin();
        playerService.insertPlayer(player);
        playerStatsService.insertPlayerStats(playerStats);
        em.getTransaction().commit();

        // update player statistics
        playerStats.setAge(30);

        em.getTransaction().begin();
        playerStatsService.updatePlayerStats(playerStats);
        em.getTransaction().commit();

        final PlayerStats result = playerStatsService.findPlayerStatsByPlayer(player);
        Assert.assertNotNull(result);
        Assert.assertEquals(30, result.getAge().intValue());
    }
}
