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
 * Tests methods of class {@link StandingsHistory}.
 *
 * @author mwalter
 */
public class StandingsHistoryTest {

    @Test
    public void testStandings() {
        final StandingsHistory standingsHistory = new StandingsHistory();
        standingsHistory.setId(1L);
        standingsHistory.setGoalsAgainst(5);
        standingsHistory.setGoalsFor(11);
        final Date now = new Date();
        standingsHistory.setMatchDay(now);
        standingsHistory.setMatchesDrawn(2);
        standingsHistory.setMatchesLost(3);
        standingsHistory.setMatchesWon(5);
        standingsHistory.setPosition(2);
        standingsHistory.setTeamId(1L);

        Assert.assertEquals(1L, standingsHistory.getId().longValue());
        Assert.assertEquals(1L, standingsHistory.getTeamId().longValue());
        Assert.assertEquals(6, standingsHistory.getGoalsDiff().intValue());
        Assert.assertEquals(11, standingsHistory.getGoalsFor().intValue());
        Assert.assertEquals(5, standingsHistory.getGoalsAgainst().intValue());
        Assert.assertEquals(now, standingsHistory.getMatchDay());
        Assert.assertEquals(17, standingsHistory.getPoints().intValue());
        Assert.assertEquals(10, standingsHistory.getMatchesPlayed().intValue());
        Assert.assertEquals(5, standingsHistory.getMatchesWon().intValue());
        Assert.assertEquals(2, standingsHistory.getMatchesDrawn().intValue());
        Assert.assertEquals(3, standingsHistory.getMatchesLost().intValue());
        Assert.assertEquals(2, standingsHistory.getPosition().intValue());
    }

    @Test
    public void testToString() {
        final StandingsHistory standingsHistory = new StandingsHistory();
        standingsHistory.setId(1L);
        standingsHistory.setGoalsAgainst(5);
        standingsHistory.setGoalsFor(11);
        final Date now = new Date();
        standingsHistory.setMatchDay(now);
        standingsHistory.setMatchesDrawn(2);
        standingsHistory.setMatchesLost(3);
        standingsHistory.setMatchesWon(5);
        standingsHistory.setPosition(2);
        standingsHistory.setTeamId(1L);

        final String toString = standingsHistory.toString();
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
