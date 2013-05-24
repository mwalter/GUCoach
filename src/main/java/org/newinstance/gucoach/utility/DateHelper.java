/*
 * GUCoach - your personal coach for Goalunited (tm).
 * Licenced under General Public Licence v3 (GPLv3)
 * newInstance.org, 2012-2013
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

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Provides helper methods for date formatting.
 *
 * @author mwalter
 */
public final class DateHelper {

    private DateHelper() {
        // hide constructor
    }

    /**
     * Returns a date in a readable format. Time is not displayed.
     *
     * @param date the date to format
     * @return the date value as medium formatted string
     * @see DateFormat
     */
    public static String formatDate(final Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Date must not be null.");
        }

        final DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        return df.format(date.getTime());
    }

    /**
     * Parses text to produce a date.
     *
     * @param text the text to parse
     * @return the date parsed from the string
     * @throws ParseException if an error occurs during parsing
     */
    public static Date parseDate(final String text) throws ParseException {
        final DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        return df.parse(text);
    }

    /**
     * Converts a date to a calendar object.
     *
     * @param date the date to convert
     * @return the calendar object
     */
    public static Calendar convertToCalendar(final Date date) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

}
