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

import java.text.ParseException;
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
}
