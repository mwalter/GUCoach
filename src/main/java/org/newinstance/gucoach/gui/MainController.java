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

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Control;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.util.Callback;
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
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controls user interaction in the main application window and initialises player data.
 *
 * @author mwalter
 */
public class MainController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private TableView tableViewPlayer;
    @FXML
    private TableColumn tableColumnName;
    @FXML
    private TableColumn tableColumnCountry;
    @FXML
    private TableColumn tableColumnNumber;
    @FXML
    private TableColumn tableColumnAge;
    @FXML
    private TableColumn tableColumnStrength;
    @FXML
    private TableColumn tableColumnPosition;
    @FXML
    private TableColumn tableColumnExperience;
    @FXML
    private TableColumn tableColumnSkillGk;
    @FXML
    private TableColumn tableColumnSkillTk;
    @FXML
    private TableColumn tableColumnSkillPm;
    @FXML
    private TableColumn tableColumnSkillPa;
    @FXML
    private TableColumn tableColumnSkillSc;

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
        System.exit(0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void initialize(final URL location, final ResourceBundle resources) {
        final ObservableList<PlayerDataRow> playerData = PlayerContentProvider.getPlayerData();
        if (playerData.isEmpty()) {
            // TODO create internationalised no data message
            tableViewPlayer.setPlaceholder(null);
            return;
        }

        // TODO replace attributes with constant names
        tableColumnName.setCellValueFactory(new PropertyValueFactory<PlayerDataRow, String>("fullName"));
        tableColumnCountry.setCellValueFactory(new PropertyValueFactory<PlayerDataRow, String>("country"));
        tableColumnNumber.setCellValueFactory(new PropertyValueFactory<PlayerDataRow, String>("number"));
        tableColumnAge.setCellValueFactory(new PropertyValueFactory<PlayerDataRow, String>("age"));
        tableColumnStrength.setCellValueFactory(new PropertyValueFactory<PlayerDataRow, String>("averageStrength"));
        tableColumnPosition.setCellValueFactory(new PropertyValueFactory<PlayerDataRow, String>("position"));
        tableColumnExperience.setCellValueFactory(new PropertyValueFactory<PlayerDataRow, String>("experience"));
        tableColumnSkillGk.setCellValueFactory(new PropertyValueFactory<PlayerDataRow, String>("skillGoalkeeping"));
        tableColumnSkillGk.setCellFactory(new Callback<TableColumn, TableCell>() {
            public TableCell call(final TableColumn param) {
                return new SkillValueColorCell();
            }
        });
        tableColumnSkillTk.setCellValueFactory(new PropertyValueFactory<PlayerDataRow, String>("skillTackling"));
        tableColumnSkillTk.setCellFactory(new Callback<TableColumn, TableCell>() {
            public TableCell call(final TableColumn param) {
                return new SkillValueColorCell();
            }
        });
        tableColumnSkillPm.setCellValueFactory(new PropertyValueFactory<PlayerDataRow, String>("skillPlaymaking"));
        tableColumnSkillPm.setCellFactory(new Callback<TableColumn, TableCell>() {
            public TableCell call(final TableColumn param) {
                return new SkillValueColorCell();
            }
        });
        tableColumnSkillPa.setCellValueFactory(new PropertyValueFactory<PlayerDataRow, String>("skillPassing"));
        tableColumnSkillPa.setCellFactory(new Callback<TableColumn, TableCell>() {
            public TableCell call(final TableColumn param) {
                return new SkillValueColorCell();
            }
        });
        tableColumnSkillSc.setCellValueFactory(new PropertyValueFactory<PlayerDataRow, String>("skillScoring"));
        tableColumnSkillSc.setCellFactory(new Callback<TableColumn, TableCell>() {
            public TableCell call(final TableColumn param) {
                return new SkillValueColorCell();
            }
        });
        tableColumnName.getTableView().setItems(playerData);
        tableColumnName.getTableView().setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableColumnName.getTableView().setMinHeight(Control.USE_PREF_SIZE);
        tableColumnName.getTableView().setMaxWidth(Control.USE_PREF_SIZE);
    }

}