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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import org.newinstance.gucoach.model.Player;
import org.newinstance.gucoach.model.PlayerStats;
import org.newinstance.gucoach.service.DatabaseService;
import org.newinstance.gucoach.service.DatabaseServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates the content {@link TableView} containing all player data.
 *
 * @author mwalter
 */
public class PlayerDataContent {

    private DatabaseService databaseService = new DatabaseServiceImpl();

    public TableView createTableContent() {
        // make sure to initialise tables
        databaseService.createTables();
        final List<PlayerDataRow> playerDataRows = new ArrayList<PlayerDataRow>();
        final List<Player> players = databaseService.findAllPlayers();
        for (final Player player : players) {
            final PlayerStats playerStats = databaseService.findPlayerStatsByPlayerId(player.getId());
            playerDataRows.add(convertToPlayerDataRow(player, playerStats));
        }

        final ObservableList<PlayerDataRow> playerData = FXCollections.observableArrayList(playerDataRows);

        final ColumnConstraints column1 = new ColumnConstraints();
        column1.setHalignment(HPos.LEFT);

        final TableView table = new TableView();
        final TableColumn numberCol = new TableColumn("Nr");
        numberCol.setCellValueFactory(new PropertyValueFactory<PlayerDataRow, String>("number"));
        final TableColumn countryCol = new TableColumn("Country");
        countryCol.setCellValueFactory(new PropertyValueFactory<PlayerDataRow, String>("country"));
        final TableColumn ageCol = new TableColumn("Age");
        ageCol.setCellValueFactory(new PropertyValueFactory<PlayerDataRow, String>("age"));
        final TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<PlayerDataRow, String>("firstName"));
        final TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<PlayerDataRow, String>("lastName"));
        final TableColumn averageStrengthCol = new TableColumn("Avg");
        averageStrengthCol.setCellValueFactory(new PropertyValueFactory<PlayerDataRow, String>("averageStrength"));
        final TableColumn positionCol = new TableColumn("Pos");
        positionCol.setCellValueFactory(new PropertyValueFactory<PlayerDataRow, String>("position"));
        final TableColumn experienceCol = new TableColumn("Exp");
        experienceCol.setCellValueFactory(new PropertyValueFactory<PlayerDataRow, String>("experience"));
        final TableColumn skillGoalkeepingCol = new TableColumn("Gk");
        skillGoalkeepingCol.setCellValueFactory(new PropertyValueFactory<PlayerDataRow, String>("skillGoalkeeping"));
        final TableColumn skillTacklingCol = new TableColumn("Tk");
        skillTacklingCol.setCellValueFactory(new PropertyValueFactory<PlayerDataRow, String>("skillTackling"));
        final TableColumn skillPlaymakingCol = new TableColumn("Pm");
        skillPlaymakingCol.setCellValueFactory(new PropertyValueFactory<PlayerDataRow, String>("skillPlaymaking"));
        final TableColumn skillPassingCol = new TableColumn("Pa");
        skillPassingCol.setCellValueFactory(new PropertyValueFactory<PlayerDataRow, String>("skillPassing"));
        final TableColumn skillScoringCol = new TableColumn("Sc");
        skillScoringCol.setCellValueFactory(new PropertyValueFactory<PlayerDataRow, String>("skillScoring"));

        table.setItems(playerData);
        table.getColumns().addAll(numberCol, countryCol, ageCol, firstNameCol, lastNameCol, averageStrengthCol, positionCol, experienceCol, skillGoalkeepingCol, skillTacklingCol, skillPlaymakingCol, skillPassingCol, skillScoringCol);

        return table;
    }

    /**
     * Merges the data of the model into a data row in order to use {@link ObservableList}.
     * @param player the player entity
     * @param playerStats the player statistics entity
     * @return the merged data
     */
    private PlayerDataRow convertToPlayerDataRow(final Player player, final PlayerStats playerStats) {
        final PlayerDataRow playerDataRow = new PlayerDataRow();
        playerDataRow.setAge(playerStats.getAge());
        playerDataRow.setAssignments(playerStats.getAssignments());
        playerDataRow.setAverageStrength(playerStats.getAverageStrength());
        playerDataRow.setBirthday(player.getBirthday());
        playerDataRow.setCountry(player.getCountry());
        playerDataRow.setEndurance(playerStats.getEndurance());
        playerDataRow.setEnergy(playerStats.getEnergy());
        playerDataRow.setExperience(playerStats.getExperience());
        playerDataRow.setFirstName(player.getFirstName());
        playerDataRow.setForm(playerStats.getForm());
        playerDataRow.setGoalsSeason(playerStats.getGoalsSeason());
        playerDataRow.setGoalsTotal(playerStats.getGoalsTotal());
        playerDataRow.setHeight(player.getHeight());
        playerDataRow.setId(player.getId());
        playerDataRow.setImportDate(player.getImportDate());
        playerDataRow.setLastName(player.getLastName());
        playerDataRow.setMarketValue(playerStats.getMarketValue());
        playerDataRow.setNumber(playerStats.getNumber());
        playerDataRow.setPersonality(player.getPersonality());
        playerDataRow.setPosition(playerStats.getPosition());
        playerDataRow.setStrongFoot(player.getStrongFoot());
        playerDataRow.setRedCardsSeason(playerStats.getRedCardsSeason());
        playerDataRow.setRedCardsTotal(playerStats.getRedCardsTotal());
        playerDataRow.setSalary(playerStats.getSalary());
        playerDataRow.setSkillGoalkeeping(playerStats.getSkillGoalkeeping());
        playerDataRow.setSkillPassing(playerStats.getSkillPassing());
        playerDataRow.setSkillPlaymaking(playerStats.getSkillPlaymaking());
        playerDataRow.setSkillScoring(playerStats.getSkillScoring());
        playerDataRow.setSkillTackling(playerStats.getSkillTackling());
        playerDataRow.setTalent(playerStats.getTalent());
        playerDataRow.setTalentLevel(playerStats.getTalentLevel());
        playerDataRow.setTraining(playerStats.getTraining());
        playerDataRow.setYellowCardsSeason(playerStats.getYellowCardsSeason());
        playerDataRow.setYellowCardsTotal(playerStats.getYellowCardsTotal());
        return playerDataRow;
    }
}
