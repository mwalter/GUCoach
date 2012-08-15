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

package org.newinstance.gucoach.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.newinstance.gucoach.exception.ImportException;
import org.newinstance.gucoach.exception.ValidationException;
import org.newinstance.gucoach.gui.PlayerContentProvider;
import org.newinstance.gucoach.gui.builder.CreateLeagueSceneBuilder;
import org.newinstance.gucoach.service.ImportController;
import org.newinstance.gucoach.service.ImportControllerImpl;
import org.newinstance.gucoach.utility.PersistenceHelper;
import org.newinstance.gucoach.utility.ResourceLoader;

import java.io.File;

/**
 * Controls user interaction in the main application window.
 *
 * @author mwalter
 */
public class MainController {

    @FXML
    private BorderPane root;

    @FXML
    protected void handleMenuItemImportCsvAction(final ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();
        final File importFile = fileChooser.showOpenDialog(root.getScene().getWindow());
        if (importFile == null) {
            return;
        }

        final ImportController importController = new ImportControllerImpl(PersistenceHelper.getInstance().createEntityManager());
        try {
            importController.executeImport(importFile);
            // TODO show error messages in status bar
        } catch (final ImportException e) {
            e.printStackTrace();
        } catch (final ValidationException e) {
            e.printStackTrace();
        }

        // update team table after import to show new player data
        ControllerProvider.getInstance().getTeamController().setPlayerData(PlayerContentProvider.getPlayerData());
    }

    @FXML
    protected void handleMenuItemExitAction(final ActionEvent event) {
        final Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    /**
     * Builds and shows the dialogue to create a new league.
     *
     * @param event the action event
     */
    @FXML
    protected void showCreateLeagueWindow(final ActionEvent event) {
        // build dialogue with builder
        final Parent root = new CreateLeagueSceneBuilder().buildScene();
        final Scene scene = new Scene(root);
        scene.getStylesheets().add("stylesheet.css");
        final Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(ResourceLoader.getResource("label.title.createLeague"));
        stage.show();
    }
}
