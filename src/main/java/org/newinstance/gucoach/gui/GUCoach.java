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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.newinstance.gucoach.exception.ImportException;
import org.newinstance.gucoach.exception.ValidationException;
import org.newinstance.gucoach.service.ImportController;
import org.newinstance.gucoach.service.ImportControllerImpl;
import org.newinstance.gucoach.service.ImportService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Starts the GUCoach application.
 *
 * @author mwalter
 */
public class GUCoach extends Application {

    public static final String APPLICATION_TITLE = "GUCoach";

    /**
     * Executes the application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) {
        stage.setTitle(APPLICATION_TITLE);

        final BorderPane borderPane = new BorderPane();
        final MenuBar menuBar = new MenuBar();
        // --- Menu File
        final Menu menuFile = new Menu("File");
        final MenuItem importCsv = new MenuItem("Import CSV...");
        importCsv.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(final ActionEvent t) {
                final FileChooser fileChooser = new FileChooser();
                final File importFile = fileChooser.showOpenDialog(stage.getOwner());
                if (importFile == null) {
                    return;
                }

                final ImportController importController = new ImportControllerImpl();
                try {
                    importController.executeImport(new InputStreamReader(new FileInputStream(importFile), ImportService.FILE_ENCODING));
                    // TODO show error messages in status bar
                } catch (final FileNotFoundException e) {
                    e.printStackTrace();
                } catch (final ImportException e) {
                    e.printStackTrace();
                } catch (final ValidationException e) {
                    e.printStackTrace();
                } catch (final UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                // TODO reload table here to show new imported data
            }
        });
        final MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(final ActionEvent t) {
                System.exit(0);
            }
        });
        menuFile.getItems().addAll(importCsv, exit);
        // --- Menu Statistics
        final Menu menuStatistics = new Menu("Statistics");
        // --- Menu Help
        final Menu menuHelp = new Menu("Help");
        final MenuItem about = new MenuItem("About GUCoach");
        menuHelp.getItems().addAll(about);
        menuBar.getMenus().addAll(menuFile, menuStatistics, menuHelp);

        final HBox statusBar = new HBox(10);
        statusBar.setMinHeight(20);
        final Insets insets = new Insets(2, 0, 2, 2);
        statusBar.setPadding(insets);
        statusBar.getChildren().addAll(new Label("Ready."));
        final PlayerDataContent playerDataContent = new PlayerDataContent();
        final Node appContent = playerDataContent.createTableContent();
        borderPane.setTop(menuBar);
        borderPane.setCenter(appContent);
        borderPane.setBottom(statusBar);

        final Scene scene = new Scene(borderPane, 1024, 768);
        scene.setFill(Color.OLDLACE);

        stage.setScene(scene);
        stage.show();
    }

}
