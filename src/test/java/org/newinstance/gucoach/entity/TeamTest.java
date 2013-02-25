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

/**
 * Tests methods of class {@link Team}.
 *
 * @author mwalter
 */
public class TeamTest {

    @Test
    public void testTeam() {
        final Team team = new Team();
        team.setId(1L);
        team.setName("FC Barcelona");
        team.setStartPos(3);
        team.setStrength(3.2f);

        Assert.assertEquals(1L, team.getId().longValue());
        Assert.assertEquals("FC Barcelona", team.getName());
        Assert.assertEquals(3, team.getStartPos().intValue());
        Assert.assertEquals(3.2f, team.getStrength(), 0);
    }

    @Test
    public void testToString() {
        final Team team = new Team();
        team.setId(1L);
        team.setName("FC Barcelona");
        team.setStartPos(3);
        team.setStrength(3.2f);

        final String toString = team.toString();
        Assert.assertTrue(toString.contains("id=1"));
        Assert.assertTrue(toString.contains("name=FC Barcelona"));
        Assert.assertTrue(toString.contains("startPos=3"));
        Assert.assertTrue(toString.contains("strength=3.2"));
    }
}
