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

package org.newinstance.gucoach.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

/**
 * Starts the GUCoach application.
 *
 * @author mwalter
 */
public class GUCoach extends Application {

    /** The application name. */
    public static final String APPLICATION_TITLE = "GUCoach";
    /** The layout of the main application window. */
    public static final String LAYOUT_WINDOW_MAIN = "fxml/main.fxml";

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
        stage.setTitle(APPLICATION_TITLE);

        // load main window layout and resources
        final Parent root = FXMLLoader.load(getClass().getResource(LAYOUT_WINDOW_MAIN), ResourceBundle.getBundle("ApplicationResources"));

        // create scene and display window
        final Scene scene = new Scene(root, 1300, 900);
        scene.getStylesheets().add("stylesheet.css");
        stage.setScene(scene);
        stage.show();
    }

}
