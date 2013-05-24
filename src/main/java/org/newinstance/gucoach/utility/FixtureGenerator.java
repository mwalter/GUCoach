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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Generates all fixtures for a season.
 *
 * @author mwalter
 */
@Component
public class FixtureGenerator {

    /** The log4j logger. */
    private static final Logger LOGGER = LogManager.getLogger(FixtureGenerator.class.getName());

    /** Every league consists of 12 teams. */
    private static final int NUMBER_OF_TEAMS_IN_LEAGUE = 12;

    /** Holds all fixtures. First dimension is the matchday, second dimension is the group of 6 fixtures and third dimension is the fixture. */
    // TODO should be computed if possible
    private static final int[][][] FIXTURES = {{{1, 11}, {2, 9}, {3, 8}, {4, 7}, {10, 6}, {12, 5}}, {{1, 4}, {6, 12}, {7, 10}, {8, 5}, {9, 3}, {11, 2}},
            {{2, 1}, {3, 11}, {4, 9}, {5, 6}, {10, 8}, {12, 7}}, {{1, 12}, {2, 3}, {7, 6}, {8, 4}, {9, 10}, {11, 5}},
            {{3, 1}, {4, 2}, {5, 7}, {6, 8}, {10, 11}, {12, 9}}, {{1, 5}, {2, 10}, {3, 4}, {8, 7}, {9, 6}, {11, 12}},
            {{4, 11}, {5, 2}, {6, 1}, {7, 9}, {10, 3}, {12, 8}}, {{1, 7}, {2, 12}, {3, 5}, {4, 10}, {9, 8}, {11, 6}},
            {{5, 9}, {6, 3}, {7, 2}, {8, 11}, {10, 1}, {12, 4}}, {{1, 9}, {2, 8}, {3, 12}, {4, 6}, {10, 5}, {11, 7}},
            {{5, 4}, {6, 2}, {7, 3}, {8, 1}, {9, 11}, {12, 10}}, {{11, 1}, {9, 2}, {8, 3}, {7, 4}, {6, 10}, {5, 12}},
            {{4, 1}, {12, 6}, {10, 7}, {5, 8}, {3, 9}, {2, 11}}, {{1, 2}, {11, 3}, {9, 4}, {6, 5}, {8, 10}, {7, 12}},
            {{12, 1}, {3, 2}, {6, 7}, {4, 8}, {10, 9}, {5, 11}}, {{1, 3}, {2, 4}, {7, 5}, {8, 6}, {11, 10}, {9, 12}},
            {{5, 1}, {10, 2}, {4, 3}, {7, 8}, {6, 9}, {12, 11}}, {{11, 4}, {2, 5}, {1, 6}, {9, 7}, {3, 10}, {8, 12}},
            {{7, 1}, {12, 2}, {5, 3}, {10, 4}, {8, 9}, {6, 11}}, {{9, 5}, {3, 6}, {2, 7}, {11, 8}, {1, 10}, {4, 12}},
            {{9, 1}, {8, 2}, {12, 3}, {6, 4}, {5, 10}, {7, 11}}, {{4, 5}, {2, 6}, {3, 7}, {1, 8}, {11, 9}, {10, 12}}};

    /**
     * Generates the fixtures for all teams playing in a league based on the fixtures table.
     *
     * @param teamIds the ids of the teams playing in the league
     * @param firstMatchday the first matchday of the season, needed to calculate all following matchdays
     * @return a map containing all fixtures ordered by matchday (e.g. Date, Map&lt;Number of match, array of team ids [home team id, away team id]&gt;
     */
    public Map<Calendar, Map<Integer, Long[]>> generateFixtures(final List<Long> teamIds, final Calendar firstMatchday) {
        LOGGER.info("Generating fixtures. First matchday will be {}.", DateHelper.formatDate(firstMatchday.getTime()));

        // TODO can be removed if GUI checks for 12 teams being entered by user
        if (teamIds.size() != NUMBER_OF_TEAMS_IN_LEAGUE) {
            throw new IllegalArgumentException("Illegal number of teams.");
        }

        final Long[] teams = teamIds.toArray(new Long[teamIds.size()]);
        // do not change original calendar so make a copy
        final Calendar matchday = (Calendar) firstMatchday.clone();
        final Map<Calendar, Map<Integer, Long[]>> fixtures = new LinkedHashMap<Calendar, Map<Integer, Long[]>>();

        // iterate over matchdays
        for (int day = 0; day < FIXTURES.length; day++) {
            final Map<Integer, Long[]> fixturesOfMatchday = new LinkedHashMap<Integer, Long[]>();
            // iterate over all fixtures of a single matchday
            for (int fixture = 0; fixture < FIXTURES[day].length; fixture++) {
                // get home team id
                final Long homeTeamId = teams[FIXTURES[day][fixture][0] - 1];
                // get away team id
                final Long awayTeamId = teams[FIXTURES[day][fixture][1] - 1];
                fixturesOfMatchday.put(fixture, new Long[] {homeTeamId, awayTeamId});
            }
            // put all fixtures of one matchday into calendar map
            fixtures.put((Calendar) matchday.clone(), fixturesOfMatchday);
            // and increase date
            if (day % 2 == 0) {
                // even - next matchday will be in 4 days
                matchday.add(Calendar.DAY_OF_MONTH, 4);
            } else {
                // odd - next matchday will be in 3 days
                matchday.add(Calendar.DAY_OF_MONTH, 3);
            }
        }
        return fixtures;
    }

}
