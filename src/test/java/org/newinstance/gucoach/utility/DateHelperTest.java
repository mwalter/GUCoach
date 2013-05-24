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

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Tests the methods of the {@link DateHelper}.
 *
 * @author mwalter
 */
public class DateHelperTest {

    @Test
    public void formatDateTest() {
        final String result = DateHelper.formatDate(new Date());
        Assert.assertNotNull(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void formatInvalidDate() {
        DateHelper.formatDate(null);
    }

    @Test
    public void parseDate() throws Exception {
        // keep test independent from execution environment
        Locale.setDefault(Locale.GERMANY);
        final Date result = DateHelper.parseDate("02.05.2012");
        Assert.assertNotNull(result);
    }

    @Test(expected = ParseException.class)
    public void parseInvalidDate() throws Exception {
        // keep test independent from execution environment
        Locale.setDefault(Locale.US);
        DateHelper.parseDate("02.05.2012");
    }

    @Test
    public void convertToCalendar() throws Exception {
        final Date date = DateHelper.parseDate("24.05.2013");
        final Calendar result = DateHelper.convertToCalendar(date);
        Assert.assertNotNull(result);

        final String stringRepresentation = result.toString();
        Assert.assertTrue(StringUtils.contains(stringRepresentation, "DAY_OF_MONTH=24"));
        Assert.assertTrue(StringUtils.contains(stringRepresentation, "MONTH=4"));
        Assert.assertTrue(StringUtils.contains(stringRepresentation, "YEAR=2013"));
    }
}
