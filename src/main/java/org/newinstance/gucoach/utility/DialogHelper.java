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

import javafx.scene.control.Alert;

/**
 * Provides helper methods for creating {@link Alert}s.
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
     * @param messageId the id of the message to display
     */
    public static Alert createWarningDialog(final MessageId messageId) {
        final String title = ResourceLoader.getMessage(MessageId.E001.getMessageKey());
        final String message = ResourceLoader.getMessage(messageId.getMessageKey());
        final Alert warningDialog = new Alert(Alert.AlertType.WARNING);
        warningDialog.setTitle(title);
        warningDialog.setContentText(message);
        return warningDialog;
    }
}
