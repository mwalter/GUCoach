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

import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;
import java.util.MissingResourceException;

/**
 * Tests the methods of the {@link ResourceLoader}.
 *
 * @author mwalter
 */
public class ResourceLoaderTest {
    
    @Test
    public void getMessageTest() {
        Locale.setDefault(Locale.ENGLISH);
        final String message = ResourceLoader.getMessage(MessageId.E001.getMessageKey());
        Assert.assertNotNull(message);
        Assert.assertEquals("Error initialising Mybatis configuration.", message);
    }

    @Test
    public void getMessageWithParameterTest() {
        Locale.setDefault(Locale.ENGLISH);
        final String message = ResourceLoader.getMessage(MessageId.V002.getMessageKey(), "Messi");
        Assert.assertNotNull(message);
        Assert.assertEquals("Could not find data for player Messi.", message);
    }

    @Test
    public void getResourceTest() {
        Locale.setDefault(Locale.ENGLISH);
        final String value = ResourceLoader.getResource("label.title.createLeague");
        Assert.assertNotNull(value);
        Assert.assertEquals("Create League", value);
    }

    @Test(expected = MissingResourceException.class)
    public void getMissingResource() {
        Locale.setDefault(Locale.ENGLISH);
        ResourceLoader.getResource("foobar");
    }
}
