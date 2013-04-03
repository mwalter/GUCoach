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

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newinstance.gucoach.entity.Player;
import org.newinstance.gucoach.gui.model.PlayerModel;
import org.newinstance.gucoach.utility.MessageId;
import org.newinstance.gucoach.utility.ResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Controls user interaction in the team tab pane.
 *
 * @author mwalter
 */
@Component
public class TeamController {

    /** The log4j logger. */
    private static final Logger LOGGER = LogManager.getLogger(TeamController.class.getName());

    @Autowired
    private PlayerModel playerModel;

    @FXML
    private TableView<Player> tableViewPlayer;
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

    @FXML
    public void initialize() {
        LOGGER.info("TeamController is accessing player model.");
        tableViewPlayer.setItems(playerModel.getPlayers());

        // if there are no players yet display import player data message
        if (tableViewPlayer.getItems().isEmpty()) {
            final Label message = new Label();
            message.setText(ResourceLoader.getMessage(MessageId.I001.getMessageKey()));
            tableViewPlayer.setPlaceholder(message);
        }

        // add listener for row selection
        tableViewPlayer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Player>() {

            @Override
            public void changed(final ObservableValue<? extends Player> observable, final Player oldValue, final Player newValue) {
                // only update fields if user selected a new row - prevents NPE if user clicks to sort a column
                if (newValue != null) {
                    fillPlayerDataIntoTextFields(newValue);
                }
            }
        });
    }

    /**
     * Fills data about a selected player into the text fields in the player details section.
     *
     * @param playerData contains the data about the selected player
     */
    private void fillPlayerDataIntoTextFields(final Player playerData) {
        playerName.setText(playerData.getFirstName() + " " + playerData.getLastName());
        playerAge.setText(playerData.getPlayerStats().getAge().toString());
        playerHeight.setText(playerData.getHeight().toString());
        playerCountry.setText(playerData.getCountry().name());
        playerPersonality.setText(playerData.getPersonality());
        playerBirthday.setText(playerData.getBirthday());
        playerFoot.setText(playerData.getStrongFoot().getDescription());
        playerYellowCards.setText(playerData.getPlayerStats().getYellowCardsSeasonAndTotal());
        playerExperience.setText(playerData.getPlayerStats().getExperience().toString());
        playerForm.setText(playerData.getPlayerStats().getForm().toString());
        playerEnergy.setText(playerData.getPlayerStats().getEnergy().toString());
        playerEndurance.setText(playerData.getPlayerStats().getEndurance().toString());
        playerAssignments.setText(playerData.getPlayerStats().getAssignments().toString());
        playerPosition.setText(playerData.getPlayerStats().getPosition().name());
        playerTalent.setText(playerData.getPlayerStats().getTalent());
        playerNumber.setText(playerData.getPlayerStats().getNumber().toString());
        playerGoals.setText(playerData.getPlayerStats().getGoalsSeasonAndTotal());
        playerSalary.setText(playerData.getPlayerStats().getSalary().toString());
        playerMarketValue.setText(playerData.getPlayerStats().getMarketValue() == null ? "" : playerData.getPlayerStats().getMarketValue().toString());
        playerStrength.setText(playerData.getPlayerStats().getAverageStrength().toString());
        playerRedCards.setText(playerData.getPlayerStats().getRedCardsSeasonAndTotal());
        playerGoalkeeping.setText(playerData.getPlayerStats().getSkillGoalkeeping().toString());
        playerTackling.setText(playerData.getPlayerStats().getSkillTackling().toString());
        playerPlaymaking.setText(playerData.getPlayerStats().getSkillPlaymaking().toString());
        playerPassing.setText(playerData.getPlayerStats().getSkillPassing().toString());
        playerScoring.setText(playerData.getPlayerStats().getSkillScoring().toString());
    }

}
