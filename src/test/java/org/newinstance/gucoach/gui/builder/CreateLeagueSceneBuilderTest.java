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

package org.newinstance.gucoach.gui.builder;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests methods of class {@link CreateLeagueSceneBuilder}.
 *
 * @author mwalter
 */
public class CreateLeagueSceneBuilderTest {

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
    public void buildCreateLeagueSceneTest() {
        final Parent root = new CreateLeagueSceneBuilder().buildScene();
        Assert.assertNotNull(root);
        final ObservableList<Node> children = root.getChildrenUnmodifiable();
        // two VBoxes
        Assert.assertEquals(2, children.size());
        final Node tfMatchday = root.lookup("#idTfMatchday");
        Assert.assertNotNull(tfMatchday);
        final TextField matchday = (TextField) tfMatchday;
        final String date = "04.06.2012";
        matchday.setText(date);
        Assert.assertEquals(date, matchday.getText());
    }

    public static class JavaFXTestApp extends Application {

        @Override
        public void start(final Stage primaryStage) throws Exception {
            // do nothing
        }
    }
}
