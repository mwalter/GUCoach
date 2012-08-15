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
import org.junit.BeforeClass;
import org.junit.Test;
import org.newinstance.gucoach.model.Player;
import org.newinstance.gucoach.model.PlayerHistory;
import org.newinstance.gucoach.model.PlayerStats;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 * Tests the methods of the {@link ImportService}.
 *
 * @author mwalter
 */
public class ImportServiceTest {

    private static final String SAMPLE_IMPORT_FILE = "src/test/resources/gu_2011-11-20_team.csv";
    private static ImportService importService;

    @BeforeClass
    public static void init() {
        importService = new ImportServiceImpl();
    }

    @Test
    public void importDataTest() throws Exception {
        final InputStreamReader fileReader = new InputStreamReader(new FileInputStream(SAMPLE_IMPORT_FILE), ImportService.FILE_ENCODING);
        importService.importData(fileReader);

        final List<Player> playerList = importService.getPlayers();
        Assert.assertNotNull(playerList);
        Assert.assertFalse(playerList.isEmpty());

        final Map<Long, PlayerStats> statsList = importService.getStats();
        Assert.assertNotNull(statsList);
        Assert.assertFalse(statsList.isEmpty());

        final Map<Long, PlayerHistory> historyList = importService.getHistory();
        Assert.assertNotNull(historyList);
        Assert.assertFalse(historyList.isEmpty());
        Assert.assertEquals("Number of history records does not match player records.", playerList.size(), historyList.size());
    }

}
