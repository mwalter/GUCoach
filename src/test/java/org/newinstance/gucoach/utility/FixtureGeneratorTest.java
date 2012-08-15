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

package org.newinstance.gucoach.utility;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Tests the methods of the {@link FixtureGenerator}.
 *
 * @author mwalter
 */
public class FixtureGeneratorTest {

    @Test(expected = IllegalArgumentException.class)
    public void checkGenerateFixturesWithInsufficientTeams() {
        final List<Long> teamIds = new ArrayList<Long>();
        teamIds.add(1L);
        FixtureGenerator.generateFixtures(teamIds, Calendar.getInstance());
    }

    @Test
    public void checkGenerateFixtures() {
        final Map<Calendar, Map<Integer, Long[]>> fixtures = FixtureGenerator.generateFixtures(getTeamIds(), Calendar.getInstance());
        for (Map<Integer, Long[]> fixturesOfMatchday : fixtures.values()) {
            // there always have to be 6 fixtures per matchday
            Assert.assertEquals(6, fixturesOfMatchday.size());

            final List<Long> allTeams = getTeamIds();
            for (Long[] fixture : fixturesOfMatchday.values()) {
                Assert.assertTrue(allTeams.contains(fixture[0]));
                Assert.assertTrue(allTeams.contains(fixture[1]));
                allTeams.remove(fixture[0]);
                allTeams.remove(fixture[1]);
            }
            // if list is empty all teams had been lined up for a fixture
            Assert.assertTrue(allTeams.isEmpty());
        }

        // there are 22 matchdays per season
        Assert.assertEquals(22, fixtures.size());
    }

    /**
     * Returns a list with some team ids.
     *
     * @return a list with team ids
     */
    private List<Long> getTeamIds() {
        final List<Long> teamIds = new ArrayList<Long>();
        teamIds.add(1L);
        teamIds.add(2L);
        teamIds.add(3L);
        teamIds.add(4L);
        teamIds.add(5L);
        teamIds.add(6L);
        teamIds.add(7L);
        teamIds.add(8L);
        teamIds.add(9L);
        teamIds.add(10L);
        teamIds.add(11L);
        teamIds.add(12L);
        return teamIds;
    }
}
