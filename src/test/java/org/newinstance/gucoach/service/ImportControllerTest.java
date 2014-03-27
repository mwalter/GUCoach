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

package org.newinstance.gucoach.service;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.newinstance.gucoach.base.BaseTest;
import org.newinstance.gucoach.entity.Player;
import org.newinstance.gucoach.entity.PlayerHistory;
import org.newinstance.gucoach.entity.PlayerStats;
import org.newinstance.gucoach.exception.ImportException;
import org.newinstance.gucoach.exception.ValidationException;
import org.newinstance.gucoach.utility.MessageId;

/**
 * Tests the methods of the {@link ImportController}.
 *
 * @author mwalter
 */
public class ImportControllerTest extends BaseTest {

    private static final String SAMPLE_EMPTY_IMPORT_FILE = "src/test/resources/empty_file.csv";

    private static final String SAMPLE_FILE_WITHOUT_PLAYER_DATA = "src/test/resources/file_without_player_data.csv";

    private static final String SAMPLE_FILE_PLAYER_DATA_WITHOUT_HISTORY_DATA = "src/test/resources/file_player_data_without_history_data.csv";

    private static final String SAMPLE_IMPORT_FILE = "src/test/resources/gu_2011-11-20_team.csv";

    private static final String SAMPLE_IMPORT_FILE_UPDATE = "src/test/resources/gu_2011-11-21_team_update.csv";

    @After
    public void tearDown() {
        importService.reset();
    }

    @Test
    public void executeDelete() {
        final Player player1 = createPlayer(556677L);
        final Player player2 = createPlayer(889933L);

        playerService.save(player1);
        playerService.save(player2);

        List<Player> players = playerService.findAll();
        Assert.assertNotNull(players);
        Assert.assertFalse(players.isEmpty());

        for (final Player player : players) {
            playerService.delete(player);
        }

        players = playerService.findAll();
        Assert.assertNotNull(players);
        Assert.assertTrue(players.isEmpty());
    }

    @Test(expected = ValidationException.class)
    public void executeImportFileAlreadyImported() throws Exception {
        importController.executeImport(new File(SAMPLE_IMPORT_FILE));
        // now import same file a second time
        try {
            importController.executeImport(new File(SAMPLE_IMPORT_FILE));
        } catch (final ValidationException ve) {
            Assert.assertEquals(MessageId.V001, ve.getMessageId());
            throw ve;
        }
    }

    @Test
    public void executeImport() throws Exception {
        importController.executeImport(new File(SAMPLE_IMPORT_FILE));
        final List<Player> players = playerService.findAll();
        Assert.assertFalse(players.isEmpty());
        for (final Player player : players) {
            final PlayerStats playerStats = player.getPlayerStats();
            Assert.assertNotNull(playerStats);
            Assert.assertEquals(player.getId(), playerStats.getPlayer().getId());
            final List<PlayerHistory> playerHistoryList = playerHistoryService.findByPlayer(player);
            Assert.assertNotNull(playerHistoryList);
            Assert.assertFalse(playerHistoryList.isEmpty());
        }
    }

    @Test(expected = ValidationException.class)
    public void executeImportWithoutPlayerData() throws Exception {
        try {
            importController.executeImport(new File(SAMPLE_FILE_WITHOUT_PLAYER_DATA));
            Assert.fail("Import should throw ValidationException.");
        } catch (ValidationException ve) {
            Assert.assertEquals(MessageId.V003, ve.getMessageId());
            throw ve;
        }
    }

    @Test(expected = ValidationException.class)
    public void executeImportPlayerDataWithoutHistoryData() throws Exception {
        try {
            importController.executeImport(new File(SAMPLE_FILE_PLAYER_DATA_WITHOUT_HISTORY_DATA));
            Assert.fail("Import should throw ValidationException.");
        } catch (ValidationException ve) {
            Assert.assertEquals(MessageId.V002, ve.getMessageId());
            throw ve;
        }
    }

    @Test
    public void executeImportUpdate() throws Exception {
        importController.executeImport(new File(SAMPLE_IMPORT_FILE));
        // now import new file to update player data
        importController.executeImport(new File(SAMPLE_IMPORT_FILE_UPDATE));
        // use a player id from the import file which was deleted during update
        final Player deletedPlayer = playerService.findOne(4848870L);
        Assert.assertNull(deletedPlayer);
    }

    @Test(expected = ImportException.class)
    public void executeImportOnEmptyFile() throws Exception {
        try {
            importController.executeImport(new File(SAMPLE_EMPTY_IMPORT_FILE));
            Assert.fail("Import should throw ImportException.");
        } catch (ImportException ie) {
            Assert.assertEquals(MessageId.E003, ie.getMessageId());
            throw ie;
        }
    }

    @Test(expected = ImportException.class)
    public void executeImportOnMissingFile() throws Exception {
        try {
            // triggers FileNotFoundException
            importController.executeImport(new File("sample_file.csv"));
            Assert.fail("Import should throw ImportException.");
        } catch (ImportException ie) {
            Assert.assertEquals(MessageId.E004, ie.getMessageId());
            throw ie;
        }
    }
}
