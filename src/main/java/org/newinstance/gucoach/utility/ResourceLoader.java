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

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Loads messages and resources used by the application with default locale.
 * TODO maybe improve error handling by catching possible exceptions like MissingResourceException
 *
 * @author mwalter
 */
public final class ResourceLoader {

    private static final String APPLICATION_MESSAGES = "ApplicationMessages";

    private static final String APPLICATION_RESOURCES = "ApplicationResources";

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
        final ResourceBundle bundle = ResourceBundle.getBundle(APPLICATION_MESSAGES);
        return MessageFormat.format(bundle.getString(key), params);
    }

    /**
     * Returns the resource value for the specified key.
     *
     * @param key the resource key
     * @return the resource value
     */
    public static String getResource(final String key) {
        final ResourceBundle bundle = ResourceBundle.getBundle(APPLICATION_RESOURCES);
        return bundle.getString(key);
    }
}
