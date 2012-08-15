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
import org.newinstance.gucoach.gui.StandingsContentProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Tests methods of class {@link StandingsComparator}.
 *
 * @author mwalter
 */
public class StandingsComparatorTest {

    @Test
    public void testStandingsComparison() {
        final List<StandingsContentProvider.StandingsDataRow> standings = new ArrayList<StandingsContentProvider.StandingsDataRow>();
        final StandingsContentProvider.StandingsDataRow row1 = new StandingsContentProvider.StandingsDataRow("Aleppo", 5, 3, 2, 0, 12, 3, 11);
        final StandingsContentProvider.StandingsDataRow row2 = new StandingsContentProvider.StandingsDataRow("Jersey", 5, 2, 2, 1, 8, 5, 8);
        final StandingsContentProvider.StandingsDataRow row3 = new StandingsContentProvider.StandingsDataRow("Houston", 5, 2, 1, 2, 6, 5, 7);
        final StandingsContentProvider.StandingsDataRow row4 = new StandingsContentProvider.StandingsDataRow("Wien", 5, 0, 2, 3, 2, 9, 2);
        final StandingsContentProvider.StandingsDataRow row5 = new StandingsContentProvider.StandingsDataRow("Kiev", 5, 0, 0, 5, 2, 16, 0);

        standings.add(row1);
        standings.add(row2);
        standings.add(row3);
        standings.add(row4);
        standings.add(row5);

        Collections.sort(standings, new StandingsComparator<StandingsContentProvider.StandingsDataRow>());
        Assert.assertEquals(5, standings.size());

        Assert.assertEquals("Aleppo", standings.get(0).getTeamName());
        Assert.assertEquals("Kiev", standings.get(4).getTeamName());
    }

}
