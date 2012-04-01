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
 * Tests methods of class {@link Match}.
 *
 * @author mwalter
 */
public class MatchTest {

    @Test
    public void testHomeTeamId() {
        final Match match = new Match();
        match.setHomeTeamId(1L);
        Assert.assertEquals(1L, match.getHomeTeamId().longValue());
    }

    @Test
    public void testId() {
        final Match match = new Match();
        match.setId(1L);
        Assert.assertEquals(1L, match.getId().longValue());
    }

    @Test
    public void testMatchDay() {
        final Match match = new Match();
        final Date date = new Date();
        match.setMatchDay(date);
        Assert.assertEquals(date, match.getMatchDay());
    }

    @Test
    public void testMatchResult() {
        final Match match = new Match();
        match.setMatchResult("3:1");
        Assert.assertEquals("3:1", match.getMatchResult());
    }

    @Test
    public void testVisitingTeamId() {
        final Match match = new Match();
        match.setVisitingTeamId(1L);
        Assert.assertEquals(1L, match.getVisitingTeamId().longValue());
    }

    @Test
    public void testToString() {
        final Match match = new Match();
        match.setId(1L);
        match.setHomeTeamId(3L);
        match.setVisitingTeamId(5L);
        match.setMatchResult("2:2");

        final String toString = match.toString();
        Assert.assertTrue(toString.contains("id=1"));
        Assert.assertTrue(toString.contains("homeTeamId=3"));
        Assert.assertTrue(toString.contains("visitingTeamId=5"));
        Assert.assertTrue(toString.contains("matchResult=2:2"));
    }

}
