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

package org.newinstance.gucoach.entity;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Tests methods of class {@link Player}.
 *
 * @author mwalter
 * @version 04.02.12
 */
public class PlayerTest {

    @Test
    public void testFirstName() {
        final Player player = new Player();
        player.setFirstName("Joe");
        Assert.assertEquals("Joe", player.getFirstName());
    }

    @Test
    public void testLastName() {
        final Player player = new Player();
        player.setLastName("Foobar");
        Assert.assertEquals("Foobar", player.getLastName());
    }

    @Test
    public void testBirthday() {
        final Player player = new Player();
        player.setBirthday("13.08");
        Assert.assertEquals("13.08", player.getBirthday());
    }

    @Test
    public void testCountry() {
        final Player player = new Player();
        player.setCountry(Country.DE);
        Assert.assertEquals(Country.DE, player.getCountry());
    }

    @Test
    public void testId() {
        final Player player = new Player();
        player.setId(1L);
        Assert.assertEquals(1L, player.getId().longValue());
    }

    @Test
    public void testPreferredFoot() {
        final Player player = new Player();
        player.setStrongFoot(StrongFoot.L);
        Assert.assertEquals(StrongFoot.L, player.getStrongFoot());
    }

    @Test
    public void testHeight() {
        final Player player = new Player();
        player.setHeight(182);
        Assert.assertEquals(182, player.getHeight().intValue());
    }

    @Test
    public void testPersonality() {
        final Player player = new Player();
        player.setPersonality("flexible");
        Assert.assertEquals("flexible", player.getPersonality());
    }

    @Test
    public void testImportDate() {
        final Player player = new Player();
        player.setImportDate(new Date());
        Assert.assertNotNull(player.getImportDate());
    }
}
