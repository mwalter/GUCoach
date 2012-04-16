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

package org.newinstance.gucoach.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * TODO document me
 *
 * @author mwalter
 */
public class LeagueController {

    /** The window title. */
    public static final String TITLE_CREATE_LEAGUE = "Create League";
    /** The layout of the main application window. */
    public static final String SCENE_CREATE_LEAGUE = "../fxml/windowCreateLeague.fxml";

    @FXML
    private BorderPane borderPane;

    public void showCreateLeagueWindow(final ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(SCENE_CREATE_LEAGUE), ResourceBundle.getBundle("ApplicationResources"));
        } catch (final IOException e) {
            e.printStackTrace();
        }

        final Scene scene = new Scene(root);
        final Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(TITLE_CREATE_LEAGUE);
        stage.show();
    }

    public void editFixture(final ActionEvent event) {

    }

    /**
     * Cancels the create league process and closes the window.
     *
     * @param event the event
     */
    public void closeWindow(final ActionEvent event) {
        final Stage stage = (Stage) borderPane.getScene().getWindow();
        stage.close();
    }
}
