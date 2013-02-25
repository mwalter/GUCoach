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

package org.newinstance.gucoach.entity;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Tests methods of class {@link PlayerHistory}.
 *
 * @author mwalter
 */
public class PlayerHistoryTest {

    @Test
    public void testId() {
        final PlayerHistory playerHistory = new PlayerHistory();
        playerHistory.setId(1L);
        Assert.assertEquals(1L, playerHistory.getId().longValue());
    }

    @Test
    public void testPlayer() {
        final Player player = new Player();
        player.setLastName("Messi");
        player.setFirstName("Lionel");
        final PlayerHistory playerHistory = new PlayerHistory();
        playerHistory.setPlayer(player);
        Assert.assertEquals("Messi", playerHistory.getPlayer().getLastName());
    }

    @Test
    public void testAverageStrength() {
        final PlayerHistory playerHistory = new PlayerHistory();
        playerHistory.setAverageStrength(3.4f);
        Assert.assertEquals(3.4f, playerHistory.getAverageStrength(), 0);
    }

    @Test
    public void testForm() {
        final PlayerHistory playerHistory = new PlayerHistory();
        playerHistory.setForm(67);
        Assert.assertEquals(67, playerHistory.getForm().intValue());
    }

    @Test
    public void testEnergy() {
        final PlayerHistory playerHistory = new PlayerHistory();
        playerHistory.setEnergy(88);
        Assert.assertEquals(88, playerHistory.getEnergy().intValue());
    }

    @Test
    public void testEndurance() {
        final PlayerHistory playerHistory = new PlayerHistory();
        playerHistory.setEndurance(82);
        Assert.assertEquals(82, playerHistory.getEndurance().intValue());
    }

    @Test
    public void testExperience() {
        final PlayerHistory playerHistory = new PlayerHistory();
        playerHistory.setExperience(82);
        Assert.assertEquals(82, playerHistory.getExperience().intValue());
    }
    
    @Test
    public void testSkillGoalkeeping() {
        final PlayerHistory playerHistory = new PlayerHistory();
        playerHistory.setSkillGoalkeeping(5);
        Assert.assertEquals(5, playerHistory.getSkillGoalkeeping().intValue());
    }

    @Test
    public void testSkillTackling() {
        final PlayerHistory playerHistory = new PlayerHistory();
        playerHistory.setSkillTackling(17);
        Assert.assertEquals(17, playerHistory.getSkillTackling().intValue());
    }

    @Test
    public void testSkillPlaymaking() {
        final PlayerHistory playerHistory = new PlayerHistory();
        playerHistory.setSkillPlaymaking(22);
        Assert.assertEquals(22, playerHistory.getSkillPlaymaking().intValue());
    }

    @Test
    public void testSkillPassing() {
        final PlayerHistory playerHistory = new PlayerHistory();
        playerHistory.setSkillPassing(11);
        Assert.assertEquals(11, playerHistory.getSkillPassing().intValue());
    }

    @Test
    public void testSkillScoring() {
        final PlayerHistory playerHistory = new PlayerHistory();
        playerHistory.setSkillScoring(7);
        Assert.assertEquals(7, playerHistory.getSkillScoring().intValue());
    }

    @Test
    public void testImportDate() {
        final PlayerHistory playerHistory = new PlayerHistory();
        final Date date = new Date();
        playerHistory.setImportDate(date);
        Assert.assertEquals(date, playerHistory.getImportDate());
    }
}
