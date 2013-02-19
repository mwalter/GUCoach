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

package org.newinstance.gucoach.gui;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.newinstance.gucoach.gui.controller.SpringFxmlLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Starts the GUCoach application.
 *
 * @author mwalter
 */
public class GUCoach extends Application {

    /** The application icon. */
    private static final String APPLICATION_ICON = "../../../../images/gucoach-icon.png";
    /** The application name. */
    private static final String APPLICATION_TITLE = "GUCoach - your personal coach for Goalunited";
    /** The layout of the main application window. */
    private static final String FXML_MAIN = "/fxml/main.fxml";
    /** The main stylesheets. */
    private static final String GUCOACH_STYLES = "gucoach.css";

    /**
     * Executes the application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) throws Exception {
        final ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/applicationContext.xml");
        final SpringFxmlLoader loader = new SpringFxmlLoader(context);
        final Parent root = (Parent) loader.load(FXML_MAIN);

        // create scene and display window
        final Scene scene = new Scene(root, 1300, 900);
        scene.getStylesheets().add(GUCOACH_STYLES);
        stage.setTitle(APPLICATION_TITLE);
        stage.getIcons().add(new Image(getClass().getResource(APPLICATION_ICON).toString()));
        stage.setScene(scene);
        stage.show();
    }

}
