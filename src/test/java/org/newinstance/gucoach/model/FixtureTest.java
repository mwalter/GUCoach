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

package org.newinstance.gucoach.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Tests methods of class {@link Fixture}.
 *
 * @author mwalter
 */
public class FixtureTest {

    @Test
    public void testHomeTeam() {
        final Team team = new Team();
        final Fixture fixture = new Fixture();
        fixture.setHomeTeam(team);
        Assert.assertEquals(team, fixture.getHomeTeam());
    }

    @Test
    public void testId() {
        final Fixture fixture = new Fixture();
        fixture.setId(1L);
        Assert.assertEquals(1L, fixture.getId().longValue());
    }

    @Test
    public void testMatchDay() {
        final Fixture fixture = new Fixture();
        final Date date = new Date();
        fixture.setMatchDay(date);
        Assert.assertEquals(date, fixture.getMatchDay());
    }

    @Test
    public void testMatchResult() {
        final Fixture fixture = new Fixture();
        fixture.setMatchResult("3:1");
        Assert.assertEquals("3:1", fixture.getMatchResult());
    }

    @Test
    public void testAwayTeam() {
        final Team team = new Team();
        final Fixture fixture = new Fixture();
        fixture.setAwayTeam(team);
        Assert.assertEquals(team, fixture.getAwayTeam());
    }

    @Test
    public void testToString() {
        final Team team1 = new Team();
        team1.setId(3L);
        final Team team2 = new Team();
        team2.setId(5L);

        final Fixture fixture = new Fixture();
        fixture.setId(1L);
        fixture.setHomeTeam(team1);
        fixture.setAwayTeam(team2);
        fixture.setMatchResult("2:2");

        final String toString = fixture.toString();
        Assert.assertTrue(toString.contains("id=1"));
        Assert.assertTrue(toString.contains("homeTeamId=3"));
        Assert.assertTrue(toString.contains("awayTeamId=5"));
        Assert.assertTrue(toString.contains("matchResult=2:2"));
    }

}
