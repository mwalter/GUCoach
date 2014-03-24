/*
 * GUCoach - your personal coach for Goalunited (tm).
 * Licenced under General Public Licence v3 (GPLv3)
 * newInstance.org, 2012-2014
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

import javafx.application.Application;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests the methods of the {@link TextInputHelper}.
 *
 * @author mwalter
 */
public class TextInputHelperTest {

    @BeforeClass
    public static void initJavaFX() {
        final Thread thread = new Thread("JavaFX Unit Test Thread") {
            public void run() {
                Application.launch(JavaFXTestApp.class, "");
            }
        };
        thread.setDaemon(true);
        thread.start();
    }

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

    public static class JavaFXTestApp extends Application {

        @Override
        public void start(final Stage primaryStage) throws Exception {
            // do nothing
        }
    }

}
