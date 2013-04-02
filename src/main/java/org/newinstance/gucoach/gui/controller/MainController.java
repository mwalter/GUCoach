/*
 * GUCoach - your personal coach for Goalunited (tm).
 * Licensed under General Public Licence v3 (GPLv3)
 * newInstance.org, 2012-2013
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

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newinstance.gucoach.exception.ImportException;
import org.newinstance.gucoach.exception.ValidationException;
import org.newinstance.gucoach.gui.model.PlayerModel;
import org.newinstance.gucoach.service.ImportController;
import org.newinstance.gucoach.service.PlayerService;
import org.newinstance.gucoach.utility.ResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Controls user interaction in the main application window.
 *
 * @author mwalter
 */
@Component
public class MainController {

    private static final Logger logger = LogManager.getLogger(MainController.class.getName());

    @Autowired
    private SpringFxmlLoader loader;

    @Autowired
    private ImportController importController;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private PlayerModel playerModel;

    @FXML
    private BorderPane root;

    @FXML
    private TabPane tabPaneTabs;

    @FXML
    private VBox vBoxWelcomeMessage;

    @FXML
    public void initialize() {
        vBoxWelcomeMessage.setVisible(false);
        playerModel.setPlayers(playerService.findAllPlayers());
        // if there is player data available add team tab
        if (!playerModel.getPlayers().isEmpty()) {
            final Tab tabTeam = (Tab) loader.load("/fxml/tabTeam.fxml");
            tabPaneTabs.getTabs().add(tabTeam);
        }
        // if there is no data at all show welcome message instead of tab pane
        if (tabPaneTabs.getTabs().isEmpty()) {
            tabPaneTabs.setVisible(false);
            vBoxWelcomeMessage.setVisible(true);
        }
    }

    @FXML
    protected void handleMenuItemImportCsvAction(final ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();
        final File importFile = fileChooser.showOpenDialog(root.getScene().getWindow());
        if (importFile == null) {
            return;
        }

        try {
            importController.executeImport(importFile);
            // TODO show error messages in status bar
        } catch (final ImportException e) {
            e.printStackTrace();
        } catch (final ValidationException e) {
            e.printStackTrace();
        }

        // update team table after import to show new player data
        logger.info("Updating player model.");
        playerModel.setPlayers(playerService.findAllPlayers());

        // if tab pane was empty load tab team
        if (tabPaneTabs.getTabs().isEmpty()) {
            logger.info("Loading tabTeam.fxml.");
            final Tab tabTeam = (Tab) loader.load("/fxml/tabTeam.fxml");
            tabPaneTabs.getTabs().add(tabTeam);
        }

        // if welcome message is visible hide message and show content instead
        if (vBoxWelcomeMessage.isVisible()) {
            vBoxWelcomeMessage.setVisible(false);
            tabPaneTabs.setVisible(true);
        }
    }

    @FXML
    protected void handleMenuItemExitAction(final ActionEvent event) {
        Platform.exit();
    }

    /**
     * Builds and shows the dialogue to create a new league.
     *
     * @param event the action event
     */
    @FXML
    protected void showCreateLeagueWindow(final ActionEvent event) {
        final Parent window = (Parent) loader.load("/fxml/windowCreateLeague.fxml");
        final Scene scene = new Scene(window);
        scene.getStylesheets().add("gucoach.css");
        final Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(ResourceLoader.getResource("label.title.createLeague"));
        stage.show();
    }
}
