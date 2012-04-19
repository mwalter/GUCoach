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
import org.newinstance.gucoach.service.ImportService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Controls user interaction in the main application window and initialises player data.
 *
 * @author mwalter
 */
public class MainController {

    @FXML
    private BorderPane borderPane;

    @FXML
    protected void handleMenuItemImportCsvAction(final ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();
        final File importFile = fileChooser.showOpenDialog(borderPane.getScene().getWindow());
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

    @FXML
    protected void handleMenuItemExitAction(final ActionEvent event) {
        final Stage stage = (Stage) borderPane.getScene().getWindow();
        stage.close();
    }

}
