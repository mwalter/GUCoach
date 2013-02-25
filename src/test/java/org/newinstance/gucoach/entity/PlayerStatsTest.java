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
 * Tests methods of class {@link PlayerStats}.
 *
 * @author mwalter
 * @version 11.02.12
 */
public class PlayerStatsTest {

    @Test
    public void testId() {
        final PlayerStats playerStats = new PlayerStats();
        playerStats.setId(1L);
        Assert.assertEquals(1L, playerStats.getId().longValue());
    }

    @Test
    public void testPlayer() {
        final Player player = new Player();
        player.setLastName("Messi");
        player.setFirstName("Lionel");
        final PlayerStats playerStats = new PlayerStats();
        playerStats.setPlayer(player);
        Assert.assertEquals(player.getLastName(), playerStats.getPlayer().getLastName());
    }

    @Test
    public void testAssignments() {
        final PlayerStats playerStats = new PlayerStats();
        playerStats.setAssignments(22);
        Assert.assertEquals(22, playerStats.getAssignments().intValue());
    }

    @Test
    public void testGoalsSeason() {
        final PlayerStats playerStats = new PlayerStats();
        playerStats.setGoalsSeason(3);
        Assert.assertEquals(3, playerStats.getGoalsSeason().intValue());
    }

    @Test
    public void testGoalsTotal() {
        final PlayerStats playerStats = new PlayerStats();
        playerStats.setGoalsTotal(13);
        Assert.assertEquals(13, playerStats.getGoalsTotal().intValue());
    }

    @Test
    public void testMarketValue() {
        final PlayerStats playerStats = new PlayerStats();
        playerStats.setMarketValue(200000);
        Assert.assertEquals(200000, playerStats.getMarketValue().intValue());
    }

    @Test
    public void testYellowCardsSeason() {
        final PlayerStats playerStats = new PlayerStats();
        playerStats.setYellowCardsSeason(2);
        Assert.assertEquals(2, playerStats.getYellowCardsSeason().intValue());
    }

    @Test
    public void testYellowCardsTotal() {
        final PlayerStats playerStats = new PlayerStats();
        playerStats.setYellowCardsTotal(7);
        Assert.assertEquals(7, playerStats.getYellowCardsTotal().intValue());
    }

    @Test
    public void testRedCardsSeason() {
        final PlayerStats playerStats = new PlayerStats();
        playerStats.setRedCardsSeason(0);
        Assert.assertEquals(0, playerStats.getRedCardsSeason().intValue());
    }

    @Test
    public void testRedCardsTotal() {
        final PlayerStats playerStats = new PlayerStats();
        playerStats.setRedCardsTotal(1);
        Assert.assertEquals(1, playerStats.getRedCardsTotal().intValue());
    }

    @Test
    public void testNumber() {
        final PlayerStats playerStats = new PlayerStats();
        playerStats.setNumber(11);
        Assert.assertEquals(11, playerStats.getNumber().intValue());
    }

    @Test
    public void testTraining() {
        final PlayerStats playerStats = new PlayerStats();
        playerStats.setTraining("undefined");
        Assert.assertEquals("undefined", playerStats.getTraining());
    }

    @Test
    public void testAverageStrength() {
        final PlayerStats playerStats = new PlayerStats();
        playerStats.setAverageStrength(3.4f);
        Assert.assertEquals(3.4f, playerStats.getAverageStrength(), 0);
    }

    @Test
    public void testPosition() {
        final PlayerStats playerStats = new PlayerStats();
        playerStats.setPosition(Position.MID);
        Assert.assertEquals(Position.MID, playerStats.getPosition());
    }

    @Test
    public void testForm() {
        final PlayerStats playerStats = new PlayerStats();
        playerStats.setForm(67);
        Assert.assertEquals(67, playerStats.getForm().intValue());
    }

    @Test
    public void testEnergy() {
        final PlayerStats playerStats = new PlayerStats();
        playerStats.setEnergy(88);
        Assert.assertEquals(88, playerStats.getEnergy().intValue());
    }

    @Test
    public void testEndurance() {
        final PlayerStats playerStats = new PlayerStats();
        playerStats.setEndurance(82);
        Assert.assertEquals(82, playerStats.getEndurance().intValue());
    }

    @Test
    public void testExperience() {
        final PlayerStats playerStats = new PlayerStats();
        playerStats.setExperience(82);
        Assert.assertEquals(82, playerStats.getExperience().intValue());
    }
    
    @Test
    public void testSkillGoalkeeping() {
        final PlayerStats playerStats = new PlayerStats();
        playerStats.setSkillGoalkeeping(5);
        Assert.assertEquals(5, playerStats.getSkillGoalkeeping().intValue());
    }

    @Test
    public void testSkillTackling() {
        final PlayerStats playerStats = new PlayerStats();
        playerStats.setSkillTackling(17);
        Assert.assertEquals(17, playerStats.getSkillTackling().intValue());
    }

    @Test
    public void testSkillPlaymaking() {
        final PlayerStats playerStats = new PlayerStats();
        playerStats.setSkillPlaymaking(22);
        Assert.assertEquals(22, playerStats.getSkillPlaymaking().intValue());
    }

    @Test
    public void testSkillPassing() {
        final PlayerStats playerStats = new PlayerStats();
        playerStats.setSkillPassing(11);
        Assert.assertEquals(11, playerStats.getSkillPassing().intValue());
    }

    @Test
    public void testSkillScoring() {
        final PlayerStats playerStats = new PlayerStats();
        playerStats.setSkillScoring(7);
        Assert.assertEquals(7, playerStats.getSkillScoring().intValue());
    }

    @Test
    public void testTalent() {
        final PlayerStats playerStats = new PlayerStats();
        playerStats.setTalent("Corners");
        Assert.assertEquals("Corners", playerStats.getTalent());
    }

    @Test
    public void testTalentLevel() {
        final PlayerStats playerStats = new PlayerStats();
        playerStats.setTalentLevel(1);
        Assert.assertEquals(1, playerStats.getTalentLevel().intValue());
    }

    @Test
    public void testAge() {
        final PlayerStats playerStats = new PlayerStats();
        playerStats.setAge(17);
        Assert.assertEquals(17, playerStats.getAge().intValue());
    }

    @Test
    public void testSalary() {
        final PlayerStats playerStats = new PlayerStats();
        playerStats.setSalary(1700);
        Assert.assertEquals(1700, playerStats.getSalary().intValue());
    }

    @Test
    public void testImportDate() {
        final PlayerStats playerStats = new PlayerStats();
        final Date date = new Date();
        playerStats.setImportDate(date);
        Assert.assertEquals(date, playerStats.getImportDate());
    }
}
