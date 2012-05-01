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

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextInputControl;

/**
 * Provides helper methods for text input.
 *
 * @author mwalter
 */
public final class TextInputHelper {

    /**
     * Adds a listener to the control which limits the input of characters to the given maximum length.
     *
     * @param control the text input control
     * @param maxLength the maximum number of characters allowed to input
     */
    public static void addLengthListener(final TextInputControl control, final int maxLength) {
        control.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observableValue, final String oldValue, final String newValue) {
                if (newValue.length() > maxLength) {
                    control.setText(oldValue);
                }
            }
        });
    }

    private TextInputHelper() {
        // hide constructor
    }
}
