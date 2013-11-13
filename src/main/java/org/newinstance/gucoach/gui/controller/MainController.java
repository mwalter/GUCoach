/*
 * GUCoach - your personal coach for Goalunited (tm).
 * Licensed under General Public License v3 (GPLv3)
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

import java.io.File;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
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
import org.newinstance.gucoach.entity.Fixture;
import org.newinstance.gucoach.exception.ImportException;
import org.newinstance.gucoach.exception.ValidationException;
import org.newinstance.gucoach.gui.model.LeagueModel;
import org.newinstance.gucoach.gui.model.TeamModel;
import org.newinstance.gucoach.service.FixtureService;
import org.newinstance.gucoach.service.ImportController;
import org.newinstance.gucoach.utility.ResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Controls user interaction in the main application window.
 *
 * @author mwalter
 */
@Component
public class MainController {

    /** The log4j logger. */
    private static final Logger LOGGER = LogManager.getLogger(MainController.class.getName());

    @Autowired
    private SpringFxmlLoader loader;

    @Autowired
    private ImportController importController;

    @Autowired
    private FixtureService fixtureService;

    @Autowired
    private LeagueModel leagueModel;

    @Autowired
    private TeamModel teamModel;

    @FXML
    private BorderPane root;

    @FXML
    private TabPane tabPaneTabs;

    @FXML
    private VBox vBoxWelcomeMessage;

    @FXML
    public void initialize() {
        vBoxWelcomeMessage.setVisible(false);

        // player tab
        // if there is player data available add team tab
        if (!teamModel.getPlayers().isEmpty()) {
            LOGGER.info("Loading tabTeam.fxml.");
            final Tab tabTeam = (Tab) loader.load("/fxml/tabTeam.fxml");
            tabPaneTabs.getTabs().add(tabTeam);
        }
        // league tab
        leagueModel.setFixtures(fixtureService.findAll());
        // if there is fixture data available add fixture tab
        if (!leagueModel.getFixtures().isEmpty()) {
            LOGGER.info("Loading tabLeague.fxml.");
            final Tab tabLeague = (Tab) loader.load("/fxml/tabLeague.fxml");
            tabPaneTabs.getTabs().add(tabLeague);
        }

        // if we have more than a tab select the team tab for default
        if (tabPaneTabs.getTabs().size() > 1 && getTabWithName("team") != null) {
            tabPaneTabs.getSelectionModel().select(getTabWithName("team"));
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

        // use task to import player data
        final Task<Void> importPlayerTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    importController.executeImport(importFile);
                } catch (final ImportException ie) {
                    LOGGER.error("Error importing file {}.", importFile.getName(), ie);
                    // TODO display error message
                } catch (final ValidationException ve) {
                    LOGGER.error("Error validating file {}.", importFile.getName(), ve);
                    // TODO display error message
                }
                return null;
            }
        };

        importPlayerTask.stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(final ObservableValue<? extends Worker.State> source, final Worker.State oldState, final Worker.State newState) {
                if (newState.equals(Worker.State.SUCCEEDED)) {
                    LOGGER.info("Players imported successfully.");
                    // update team table after import to show new player data
                    teamModel.updatePlayers();

                    // if tab pane was empty load tab team
                    if (getTabWithName("team") == null) {
                        LOGGER.info("Loading tabTeam.fxml.");
                        final Tab tabTeam = (Tab) loader.load("/fxml/tabTeam.fxml");
                        tabPaneTabs.getTabs().add(tabTeam);
                        tabPaneTabs.getSelectionModel().select(tabTeam);
                    }

                    // if welcome message is visible hide message and show content instead
                    if (vBoxWelcomeMessage.isVisible()) {
                        vBoxWelcomeMessage.setVisible(false);
                        tabPaneTabs.setVisible(true);
                    }
                }
                if (newState.equals(Worker.State.FAILED)) {
                    LOGGER.info("Importing players failed.");
                    // TODO show error message
                }
            }
        });

        new Thread(importPlayerTask).start();
    }

    /**
     * Closes the application.
     *
     * @param event the action event
     */
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

    public void handleLeagueUpdate() {
        final ObservableList<Fixture> fixtures = leagueModel.getFixtures();
        LOGGER.info("Loading tabLeague.fxml.");
        final Tab tabLeague = (Tab) loader.load("/fxml/tabLeague.fxml");
        tabPaneTabs.getTabs().add(tabLeague);
        tabPaneTabs.getSelectionModel().select(tabLeague);
    }

    /**
     * Returns the tab with the given id.
     *
     * @param tabId the id of the tab to find
     * @return the tab or <code>null</code>, if there is no tab with the id
     */
    private Tab getTabWithName(final String tabId) {
        for (final Tab tab : tabPaneTabs.getTabs()) {
            if (tabId.equals(tab.getId())) {
                return tab;
            }
        }
        return null;
    }
}
