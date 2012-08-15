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
import org.junit.Before;
import org.junit.Test;
import org.newinstance.gucoach.base.PersistenceTest;
import org.newinstance.gucoach.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests the methods of the {@link PlayerService}.
 *
 * @author mwalter
 */
public class PlayerServiceTest extends PersistenceTest {

    private PlayerService playerService;

    @Before
    public void init() {
        playerService = new PlayerService(em);
    }

    @Test
    public void insertPlayerTest() {
        final Player player = createPlayer();

        em.getTransaction().begin();
        playerService.insertPlayer(player);
        em.getTransaction().commit();

        final Player result = playerService.findPlayerById(player.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals("Foobar", player.getLastName());
        Assert.assertEquals(185, player.getHeight().intValue());
    }

    @Test
    public void removePlayerTest() {
        final Player player = createPlayer();

        final List<Player> players = new ArrayList<Player>();
        players.add(player);

        em.getTransaction().begin();
        playerService.insertPlayers(players);
        em.getTransaction().commit();

        List<Player> result = playerService.findAllPlayers();
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());

        // remove player
        em.getTransaction().begin();
        playerService.removePlayer(player);
        em.getTransaction().commit();

        result = playerService.findAllPlayers();
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }

}
