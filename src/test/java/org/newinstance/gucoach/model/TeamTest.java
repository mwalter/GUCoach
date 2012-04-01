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

/**
 * Tests methods of class {@link Team}.
 *
 * @author mwalter
 */
public class TeamTest {

    @Test
    public void testId() {
        final Team team = new Team();
        team.setId(1L);
        Assert.assertEquals(1L, team.getId().longValue());
    }

    @Test
    public void testPosition() {
        final Team team = new Team();
        team.setPosition(1);
        Assert.assertEquals(1, team.getPosition().intValue());
    }

    @Test
    public void testName() {
        final Team team = new Team();
        team.setName("FC Barcelona");
        Assert.assertEquals("FC Barcelona", team.getName());
    }

    @Test
    public void testStrength() {
        final Team team = new Team();
        team.setStrength(3.2f);
        Assert.assertEquals(3.2f, team.getStrength(), 0);
    }

    @Test
    public void testMatchesWon() {
        final Team team = new Team();
        team.setMatchesWon(5);
        Assert.assertEquals(5, team.getMatchesWon().intValue());
    }

    @Test
    public void testMatchesDrawn() {
        final Team team = new Team();
        team.setMatchesDrawn(5);
        Assert.assertEquals(5, team.getMatchesDrawn().intValue());
    }

    @Test
    public void testMatchesLost() {
        final Team team = new Team();
        team.setMatchesLost(5);
        Assert.assertEquals(5, team.getMatchesLost().intValue());
    }

    @Test
    public void testPoints() {
        final Team team = new Team();
        team.setPoints(9);
        Assert.assertEquals(9, team.getPoints().intValue());
    }

    @Test
    public void testGoalsFor() {
        final Team team = new Team();
        team.setGoalsFor(5);
        Assert.assertEquals(5, team.getGoalsFor().intValue());
    }

    @Test
    public void testGoalsAgainst() {
        final Team team = new Team();
        team.setGoalsAgainst(5);
        Assert.assertEquals(5, team.getGoalsAgainst().intValue());
    }

    @Test
    public void testToString() {
        final Team team = new Team();
        team.setId(1L);
        team.setName("FC Barcelona");
        team.setMatchesWon(5);
        team.setMatchesDrawn(0);
        team.setMatchesLost(0);
        team.setPoints(15);
        team.setGoalsFor(27);
        team.setGoalsAgainst(3);

        final String toString = team.toString();
        Assert.assertTrue(toString.contains("id=1"));
        Assert.assertTrue(toString.contains("name=FC Barcelona"));
        Assert.assertTrue(toString.contains("matchesWon=5"));
        Assert.assertTrue(toString.contains("matchesDrawn=0"));
        Assert.assertTrue(toString.contains("matchesLost=0"));
        Assert.assertTrue(toString.contains("points=15"));
        Assert.assertTrue(toString.contains("goalsFor=27"));
        Assert.assertTrue(toString.contains("goalsAgainst=3"));
    }
}
