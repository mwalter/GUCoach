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

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Control;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import org.newinstance.gucoach.gui.CountryValueFlagCell;
import org.newinstance.gucoach.gui.PlayerContentProvider;
import org.newinstance.gucoach.gui.PlayerDataRow;
import org.newinstance.gucoach.gui.SkillValueColorCell;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controls user interaction in the main application window and initialises player data.
 *
 * @author mwalter
 */
public class TeamController implements Initializable {

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
    private TextField playerName;
    @FXML
    private TextField playerAge;
    @FXML
    private TextField playerHeight;
    @FXML
    private TextField playerCountry;
    @FXML
    private TextField playerPersonality;
    @FXML
    private TextField playerBirthday;
    @FXML
    private TextField playerFoot;
    @FXML
    private TextField playerYellowCards;
    @FXML
    private TextField playerExperience;
    @FXML
    private TextField playerForm;
    @FXML
    private TextField playerEnergy;
    @FXML
    private TextField playerEndurance;
    @FXML
    private TextField playerAssignments;
    @FXML
    private TextField playerPosition;
    @FXML
    private TextField playerTalent;
    @FXML
    private TextField playerNumber;
    @FXML
    private TextField playerGoals;
    @FXML
    private TextField playerSalary;
    @FXML
    private TextField playerMarketValue;
    @FXML
    private TextField playerStrength;
    @FXML
    private TextField playerRedCards;
    @FXML
    private TextField playerGoalkeeping;
    @FXML
    private TextField playerTackling;
    @FXML
    private TextField playerPlaymaking;
    @FXML
    private TextField playerPassing;
    @FXML
    private TextField playerScoring;

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
        tableColumnCountry.setCellFactory(new Callback<TableColumn, TableCell>() {
            public TableCell call(final TableColumn param) {
                return new CountryValueFlagCell();
            }
        });
        tableColumnNumber.setCellValueFactory(new PropertyValueFactory<PlayerDataRow, String>("number"));
        tableColumnAge.setCellValueFactory(new PropertyValueFactory<PlayerDataRow, String>("age"));
        tableColumnStrength.setCellValueFactory(new PropertyValueFactory<PlayerDataRow, String>("strength"));
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

        tableViewPlayer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PlayerDataRow>() {

            @Override
            public void changed(ObservableValue<? extends PlayerDataRow> observable, PlayerDataRow oldValue, PlayerDataRow newValue) {
                fillPlayerDataIntoTextFields(newValue);
            }
        });
    }

    /**
     * Fills data about a selected player into the text fields in the player details section.
     *
     * @param playerData contains the data about the selected player
     */
    private void fillPlayerDataIntoTextFields(final PlayerDataRow playerData) {
        playerName.setText(playerData.getFirstName() + " " + playerData.getLastName());
        playerAge.setText(playerData.getAge().toString());
        playerHeight.setText(playerData.getHeight().toString());
        playerCountry.setText(playerData.getCountry().name());
        playerPersonality.setText(playerData.getPersonality());
        playerBirthday.setText(playerData.getBirthday());
        playerFoot.setText(playerData.getStrongFoot().getDescription());
        playerYellowCards.setText(playerData.getYellowCardsSeasonAndTotal());
        playerExperience.setText(playerData.getExperience().toString());
        playerForm.setText(playerData.getForm().toString());
        playerEnergy.setText(playerData.getEnergy().toString());
        playerEndurance.setText(playerData.getEndurance().toString());
        playerAssignments.setText(playerData.getAssignments().toString());
        playerPosition.setText(playerData.getPosition().name());
        playerTalent.setText(playerData.getTalent());
        playerNumber.setText(playerData.getNumber().toString());
        playerGoals.setText(playerData.getGoalsSeasonAndTotal());
        playerSalary.setText(playerData.getSalary().toString());
        // market value is not filled yet
        playerMarketValue.setText("");
        playerStrength.setText(playerData.getStrength().toString());
        playerRedCards.setText(playerData.getRedCardsSeasonAndTotal());
        playerGoalkeeping.setText(playerData.getSkillGoalkeeping().toString());
        playerTackling.setText(playerData.getSkillTackling().toString());
        playerPlaymaking.setText(playerData.getSkillPlaymaking().toString());
        playerPassing.setText(playerData.getSkillPassing().toString());
        playerScoring.setText(playerData.getSkillScoring().toString());
    }

}
