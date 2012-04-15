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
    public static final String CREATE_TABLE_FIXTURE = "createTableFixture";
    public static final String CREATE_TABLE_PLAYER = "createTablePlayer";
    public static final String CREATE_TABLE_PLAYER_HISTORY = "createTablePlayerHistory";
    public static final String CREATE_TABLE_PLAYER_STATS = "createTablePlayerStats";
    public static final String CREATE_TABLE_TEAM = "createTableTeam";

    // --- DELETE ---
    public static final String DELETE_ALL_FIXTURES = "deleteAllFixtures";
    public static final String DELETE_ALL_TEAMS = "deleteAllTeams";
    public static final String DELETE_PLAYER = "deletePlayer";

    // --- FIND ---
    public static final String FIND_ALL_IMPORT_DATES = "findAllImportDates";
    public static final String FIND_ALL_FIXTURES = "findAllFixtures";
    public static final String FIND_ALL_PLAYERS = "findAllPlayers";
    public static final String FIND_ALL_TEAMS = "findAllTeams";
    public static final String FIND_LATEST_IMPORT_DATE = "findLatestImportDate";
    public static final String FIND_PLAYER_BY_PLAYER_ID = "findPlayerByPlayerId";
    public static final String FIND_PLAYER_HISTORY_BY_PLAYER_ID = "findPlayerHistoryByPlayerId";
    public static final String FIND_PLAYER_STATS_BY_PLAYER_ID = "findPlayerStatsByPlayerId";

    // --- INSERT ---
    public static final String INSERT_FIXTURE = "insertFixture";
    public static final String INSERT_PLAYER = "insertPlayer";
    public static final String INSERT_PLAYER_HISTORY = "insertPlayerHistory";
    public static final String INSERT_PLAYER_STATS = "insertPlayerStats";
    public static final String INSERT_TEAM = "insertTeam";

    // --- UPDATE ---
    public static final String UPDATE_FIXTURE = "updateFixture";
    public static final String UPDATE_PLAYER_STATS = "updatePlayerStats";
    public static final String UPDATE_TEAM = "updateTeam";

    // --- DROP ---
    public static final String DROP_TABLE_FIXTURE = "dropTableFixture";
    public static final String DROP_TABLE_PLAYER = "dropTablePlayer";
    public static final String DROP_TABLE_PLAYER_HISTORY = "dropTablePlayerHistory";
    public static final String DROP_TABLE_PLAYER_STATS = "dropTablePlayerStats";
    public static final String DROP_TABLE_TEAM = "dropTableTeam";

}
