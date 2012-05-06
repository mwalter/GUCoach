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
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.newinstance.gucoach.exception.ImportException;
import org.newinstance.gucoach.exception.ValidationException;
import org.newinstance.gucoach.service.ImportController;
import org.newinstance.gucoach.service.ImportControllerImpl;

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

        final ImportController importController = new ImportControllerImpl();
        try {
            importController.executeImport(importFile);
            // TODO show error messages in status bar
        } catch (final ImportException e) {
            e.printStackTrace();
        } catch (final ValidationException e) {
            e.printStackTrace();
        }
        // TODO reload table here to show new imported data
    }

    @FXML
    protected void handleMenuItemExitAction(final ActionEvent event) {
        final Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

}
