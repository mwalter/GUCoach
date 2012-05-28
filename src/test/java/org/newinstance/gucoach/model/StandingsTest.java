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
 * Tests methods of class {@link Standings}.
 *
 * @author mwalter
 */
public class StandingsTest {

    @Test
    public void testStandings() {
        final Standings standings = new Standings();
        standings.setId(1L);
        standings.setGoalsAgainst(5);
        standings.setGoalsFor(11);
        final Date now = new Date();
        standings.setMatchDay(now);
        standings.setMatchesDrawn(2);
        standings.setMatchesLost(3);
        standings.setMatchesWon(5);
        standings.setPosition(2);
        standings.setTeamId(1L);

        Assert.assertEquals(1L, standings.getId().longValue());
        Assert.assertEquals(1L, standings.getTeamId().longValue());
        Assert.assertEquals(6, standings.getGoalsDiff().intValue());
        Assert.assertEquals(11, standings.getGoalsFor().intValue());
        Assert.assertEquals(5, standings.getGoalsAgainst().intValue());
        Assert.assertEquals(now, standings.getMatchDay());
        Assert.assertEquals(17, standings.getPoints().intValue());
        Assert.assertEquals(10, standings.getMatchesPlayed().intValue());
        Assert.assertEquals(5, standings.getMatchesWon().intValue());
        Assert.assertEquals(2, standings.getMatchesDrawn().intValue());
        Assert.assertEquals(3, standings.getMatchesLost().intValue());
        Assert.assertEquals(2, standings.getPosition().intValue());
    }

    @Test
    public void testToString() {
        final Standings standings = new Standings();
        standings.setId(1L);
        standings.setGoalsAgainst(5);
        standings.setGoalsFor(11);
        final Date now = new Date();
        standings.setMatchDay(now);
        standings.setMatchesDrawn(2);
        standings.setMatchesLost(3);
        standings.setMatchesWon(5);
        standings.setPosition(2);
        standings.setTeamId(1L);

        final String toString = standings.toString();
        Assert.assertTrue(toString.contains("id=1"));
        Assert.assertTrue(toString.contains("teamId=1"));
        Assert.assertTrue(toString.contains("goalsFor=11"));
        Assert.assertTrue(toString.contains("goalsAgainst=5"));
        Assert.assertTrue(toString.contains("matchesWon=5"));
        Assert.assertTrue(toString.contains("matchesDrawn=2"));
        Assert.assertTrue(toString.contains("matchesLost=3"));
        Assert.assertTrue(toString.contains("position=2"));
    }
}
