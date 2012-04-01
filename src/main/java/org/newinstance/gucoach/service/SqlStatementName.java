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

/**
 * Sql statement names for all database operations. The names map to the sql statements in the Mybatis mapping files.
 *
 * @author mwalter
 */
public final class SqlStatementName {

    // --- CREATE ---
    public static final String CREATE_TABLE_MATCH = "createTableMatch";
    public static final String CREATE_TABLE_PLAYER = "createTablePlayer";
    public static final String CREATE_TABLE_PLAYER_HISTORY = "createTablePlayerHistory";
    public static final String CREATE_TABLE_PLAYER_STATS = "createTablePlayerStats";

    // --- DELETE ---
    public static final String DELETE_ALL_MATCHES = "deleteAllMatches";
    public static final String DELETE_PLAYER = "deletePlayer";

    // --- FIND ---
    public static final String FIND_ALL_IMPORT_DATES = "findAllImportDates";
    public static final String FIND_ALL_MATCHES = "findAllMatches";
    public static final String FIND_ALL_PLAYERS = "findAllPlayers";
    public static final String FIND_LATEST_IMPORT_DATE = "findLatestImportDate";
    public static final String FIND_PLAYER_BY_PLAYER_ID = "findPlayerByPlayerId";
    public static final String FIND_PLAYER_HISTORY_BY_PLAYER_ID = "findPlayerHistoryByPlayerId";
    public static final String FIND_PLAYER_STATS_BY_PLAYER_ID = "findPlayerStatsByPlayerId";

    // --- INSERT ---
    public static final String INSERT_MATCH = "insertMatch";
    public static final String INSERT_PLAYER = "insertPlayer";
    public static final String INSERT_PLAYER_HISTORY = "insertPlayerHistory";
    public static final String INSERT_PLAYER_STATS = "insertPlayerStats";

    // --- UPDATE ---
    public static final String UPDATE_MATCH = "updateMatch";
    public static final String UPDATE_PLAYER_STATS = "updatePlayerStats";

    // --- DROP ---
    public static final String DROP_TABLE_MATCH = "dropTableMatch";
    public static final String DROP_TABLE_PLAYER = "dropTablePlayer";
    public static final String DROP_TABLE_PLAYER_HISTORY = "dropTablePlayerHistory";
    public static final String DROP_TABLE_PLAYER_STATS = "dropTablePlayerStats";

}
