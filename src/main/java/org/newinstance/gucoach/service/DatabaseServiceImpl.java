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

import org.apache.ibatis.session.SqlSession;
import org.newinstance.gucoach.model.Player;
import org.newinstance.gucoach.model.PlayerHistory;
import org.newinstance.gucoach.model.PlayerStats;

import java.util.Date;
import java.util.List;

/**
 * Implements all database services related to player data. DDL and finder statements do not need a commit.
 *
 * @author mwalter
 */
public class DatabaseServiceImpl implements DatabaseService {

    private static final String PLAYER_MAPPER = "org.newinstance.gucoach.mapper.PlayerMapper.";
    private static final String PLAYER_STATS_MAPPER = "org.newinstance.gucoach.mapper.PlayerStatsMapper.";
    private static final String PLAYER_HISTORY_MAPPER = "org.newinstance.gucoach.mapper.PlayerHistoryMapper.";

    /**
     * Returns the {@link SqlSession} instance.
     *
     * @return the {@code SqlSession} instance
     */
    private SqlSession getSqlSession() {
        return ConnectionFactory.getInstance().openSession();
    }

    @Override
    public void createTables() {
        final SqlSession session = getSqlSession();
        try {
            session.update(PLAYER_MAPPER + SqlStatementName.CREATE_TABLE_PLAYER);
            session.update(PLAYER_STATS_MAPPER + SqlStatementName.CREATE_TABLE_PLAYER_STATS);
            session.update(PLAYER_HISTORY_MAPPER + SqlStatementName.CREATE_TABLE_PLAYER_HISTORY);
        } finally {
            session.close();
        }
    }

    @Override
    public void deletePlayer(final Long playerId) {
        final SqlSession session = getSqlSession();
        try {
            session.delete(PLAYER_MAPPER + SqlStatementName.DELETE_PLAYER, playerId);
            session.commit();
        } finally {
            session.close();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Date> findAllImportDates() {
        final SqlSession session = getSqlSession();
        try {
            return session.selectList(PLAYER_HISTORY_MAPPER + SqlStatementName.FIND_ALL_IMPORT_DATES);
        } finally {
            session.close();
        }
    }

    @Override
    public Date findLatestImportDate() {
        final SqlSession session = getSqlSession();
        try {
            return (Date) session.selectOne(PLAYER_HISTORY_MAPPER + SqlStatementName.FIND_LATEST_IMPORT_DATE);
        } finally {
            session.close();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Player> findAllPlayers() {
        final SqlSession session = getSqlSession();
        try {
            return session.selectList(PLAYER_MAPPER + SqlStatementName.FIND_ALL_PLAYERS);
        } finally {
            session.close();
        }
    }

    @Override
    public Player findPlayerByPlayerId(final Long playerId) {
        final SqlSession session = getSqlSession();
        try {
            return (Player) session.selectOne(PLAYER_MAPPER + SqlStatementName.FIND_PLAYER_BY_PLAYER_ID, playerId);
        } finally {
            session.close();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PlayerHistory> findPlayerHistoryByPlayerId(final Long playerId) {
        final SqlSession session = getSqlSession();
        try {
            return session.selectList(PLAYER_HISTORY_MAPPER + SqlStatementName.FIND_PLAYER_HISTORY_BY_PLAYER_ID, playerId);
        } finally {
            session.close();
        }
    }

    @Override
    public PlayerStats findPlayerStatsByPlayerId(final Long playerId) {
        final SqlSession session = getSqlSession();
        try {
            return (PlayerStats) session.selectOne(PLAYER_STATS_MAPPER + SqlStatementName.FIND_PLAYER_STATS_BY_PLAYER_ID, playerId);
        } finally {
            session.close();
        }
    }

    @Override
    public void insertPlayer(final Player player) {
        final SqlSession session = getSqlSession();
        try {
            session.insert(PLAYER_MAPPER + SqlStatementName.INSERT_PLAYER, player);
            session.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void insertPlayerHistory(final PlayerHistory playerHistory) {
        final SqlSession session = getSqlSession();
        try {
            session.insert(PLAYER_HISTORY_MAPPER + SqlStatementName.INSERT_PLAYER_HISTORY, playerHistory);
            session.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void insertPlayerStats(final PlayerStats playerStats) {
        final SqlSession session = getSqlSession();
        try {
            session.insert(PLAYER_STATS_MAPPER + SqlStatementName.INSERT_PLAYER_STATS, playerStats);
            session.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void updatePlayerStats(final PlayerStats playerStats) {
        final SqlSession session = getSqlSession();
        try {
            session.update(PLAYER_STATS_MAPPER + SqlStatementName.UPDATE_PLAYER_STATS, playerStats);
            session.commit();
        } finally {
            session.close();
        }
    }
}
