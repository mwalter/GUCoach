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

package org.newinstance.gucoach.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.newinstance.gucoach.entity.Player;
import org.newinstance.gucoach.gui.PlayerDataRow;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains all player data.
 *
 * @author mwalter
 */
@Component
public class PlayerModel {

    /** Holds all players. */
    private ObservableList<PlayerDataRow> players = FXCollections.observableList(new ArrayList<PlayerDataRow>());

    /**
     * Returns all players.
     *
     * @return the observable list of players
     */
    public ObservableList<PlayerDataRow> getPlayers() {
        return players;
    }

    /**
     * Sets all players. Player entities are converted to player data rows.
     *
     * @param players the players to set
     */
    public void setPlayers(final List<Player> players) {
        this.players = convertToPlayerDataRows(players);
    }

    /**
     * Merges the data of the entities into data rows in order to use {@link javafx.collections.ObservableList}.
     *
     * @param players the player entities
     * @return the observable list of data rows
     */
    private ObservableList<PlayerDataRow> convertToPlayerDataRows(final List<Player> players) {
        final List<PlayerDataRow> playerDataRows = new ArrayList<PlayerDataRow>();
        for (final Player player : players) {
            final PlayerDataRow playerDataRow = new PlayerDataRow();
            playerDataRow.setAge(player.getPlayerStats().getAge());
            playerDataRow.setAssignments(player.getPlayerStats().getAssignments());
            playerDataRow.setBirthday(player.getBirthday());
            playerDataRow.setCountry(player.getCountry());
            playerDataRow.setEndurance(player.getPlayerStats().getEndurance());
            playerDataRow.setEnergy(player.getPlayerStats().getEnergy());
            playerDataRow.setExperience(player.getPlayerStats().getExperience());
            playerDataRow.setFirstName(player.getFirstName());
            playerDataRow.setForm(player.getPlayerStats().getForm());
            playerDataRow.setGoalsSeason(player.getPlayerStats().getGoalsSeason());
            playerDataRow.setGoalsTotal(player.getPlayerStats().getGoalsTotal());
            playerDataRow.setHeight(player.getHeight());
            playerDataRow.setId(player.getId());
            playerDataRow.setImportDate(player.getImportDate());
            playerDataRow.setLastName(player.getLastName());
            playerDataRow.setMarketValue(player.getPlayerStats().getMarketValue());
            playerDataRow.setNumber(player.getPlayerStats().getNumber());
            playerDataRow.setPersonality(player.getPersonality());
            playerDataRow.setPosition(player.getPlayerStats().getPosition());
            playerDataRow.setStrength(player.getPlayerStats().getAverageStrength());
            playerDataRow.setStrongFoot(player.getStrongFoot());
            playerDataRow.setRedCardsSeason(player.getPlayerStats().getRedCardsSeason());
            playerDataRow.setRedCardsTotal(player.getPlayerStats().getRedCardsTotal());
            playerDataRow.setSalary(player.getPlayerStats().getSalary());
            playerDataRow.setSkillGoalkeeping(player.getPlayerStats().getSkillGoalkeeping());
            playerDataRow.setSkillPassing(player.getPlayerStats().getSkillPassing());
            playerDataRow.setSkillPlaymaking(player.getPlayerStats().getSkillPlaymaking());
            playerDataRow.setSkillScoring(player.getPlayerStats().getSkillScoring());
            playerDataRow.setSkillTackling(player.getPlayerStats().getSkillTackling());
            playerDataRow.setTalent(player.getPlayerStats().getTalent());
            playerDataRow.setTalentLevel(player.getPlayerStats().getTalentLevel());
            playerDataRow.setTraining(player.getPlayerStats().getTraining());
            playerDataRow.setYellowCardsSeason(player.getPlayerStats().getYellowCardsSeason());
            playerDataRow.setYellowCardsTotal(player.getPlayerStats().getYellowCardsTotal());
            playerDataRows.add(playerDataRow);
        }
        return FXCollections.observableList(playerDataRows);
    }
}
