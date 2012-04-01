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

package org.newinstance.gucoach.service;

import org.newinstance.gucoach.model.Match;
import org.newinstance.gucoach.model.Player;
import org.newinstance.gucoach.model.PlayerHistory;
import org.newinstance.gucoach.model.PlayerStats;
import org.newinstance.gucoach.model.Team;

import java.util.Date;
import java.util.List;

/**
 * The database service provides methods for database access.
 *
 * @author mwalter
 */
public interface DatabaseService {

    /** Creates all required database tables if they do not exist. */
    public void createTables();

    /** Deletes all matches from the database. */
    public void deleteAllMatches();

    /** Deletes all matches from the database. */
    public void deleteAllTeams();

    /**
     * Deletes the player with the id from the database.
     *
     * @param playerId the id of the player to delete
     */
    public void deletePlayer(final Long playerId);

    /** Deletes all database tables. */
    public void deleteTables();

    /**
     * Returns all dates of past imports.
     *
     * @return the dates
     */
    public List<Date> findAllImportDates();

    /**
     * Returns all matches from the database.
     *
     * @return the list of matches
     */
    public List<Match> findAllMatches();

    /**
     * Returns all players from the database.
     *
     * @return the list of players
     */
    public List<Player> findAllPlayers();

    /**
     * Returns all teams from the database.
     *
     * @return the list of teams
     */
    public List<Team> findAllTeams();

    /**
     * Returns the date of the latest (newest) import.
     *
     * @return the date
     */
    public Date findLatestImportDate();

    /**
     * Returns a players from the database.
     *
     * @param playerId the primary key of the player
     * @return the player
     */
    public Player findPlayerByPlayerId(final Long playerId);

    /**
     * Returns all players histories about a player from the database.
     *
     * @param playerId the primary key of the player to get the history from
     * @return the list of player histories
     */
    public List<PlayerHistory> findPlayerHistoryByPlayerId(final Long playerId);

    /**
     * Returns player statistics about a player from the database.
     *
     * @param playerId the primary key of the player to get statistics from
     * @return the player statistics
     */
    public PlayerStats findPlayerStatsByPlayerId(final Long playerId);

    /**
     * Inserts a new match into the database.
     *
     * @param match the match to insert
     */
    public void insertMatch(final Match match);

    /**
     * Inserts a new player into the database.
     *
     * @param player the player to insert
     */
    public void insertPlayer(final Player player);

    /**
     * Inserts a new player history into the database.
     *
     * @param playerHistory the player history to insert
     */
    public void insertPlayerHistory(final PlayerHistory playerHistory);

    /**
     * Inserts player statistics into the database.
     *
     * @param playerStats the player statistics to insert
     */
    public void insertPlayerStats(final PlayerStats playerStats);

    /**
     * Inserts a new team into the database.
     *
     * @param team the team to insert
     */
    public void insertTeam(final Team team);

    /**
     * Updates the match.
     *
     * @param match the match to update
     */
    public void updateMatch(final Match match);

    /**
     * Updates player statistics.
     *
     * @param playerStats the player statistics to update
     */
    public void updatePlayerStats(final PlayerStats playerStats);

    /**
     * Updates the team.
     *
     * @param team the team to update
     */
    public void updateTeam(final Team team);
}
