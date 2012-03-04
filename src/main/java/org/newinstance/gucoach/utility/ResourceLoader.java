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

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Loads messages used by the application. Default language is english. Other languages are not supported yet.
 *
 * @author mwalter
 */
public final class ResourceLoader {

    private static final String APPLICATION_MESSAGES = "ApplicationMessages";

    private ResourceLoader() {
        // hide constructor
    }

    /**
     * Returns the message for the given key in the default language.
     *
     * @param key the key of the message to load
     * @param params optional string parameters to insert into the message string
     * @return the message
     */
    public static String getMessage(final String key, final Object... params) {
        final ResourceBundle bundle = ResourceBundle.getBundle(APPLICATION_MESSAGES, Locale.ENGLISH);
        return MessageFormat.format(bundle.getString(key), params);
    }

}
