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
import org.newinstance.gucoach.model.Player;
import org.newinstance.gucoach.model.PlayerHistory;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Tests the methods of the {@link PlayerHistoryService}.
 *
 * @author mwalter
 */
public class PlayerHistoryServiceTest extends PersistenceTest {

    private PlayerService playerService;
    private PlayerHistoryService playerHistoryService;

    @Before
    public void init() {
        playerService = new PlayerService(em);
        playerHistoryService = new PlayerHistoryService(em);
    }

    @Test
    public void insertPlayerHistoryTest() {
        final Player player = createPlayer();
        final PlayerHistory playerHistory = createPlayerHistory(player);

        em.getTransaction().begin();
        playerService.insertPlayer(player);
        playerHistoryService.insertPlayerHistory(playerHistory);
        em.getTransaction().commit();

        final PlayerHistory result = playerHistoryService.findPlayerHistoryById(playerHistory.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(77, result.getEndurance().intValue());
        Assert.assertEquals(17, result.getSkillPlaymaking().intValue());
    }

    @Test
    public void findAllImportDatesTest() {
        final Player player = createPlayer();

        final Calendar cal = Calendar.getInstance();
        final PlayerHistory playerHistory1 = createPlayerHistory(player);
        playerHistory1.setPlayer(player);
        playerHistory1.setImportDate(cal.getTime());

        cal.roll(Calendar.MONTH, -1);
        final PlayerHistory playerHistory2 = createPlayerHistory(player);
        playerHistory2.setPlayer(player);
        playerHistory2.setImportDate(cal.getTime());

        em.getTransaction().begin();
        playerService.insertPlayer(player);
        playerHistoryService.insertPlayerHistory(playerHistory1);
        playerHistoryService.insertPlayerHistory(playerHistory2);
        em.getTransaction().commit();

        final List<Date> dates = playerHistoryService.findAllImportDates();
        Assert.assertNotNull(dates);
        Assert.assertFalse(dates.isEmpty());
        Assert.assertEquals(2, dates.size());
    }

    @Test
    public void findLatestImportDateTest() {
        final Player player = createPlayer();

        final Calendar cal = Calendar.getInstance();
        // reset time because time part is not persisted into database at all
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        final Date date1 = cal.getTime();
        final PlayerHistory playerHistory1 = createPlayerHistory(player);
        playerHistory1.setPlayer(player);
        playerHistory1.setImportDate(date1);

        // reduce import date by one day
        cal.set(Calendar.DAY_OF_MONTH, -1);
        final Date date2 = cal.getTime();
        final PlayerHistory playerHistory2 = createPlayerHistory(player);
        playerHistory2.setPlayer(player);
        playerHistory2.setImportDate(date2);

        em.getTransaction().begin();
        playerService.insertPlayer(player);
        playerHistoryService.insertPlayerHistory(playerHistory1);
        playerHistoryService.insertPlayerHistory(playerHistory2);
        em.getTransaction().commit();

        final Date date = playerHistoryService.findLatestImportDate();
        Assert.assertNotNull(date);
        Assert.assertEquals(date1, date);
    }

    @Test
    public void insertAndDeletePlayerHistoryTest() {
        final Player player = createPlayer();

        final PlayerHistory playerHistory1 = createPlayerHistory(player);
        playerHistory1.setPlayer(player);

        em.getTransaction().begin();
        playerService.insertPlayer(player);
        playerHistoryService.insertPlayerHistory(playerHistory1);
        em.getTransaction().commit();

        List<Player> playerList = playerService.findAllPlayers();
        Assert.assertNotNull(playerList);
        Assert.assertFalse(playerList.isEmpty());

        List<PlayerHistory> playerHistoryList = playerHistoryService.findPlayerHistoryByPlayer(player);
        Assert.assertNotNull(playerHistoryList);
        Assert.assertFalse(playerHistoryList.isEmpty());
        Assert.assertTrue(playerHistoryList.size() == 1);
        Assert.assertEquals("Player history does not match to player.", player, playerHistoryList.get(0).getPlayer());

        // insert another history record
        final PlayerHistory playerHistory2 = createPlayerHistory(player);
        playerHistory2.setPlayer(player);

        em.getTransaction().begin();
        playerHistoryService.insertPlayerHistory(playerHistory2);
        em.getTransaction().commit();

        playerHistoryList = playerHistoryService.findPlayerHistoryByPlayer(player);
        Assert.assertNotNull(playerHistoryList);
        Assert.assertFalse(playerHistoryList.isEmpty());
        Assert.assertTrue(playerHistoryList.size() == 2);

        // delete player (and related player history records)
        em.getTransaction().begin();
        playerService.removePlayer(player);
        em.getTransaction().commit();

        playerList = playerService.findAllPlayers();
        Assert.assertNotNull(playerList);
        Assert.assertTrue(playerList.isEmpty());

        playerHistoryList = playerHistoryService.findPlayerHistoryByPlayer(player);
        Assert.assertNotNull(playerHistoryList);
        Assert.assertTrue(playerHistoryList.isEmpty());
    }

}
