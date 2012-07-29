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

package org.newinstance.gucoach.utility;

import org.newinstance.gucoach.gui.StandingsContentProvider;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Compares the data of every {@link StandingsContentProvider.StandingsDataRow} in order to define the team rankings. There are three comparators used in the
 * following order:
 * <ol>
 * <li>Comparator of points the team achieved</li>
 * <li>Comparator of goals for the team</li>
 * <li>Comparator of goals difference (goals for - goals against)</li>
 * </ol>
 *
 * @author mwalter
 */
public final class StandingsComparator<T> implements Comparator<T> {

    /** List holding all comparators. */
    private List<Comparator<StandingsContentProvider.StandingsDataRow>> comparators = new ArrayList<Comparator<StandingsContentProvider.StandingsDataRow>>();

    /** The default constructor adds all required comparators to the comparators list. */
    public StandingsComparator() {
        /* The comparator comparing the difference between goals for and goals against. */
        final Comparator<StandingsContentProvider.StandingsDataRow> GOALS_DIFF_COMPARATOR = new Comparator<StandingsContentProvider.StandingsDataRow>() {
            @Override
            public int compare(StandingsContentProvider.StandingsDataRow row1, StandingsContentProvider.StandingsDataRow row2) {
                return Integer.compare(row1.getGoalsDiff(), row2.getGoalsDiff());
            }
        };
        comparators.add(GOALS_DIFF_COMPARATOR);

        /* The comparator comparing goals shot. */
        final Comparator<StandingsContentProvider.StandingsDataRow> GOALS_FOR_COMPARATOR = new Comparator<StandingsContentProvider.StandingsDataRow>() {
            @Override
            public int compare(StandingsContentProvider.StandingsDataRow row1, StandingsContentProvider.StandingsDataRow row2) {
                return Integer.compare(row1.getGoalsFor(), row2.getGoalsFor());
            }
        };
        comparators.add(GOALS_FOR_COMPARATOR);

        /* The comparator comparing points. */
        final Comparator<StandingsContentProvider.StandingsDataRow> POINTS_COMPARATOR = new Comparator<StandingsContentProvider.StandingsDataRow>() {
            @Override
            public int compare(StandingsContentProvider.StandingsDataRow row1, StandingsContentProvider.StandingsDataRow row2) {
                return Integer.compare(row1.getPoints(), row2.getPoints());
            }
        };
        comparators.add(POINTS_COMPARATOR);
    }

    @Override
    public int compare(final Object o1, final Object o2) {
        // iterate through all comparators to specify the correct rankings
        for (final Comparator<StandingsContentProvider.StandingsDataRow> comparator : comparators) {
            // object 2 first because we need descending order
            final int order = comparator.compare((StandingsContentProvider.StandingsDataRow) o2, (StandingsContentProvider.StandingsDataRow) o1);
            if (order != 0) {
                return order;
            }
        }
        return 0;
    }
}
