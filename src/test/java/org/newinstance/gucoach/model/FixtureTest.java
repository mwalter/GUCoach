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
    public void testHomeTeamId() {
        final Fixture fixture = new Fixture();
        fixture.setHomeTeamId(1L);
        Assert.assertEquals(1L, fixture.getHomeTeamId().longValue());
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
    public void testAwayTeamId() {
        final Fixture fixture = new Fixture();
        fixture.setAwayTeamId(1L);
        Assert.assertEquals(1L, fixture.getAwayTeamId().longValue());
    }

    @Test
    public void testToString() {
        final Fixture fixture = new Fixture();
        fixture.setId(1L);
        fixture.setHomeTeamId(3L);
        fixture.setAwayTeamId(5L);
        fixture.setMatchResult("2:2");

        final String toString = fixture.toString();
        Assert.assertTrue(toString.contains("id=1"));
        Assert.assertTrue(toString.contains("homeTeamId=3"));
        Assert.assertTrue(toString.contains("awayTeamId=5"));
        Assert.assertTrue(toString.contains("matchResult=2:2"));
    }

}
