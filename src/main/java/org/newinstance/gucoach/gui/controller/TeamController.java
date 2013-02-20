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

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.newinstance.gucoach.gui.PlayerContentProvider;
import org.newinstance.gucoach.gui.PlayerDataRow;
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

    @Autowired
    private PlayerContentProvider playerContentProvider;

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

    //@Override
    //@SuppressWarnings("unchecked")
    //public void initialize(final URL location, final ResourceBundle resources) {
    @FXML
    public void initialize() {
        tableViewPlayer.setItems(playerContentProvider.fetchPlayerData());

        // if there are no players yet display import player data message
        if (tableViewPlayer.getItems().isEmpty()) {
            final Label message = new Label();
            message.setText(ResourceLoader.getMessage(MessageId.I001.getMessageKey()));
            tableViewPlayer.setPlaceholder(message);
        }

        // add listener for player list changes
        tableViewPlayer.getItems().addListener(new ListChangeListener<PlayerDataRow>() {

            @Override
            public void onChanged(final Change<? extends PlayerDataRow> change) {
                tableViewPlayer.setItems(change.getList());
            }
        });

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

    /**
     * Clears and sets the list of players.
     *
     * @param list the list of players to set
     */
    @SuppressWarnings("unchecked")
    public void setPlayerData(final ObservableList<PlayerDataRow> list) {
        tableViewPlayer.getItems().setAll(list);
    }
}
