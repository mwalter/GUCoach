/*
 * GUCoach - your personal coach for Goalunited (tm).
 * Licenced under General Public Licence v3 (GPLv3)
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

package org.newinstance.gucoach.service;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.newinstance.gucoach.base.BaseTest;
import org.newinstance.gucoach.entity.Player;
import org.newinstance.gucoach.entity.PlayerHistory;

/**
 * Tests the methods of the {@link ImportService}.
 *
 * @author mwalter
 */
public class ImportServiceTest extends BaseTest {

    private static final String SAMPLE_IMPORT_FILE = "src/test/resources/gu_2011-11-20_team.csv";

    @Test
    public void importData() throws Exception {
        final InputStreamReader fileReader = new InputStreamReader(new FileInputStream(SAMPLE_IMPORT_FILE), ImportService.FILE_ENCODING);
        importService.importData(fileReader);

        final List<Player> playerList = importService.getPlayers();
        Assert.assertNotNull(playerList);
        Assert.assertFalse(playerList.isEmpty());

        for (final Player player : playerList) {
            Assert.assertNotNull(player.getPlayerStats());
        }

        final Map<Long, PlayerHistory> historyList = importService.getHistory();
        Assert.assertNotNull(historyList);
        Assert.assertFalse(historyList.isEmpty());
        Assert.assertEquals("Number of history records does not match player records.", playerList.size(), historyList.size());
    }

    @Test
    public void resetData() throws Exception {
        final InputStreamReader fileReader = new InputStreamReader(new FileInputStream(SAMPLE_IMPORT_FILE), ImportService.FILE_ENCODING);
        importService.importData(fileReader);

        Assert.assertFalse(importService.getPlayers().isEmpty());
        Assert.assertFalse(importService.getHistory().isEmpty());

        // reset all data
        importService.reset();

        Assert.assertTrue(importService.getPlayers().isEmpty());
        Assert.assertTrue(importService.getHistory().isEmpty());
    }

}
