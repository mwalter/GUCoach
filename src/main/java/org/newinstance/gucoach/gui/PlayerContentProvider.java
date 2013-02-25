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

package org.newinstance.gucoach.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import org.newinstance.gucoach.entity.Player;
import org.newinstance.gucoach.entity.PlayerStats;
import org.newinstance.gucoach.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates the content {@link TableView} containing all player data.
 *
 * @author mwalter
 */
@Component
public class PlayerContentProvider {

    /** The player service. */
    @Autowired
    private PlayerService playerService;

    /**
     * Returns all player data from the database.
     *
     * @return the player data
     */
    public ObservableList<PlayerDataRow> fetchPlayerData() {
        // make sure to initialise tables
        final List<PlayerDataRow> playerDataRows = new ArrayList<PlayerDataRow>();
        final List<Player> players = playerService.findAllPlayers();
        for (final Player player : players) {
            playerDataRows.add(convertToPlayerDataRow(player, player.getPlayerStats()));
        }

        return FXCollections.observableList(playerDataRows);
    }

    /**
     * Merges the data of the entity into a data row in order to use {@link ObservableList}.
     *
     * @param player the player entity
     * @param playerStats the player statistics entity
     * @return the merged data
     */
    private PlayerDataRow convertToPlayerDataRow(final Player player, final PlayerStats playerStats) {
        final PlayerDataRow playerDataRow = new PlayerDataRow();
        playerDataRow.setAge(playerStats.getAge());
        playerDataRow.setAssignments(playerStats.getAssignments());
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
        playerDataRow.setStrength(playerStats.getAverageStrength());
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
