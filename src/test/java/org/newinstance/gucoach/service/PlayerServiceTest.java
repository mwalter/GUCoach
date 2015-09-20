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

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.newinstance.gucoach.base.BaseTestClass;
import org.newinstance.gucoach.entity.Player;

/**
 * Tests the methods of the {@link PlayerService}.
 *
 * @author mwalter
 */
public class PlayerServiceTest extends BaseTestClass {

    @Test
    public void insertPlayer() {
        final Player player = createPlayer(1L);

        playerService.save(player);

        final Player result = playerService.findOne(player.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals("Foobar", player.getLastName());
        Assert.assertEquals(185, player.getHeight().intValue());
    }

    @Test
    public void updatePlayer() {
        final Player player = createPlayer(1L);

        playerService.save(player);

        player.setLastName(JUNIT);
        playerService.save(player);

        final Player result = playerService.findOne(player.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(JUNIT, player.getLastName());
    }

    @Test
    public void removePlayer() {
        final Player player = createPlayer(1L);

        final List<Player> players = new ArrayList<>();
        players.add(player);

        playerService.save(players);

        List<Player> result = playerService.findAll();
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());

        // remove player
        playerService.delete(player);

        result = playerService.findAll();
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }

}
