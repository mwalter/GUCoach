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

package org.newinstance.gucoach.gui;

import org.junit.Assert;
import org.junit.Test;
import org.newinstance.gucoach.model.Country;
import org.newinstance.gucoach.model.Position;
import org.newinstance.gucoach.model.StrongFoot;

import java.util.Date;

/**
 * Tests methods of class {@link PlayerDataRow}.
 *
 * @author mwalter
 */
public class PlayerDataRowTest {

    @Test
    public void testAge() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setAge(22);
        Assert.assertEquals(22, row.getAge().intValue());
    }

    @Test
    public void testAssignments() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setAssignments(100);
        Assert.assertEquals(100, row.getAssignments().intValue());
    }

    @Test
    public void testBirthday() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setBirthday("16.08");
        Assert.assertEquals("16.08", row.getBirthday());
    }

    @Test
    public void testCountry() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setCountry(Country.CH);
        Assert.assertEquals(Country.CH, row.getCountry());
    }

    @Test
    public void testEndurance() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setEndurance(60);
        Assert.assertEquals(60, row.getEndurance().intValue());
    }

    @Test
    public void testEnergy() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setEnergy(70);
        Assert.assertEquals(70, row.getEnergy().intValue());
    }

    @Test
    public void testExperience() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setExperience(60);
        Assert.assertEquals(60, row.getExperience().intValue());
    }

    @Test
    public void testFirstName() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setFirstName("John");
        Assert.assertEquals("John", row.getFirstName());
    }

    @Test
    public void testForm() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setForm(80);
        Assert.assertEquals(80, row.getForm().intValue());
    }

    @Test
    public void testFullName() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setFirstName("John");
        row.setLastName("Doe");
        Assert.assertEquals("Doe, John", row.getFullName());
    }

    @Test
    public void testGetRedCardsTotal() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setRedCardsTotal(3);
        Assert.assertEquals(3, row.getRedCardsTotal().intValue());
    }

    @Test
    public void testGoalsSeason() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setGoalsSeason(10);
        Assert.assertEquals(10, row.getGoalsSeason().intValue());
    }

    @Test
    public void testGoalsSeasonAndTotal() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setGoalsSeason(10);
        row.setGoalsTotal(20);
        Assert.assertEquals("10 (20)", row.getGoalsSeasonAndTotal());
    }

    @Test
    public void testGoalsTotal() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setGoalsTotal(10);
        Assert.assertEquals(10, row.getGoalsTotal().intValue());
    }

    @Test
    public void testHeight() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setHeight(187);
        Assert.assertEquals(187, row.getHeight().intValue());
    }

    @Test
    public void testId() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setId(1L);
        Assert.assertEquals(1L, row.getId().longValue());
    }

    @Test
    public void testImportDate() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        final Date date = new Date();
        row.setImportDate(date);
        Assert.assertEquals(date, row.getImportDate());
    }

    @Test
    public void testLastName() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setLastName("Doe");
        Assert.assertEquals("Doe", row.getLastName());
    }

    @Test
    public void testMarketValue() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setMarketValue(1000000);
        Assert.assertEquals(1000000, row.getMarketValue().intValue());
    }

    @Test
    public void testNumber() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setNumber(10);
        Assert.assertEquals(10, row.getNumber().intValue());
    }

    @Test
    public void testPersonality() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setPersonality("spirited");
        Assert.assertEquals("spirited", row.getPersonality());
    }

    @Test
    public void testPosition() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setPosition(Position.DEF);
        Assert.assertEquals(Position.DEF, row.getPosition());
    }

    @Test
    public void testRedCardsSeason() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setRedCardsSeason(1);
        Assert.assertEquals(1, row.getRedCardsSeason().intValue());
    }

    @Test
    public void testRedCardsSeasonAndTotal() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setRedCardsSeason(1);
        row.setRedCardsTotal(3);
        Assert.assertEquals("1 (3)", row.getRedCardsSeasonAndTotal());
    }

    @Test
    public void testSalary() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setSalary(15000);
        Assert.assertEquals(15000, row.getSalary().intValue());
    }

    @Test
    public void testSkillGoalkeeping() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setSkillGoalkeeping(3);
        Assert.assertEquals(3, row.getSkillGoalkeeping().intValue());
    }

    @Test
    public void testSkillPassing() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setSkillPassing(33);
        Assert.assertEquals(33, row.getSkillPassing().intValue());
    }

    @Test
    public void testSkillPlaymaking() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setSkillPlaymaking(20);
        Assert.assertEquals(20, row.getSkillPlaymaking().intValue());
    }

    @Test
    public void testSkillScoring() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setSkillScoring(5);
        Assert.assertEquals(5, row.getSkillScoring().intValue());
    }

    @Test
    public void testSkillTackling() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setSkillTackling(46);
        Assert.assertEquals(46, row.getSkillTackling().intValue());
    }

    @Test
    public void testStrength() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setStrength(4.2f);
        Assert.assertEquals(4.2f, row.getStrength(), 0);
    }

    @Test
    public void testStrongFoot() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setStrongFoot(StrongFoot.L);
        Assert.assertEquals(StrongFoot.L, row.getStrongFoot());
    }

    @Test
    public void testTalent() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setStrength(4.2f);
        Assert.assertEquals(4.2f, row.getStrength(), 0);
    }

    @Test
    public void testTalentLevel() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setTalentLevel(2);
        Assert.assertEquals(2, row.getTalentLevel().intValue());
    }

    @Test
    public void testTraining() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setTraining("");
        Assert.assertEquals("", row.getTraining());
    }

    @Test
    public void testYellowCardsSeason() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setYellowCardsSeason(3);
        Assert.assertEquals(3, row.getYellowCardsSeason().intValue());
    }

    @Test
    public void testYellowCardsSeasonAndTotal() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setYellowCardsSeason(3);
        row.setYellowCardsTotal(14);
        Assert.assertEquals("3 (14)", row.getYellowCardsSeasonAndTotal());
    }

    @Test
    public void testYellowCardsTotal() throws Exception {
        final PlayerDataRow row = new PlayerDataRow();
        row.setYellowCardsTotal(14);
        Assert.assertEquals(14, row.getYellowCardsTotal().intValue());
    }

    @Test
    public void testToString() {
        final PlayerDataRow row = new PlayerDataRow();
        row.setYellowCardsSeason(3);
        row.setYellowCardsTotal(14);
        row.setStrength(4.2f);
        row.setSalary(15000);
        row.setGoalsSeason(10);
        row.setGoalsTotal(20);
        row.setBirthday("16.08");
        row.setCountry(Country.CH);
        row.setFirstName("John");
        row.setLastName("Doe");
        row.setImportDate(new Date());

        final String toString = row.toString();
        Assert.assertTrue(toString.contains("PlayerDataRow"));
        Assert.assertTrue(toString.contains("birthday=16.08"));
        Assert.assertTrue(toString.contains("country=CH"));
        Assert.assertTrue(toString.contains("yellowCardsSeason=3"));
        Assert.assertTrue(toString.contains("yellowCardsTotal=14"));
        Assert.assertTrue(toString.contains("firstName=John"));
        Assert.assertTrue(toString.contains("lastName=Doe"));
        Assert.assertTrue(toString.contains("strength=4.2"));
        Assert.assertTrue(toString.contains("goalsSeason=10"));
        Assert.assertTrue(toString.contains("goalsTotal=20"));
        Assert.assertTrue(toString.contains("salary=15000"));
    }
}
