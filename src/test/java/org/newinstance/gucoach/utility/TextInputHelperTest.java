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

import javafx.scene.control.TextField;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Tests the methods of the {@link TextInputHelper}.
 *
 * @author mwalter
 */
public class TextInputHelperTest {

    @Test
    public void addLengthListenerTest() {
        final TextField textField = new TextField();
        TextInputHelper.addLengthListener(textField, 10);
        textField.setText("1234567890");
        textField.setText("1234");
        textField.setText("1234567890123");
        Assert.assertNotNull(textField.getText());
        Assert.assertEquals(4, textField.getText().length());
        Assert.assertEquals("1234", textField.getText());
    }
}