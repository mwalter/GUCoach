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
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.newinstance.gucoach.gui.PlayerContentProvider;
import org.newinstance.gucoach.gui.PlayerDataRow;
import org.newinstance.gucoach.utility.MessageId;
import org.newinstance.gucoach.utility.ResourceLoader;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controls user interaction in the team tab pane.
 *
 * @author mwalter
 */
public class TeamController implements Initializable {

    @FXML
    private TableView tableViewPlayer;
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
        // load player data and fill team table
        final ObservableList<PlayerDataRow> playerData = PlayerContentProvider.getPlayerData();
        tableViewPlayer.setItems(playerData);
        if (playerData.isEmpty()) {
            final Label message = new Label();
            message.setText(ResourceLoader.getMessage(MessageId.I001.getMessageKey()));
            tableViewPlayer.setPlaceholder(message);
            return;
        }

        double tableWidth = 0;
        final ObservableList<TableColumn> columns = tableViewPlayer.getColumns();
        for (final TableColumn column : columns) {
            tableWidth += column.getWidth();
        }
        // tableViewPlayer.setMinWidth(tableWidth);
        // tableViewPlayer.setMaxWidth(tableWidth);
        tableViewPlayer.setMinWidth(Control.USE_COMPUTED_SIZE);
        tableViewPlayer.setMaxWidth(Control.USE_COMPUTED_SIZE);

        // add listener for row selection
        tableViewPlayer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PlayerDataRow>() {

            @Override
            public void changed(final ObservableValue<? extends PlayerDataRow> observable, final PlayerDataRow oldValue, final PlayerDataRow newValue) {
                // only update fields if user selected a new row - prevents NPE if user clicks to sort a column
                if (newValue != null) {
                    fillPlayerDataIntoTextFields(newValue);
                }
            }
        });

        // add listener for player list changes
        playerData.addListener(new ListChangeListener<PlayerDataRow>() {

            @Override
            public void onChanged(final Change<? extends PlayerDataRow> change) {
                tableViewPlayer.setItems(change.getList());
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
