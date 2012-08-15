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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.newinstance.gucoach.base.PersistenceTest;
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
public class ImportControllerTest extends PersistenceTest {

    private static final String SAMPLE_IMPORT_FILE = "src/test/resources/gu_2011-11-20_team.csv";
    private static final String SAMPLE_IMPORT_FILE_UPDATE = "src/test/resources/gu_2011-11-21_team_update.csv";
    private static PlayerService playerService;
    private static PlayerStatsService playerStatsService;
    private static PlayerHistoryService playerHistoryService;

    @Before
    public void init() {
        playerService = new PlayerService(em);
        playerStatsService = new PlayerStatsService(em);
        playerHistoryService = new PlayerHistoryService(em);
    }

    @Test
    public void executeDeleteTest() {
        final Player player1 = createPlayer(556677L);
        final Player player2 = createPlayer(889933L);

        em.getTransaction().begin();
        playerService.insertPlayer(player1);
        playerService.insertPlayer(player2);
        em.getTransaction().commit();

        List<Player> players = playerService.findAllPlayers();
        Assert.assertNotNull(players);
        Assert.assertFalse(players.isEmpty());

        em.getTransaction().begin();
        for (final Player player : players) {
            playerService.removePlayer(player);
        }
        em.getTransaction().commit();

        players = playerService.findAllPlayers();
        Assert.assertNotNull(players);
        Assert.assertTrue(players.isEmpty());
    }

    @Test(expected = ValidationException.class)
    public void executeImportFileAlreadyImportedTest() throws Exception {
        ImportController importController = new ImportControllerImpl(em);
        importController.executeImport(new File(SAMPLE_IMPORT_FILE));
        // now import same file a second time
        try {
            importController = new ImportControllerImpl(em);
            importController.executeImport(new File(SAMPLE_IMPORT_FILE));
        } catch (final ValidationException e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    @Test
    public void executeImportTest() throws Exception {
        ImportController importController = new ImportControllerImpl(em);
        importController.executeImport(new File(SAMPLE_IMPORT_FILE));
        final List<Player> players = playerService.findAllPlayers();
        Assert.assertFalse(players.isEmpty());
        for (final Player player : players) {
            final PlayerStats playerStats = playerStatsService.findPlayerStatsByPlayer(player);
            Assert.assertNotNull(playerStats);
            Assert.assertEquals(player.getId(), playerStats.getPlayer().getId());
            final List<PlayerHistory> playerHistoryList = playerHistoryService.findPlayerHistoryByPlayer(player);
            Assert.assertNotNull(playerHistoryList);
            Assert.assertFalse(playerHistoryList.isEmpty());
        }
    }

    @Test
    public void executeImportUpdateTest() throws Exception {
        ImportController importController = new ImportControllerImpl(em);
        importController.executeImport(new File(SAMPLE_IMPORT_FILE));
        // now import new file to update player data
        importController = new ImportControllerImpl(em);
        importController.executeImport(new File(SAMPLE_IMPORT_FILE_UPDATE));
        final Player deletedPlayer = em.find(Player.class, 4848870L);
        Assert.assertNull(deletedPlayer);
    }

}
