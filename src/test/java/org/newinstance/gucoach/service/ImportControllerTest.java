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

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.newinstance.gucoach.exception.ValidationException;
import org.newinstance.gucoach.model.Player;
import org.newinstance.gucoach.model.PlayerHistory;
import org.newinstance.gucoach.model.PlayerStats;

import java.io.File;
import java.util.List;

/**
 * Tests the methods of the {@link ImportController}.
 *
 * @author mwalter
 */
public class ImportControllerTest {

    private static final String SAMPLE_IMPORT_FILE = "src/test/resources/gu_2011-11-20_team.csv";
    private static final String SAMPLE_IMPORT_FILE_UPDATE = "src/test/resources/gu_2011-11-21_team_update.csv";
    private static DatabaseService databaseService;

    @BeforeClass
    public static void init() {
        databaseService = new DatabaseServiceImpl();
        databaseService.createTables();
    }

    @Before
    public void setUp() {
        // make sure database tables are empty
        final List<Player> players = databaseService.findAllPlayers();
        for (final Player player : players) {
            databaseService.deletePlayer(player.getId());
        }
    }

    @Test
    public void executeDeleteTest() {
        List<Player> players = databaseService.findAllPlayers();
        for (final Player player : players) {
            databaseService.deletePlayer(player.getId());
        }
        players = databaseService.findAllPlayers();
        Assert.assertNotNull(players);
        Assert.assertTrue(players.isEmpty());
    }
    
    @Test
    public void executeImportTest() throws Exception {
        ImportController importController = new ImportControllerImpl();
        importController.executeImport(new File(SAMPLE_IMPORT_FILE));
        final List<Player> players = databaseService.findAllPlayers();
        Assert.assertFalse(players.isEmpty());
        for (final Player player : players) {
            final PlayerStats playerStats = databaseService.findPlayerStatsByPlayerId(player.getId());
            Assert.assertNotNull(playerStats);
            Assert.assertEquals(player.getId(), playerStats.getPlayerId());
            final List<PlayerHistory> playerHistoryList = databaseService.findPlayerHistoryByPlayerId(player.getId());
            Assert.assertNotNull(playerHistoryList);
            Assert.assertFalse(playerHistoryList.isEmpty());
        }
    }

    @Test(expected = ValidationException.class)
    public void executeImportFileAlreadyImportedTest() throws Exception {
        ImportController importController = new ImportControllerImpl();
        importController.executeImport(new File(SAMPLE_IMPORT_FILE));
        // now import same file a second time
        try {
            importController = new ImportControllerImpl();
            importController.executeImport(new File(SAMPLE_IMPORT_FILE));
        } catch (final ValidationException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Test
    public void executeImportUpdateTest() throws Exception {
        ImportController importController = new ImportControllerImpl();
        importController.executeImport(new File(SAMPLE_IMPORT_FILE));
        // now import new file to update player data
        importController = new ImportControllerImpl();
        importController.executeImport(new File(SAMPLE_IMPORT_FILE_UPDATE));
        final Player deletedPlayer = databaseService.findPlayerByPlayerId(4848870L);
        Assert.assertNull(deletedPlayer);
    }
}
