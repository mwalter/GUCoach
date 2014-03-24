/*
 * GUCoach - your personal coach for Goalunited (tm).
 * Licensed under General Public License v3 (GPLv3)
 * newInstance.org, 2014
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

import org.controlsfx.dialog.Dialogs;

/**
 * Provides helper methods for creating {@link Dialogs}.
 *
 * @author mwalter
 */
public final class DialogHelper {

    private DialogHelper() {
        // hide constructor
    }

    /**
     * Creates a warning dialog without masthead section.
     *
     * @param title the title to display
     * @param message the message to display
     */
    public static void createWarningDialog(final String title, final String message) {
        Dialogs.create().title(title).message(message).masthead(null).showWarning();
    }
}
