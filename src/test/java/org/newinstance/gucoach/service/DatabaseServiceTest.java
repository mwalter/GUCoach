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

import org.apache.ibatis.exceptions.PersistenceException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.newinstance.gucoach.model.Country;
import org.newinstance.gucoach.model.Fixture;
import org.newinstance.gucoach.model.Player;
import org.newinstance.gucoach.model.PlayerHistory;
import org.newinstance.gucoach.model.PlayerStats;
import org.newinstance.gucoach.model.Position;
import org.newinstance.gucoach.model.StrongFoot;
import org.newinstance.gucoach.model.Team;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Tests methods of class {@link DatabaseService}.
 *
 * @author mwalter
 */
public class DatabaseServiceTest {

    private DatabaseService databaseService;

    @Before
    public void setUp() {
        databaseService = new DatabaseServiceImpl();
        // if there are any tables left delete them first
        databaseService.deleteTables();
        databaseService.createTables();
    }

    @After
    public void tearDown() {
        databaseService.deleteTables();
    }

    @Test
    public void createTablesTest() {
        try {
            databaseService.createTables();
        } catch (final Exception e) {
            e.printStackTrace();
            Assert.fail("Exception was thrown.");
        }
    }

    @Test
    public void findAllImportDatesTest() {
        final Player player = createPlayer();
        databaseService.insertPlayer(player);

        final Calendar cal = Calendar.getInstance();
        final PlayerHistory playerHistory1 = createPlayerHistory();
        playerHistory1.setPlayerId(player.getId());
        playerHistory1.setImportDate(cal.getTime());
        databaseService.insertPlayerHistory(playerHistory1);

        cal.roll(Calendar.MONTH, -1);
        final PlayerHistory playerHistory2 = createPlayerHistory();
        playerHistory2.setPlayerId(player.getId());
        playerHistory2.setImportDate(cal.getTime());
        databaseService.insertPlayerHistory(playerHistory2);

        final List<Date> dates = databaseService.findAllImportDates();
        Assert.assertNotNull(dates);
        Assert.assertFalse(dates.isEmpty());
        Assert.assertEquals(2, dates.size());
    }

    @Test
    public void findLatestImportDateTest() {
        final Player player = createPlayer();
        databaseService.insertPlayer(player);

        final Calendar cal = Calendar.getInstance();
        // reset time because time part is not persisted into database at all
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        final Date date1 = cal.getTime();
        final PlayerHistory playerHistory1 = createPlayerHistory();
        playerHistory1.setPlayerId(player.getId());
        playerHistory1.setImportDate(date1);
        databaseService.insertPlayerHistory(playerHistory1);

        // reduce import date by one day
        cal.set(Calendar.DAY_OF_MONTH, -1);
        final Date date2 = cal.getTime();
        final PlayerHistory playerHistory2 = createPlayerHistory();
        playerHistory2.setPlayerId(player.getId());
        playerHistory2.setImportDate(date2);
        databaseService.insertPlayerHistory(playerHistory2);

        final Date date = databaseService.findLatestImportDate();
        Assert.assertNotNull(date);
        Assert.assertEquals(date1, date);
    }

    @Test
    public void findPlayerByPlayerId() {
        Player player = createPlayer();
        databaseService.insertPlayer(player);
        player = databaseService.findPlayerByPlayerId(player.getId());
        Assert.assertNotNull(player);
    }

    @Test
    public void insertAndDeletePlayerHistoryTest() {
        final Player player = createPlayer();
        databaseService.insertPlayer(player);

        final PlayerHistory playerHistory1 = createPlayerHistory();
        playerHistory1.setPlayerId(player.getId());
        databaseService.insertPlayerHistory(playerHistory1);

        List<Player> playerList = databaseService.findAllPlayers();
        Assert.assertNotNull(playerList);
        Assert.assertFalse(playerList.isEmpty());

        List<PlayerHistory> playerHistoryList = databaseService.findPlayerHistoryByPlayerId(player.getId());
        Assert.assertNotNull(playerHistoryList);
        Assert.assertFalse(playerHistoryList.isEmpty());
        Assert.assertTrue(playerHistoryList.size() == 1);
        Assert.assertEquals("Player history does not match to player.", player.getId(), playerHistoryList.get(0).getPlayerId());

        // insert another history record
        final PlayerHistory playerHistory2 = createPlayerHistory();
        playerHistory2.setPlayerId(player.getId());
        databaseService.insertPlayerHistory(playerHistory2);

        playerHistoryList = databaseService.findPlayerHistoryByPlayerId(player.getId());
        Assert.assertNotNull(playerHistoryList);
        Assert.assertFalse(playerHistoryList.isEmpty());
        Assert.assertTrue(playerHistoryList.size() == 2);

        // DELETE
        databaseService.deletePlayer(player.getId());

        playerList = databaseService.findAllPlayers();
        Assert.assertNotNull(playerList);
        Assert.assertTrue(playerList.isEmpty());

        playerHistoryList = databaseService.findPlayerHistoryByPlayerId(player.getId());
        Assert.assertNotNull(playerHistoryList);
        Assert.assertTrue(playerHistoryList.isEmpty());
    }

    @Test
    public void insertAndDeletePlayerTest() {
        final Player player = createPlayer();
        databaseService.insertPlayer(player);
        List<Player> result = databaseService.findAllPlayers();
        Assert.assertNotNull(result);
        Assert.assertFalse("No players found.", result.isEmpty());
        databaseService.deletePlayer(player.getId());
        result = databaseService.findAllPlayers();
        Assert.assertNotNull(result);
        Assert.assertTrue("Players found.", result.isEmpty());
    }

    @Test
    public void insertListOfTeams() {
        final Team team1 = createTeam("FC Basel", 1);
        final Team team2 = createTeam("FC Sion", 2);
        final Team team3 = createTeam("Young Boys", 3);

        final List<Team> teams = new ArrayList<Team>();
        teams.add(team1);
        teams.add(team2);
        teams.add(team3);

        databaseService.insertTeams(teams);

        final List<Team> result = databaseService.findAllTeams();
        Assert.assertNotNull(result);
        Assert.assertEquals(teams.size(), result.size());

        for (final Team team : result) {
            Assert.assertNotNull(team.getId());
        }
    }

    @Test(expected = PersistenceException.class)
    public void insertSameFixtureTwiceTest() {
        final Fixture fixture1 = createFixture();
        final Fixture fixture2 = createFixture();

        databaseService.insertFixture(fixture1);
        databaseService.insertFixture(fixture2);
    }

    @Test(expected = PersistenceException.class)
    public void insertSameTeamTwiceTest() {
        final Team team1 = createTeam("Arsenal", 1);
        final Team team2 = createTeam("Arsenal", 2);

        final List<Team> teams = new ArrayList<Team>();
        teams.add(team1);
        teams.add(team2);

        databaseService.insertTeams(teams);
    }

    @Test
    public void insertUpdateAndDeleteFixtureTest() {
        final Fixture fixture = createFixture();
        databaseService.insertFixture(fixture);

        List<Fixture> fixtureList = databaseService.findAllFixtures();
        Assert.assertNotNull(fixtureList);
        Assert.assertFalse(fixtureList.isEmpty());

        // UPDATE
        fixture.setHomeTeamId(10L);
        fixture.setAwayTeamId(7L);
        fixture.setMatchResult("2:4");
        databaseService.updateFixture(fixture);

        fixtureList = databaseService.findAllFixtures();
        Assert.assertNotNull(fixtureList);
        Assert.assertFalse(fixtureList.isEmpty());

        // compare updated values
        Assert.assertFalse(fixture.getMatchResult().equals(fixtureList.get(0).getMatchResult()));
        Assert.assertFalse(fixture.getHomeTeamId().equals(fixtureList.get(0).getHomeTeamId()));
        Assert.assertFalse(fixture.getAwayTeamId().equals(fixtureList.get(0).getAwayTeamId()));

        // DELETE
        databaseService.deleteAllFixtures();

        fixtureList = databaseService.findAllFixtures();
        Assert.assertNotNull(fixtureList);
        Assert.assertTrue(fixtureList.isEmpty());
    }

    @Test
    public void insertUpdateAndDeletePlayerStatsTest() {
        final Player player = createPlayer();
        databaseService.insertPlayer(player);

        final PlayerStats playerStats1 = createPlayerStats();
        playerStats1.setPlayerId(player.getId());
        databaseService.insertPlayerStats(playerStats1);

        List<Player> playerList = databaseService.findAllPlayers();
        Assert.assertNotNull(playerList);
        Assert.assertFalse(playerList.isEmpty());

        PlayerStats playerStats = databaseService.findPlayerStatsByPlayerId(player.getId());
        Assert.assertNotNull(playerStats);
        Assert.assertEquals("Player statistics do not match to player.", player.getId(), playerStats.getPlayerId());

        // UPDATE
        PlayerStats playerStats2 = createPlayerStats();
        playerStats2.setPlayerId(player.getId());
        playerStats2.setEndurance(65);
        playerStats2.setEnergy(71);
        playerStats2.setForm(85);
        databaseService.updatePlayerStats(playerStats2);
        playerStats2 = databaseService.findPlayerStatsByPlayerId(player.getId());
        Assert.assertNotNull(playerStats2);
        // compare updated values
        Assert.assertFalse(playerStats2.getEndurance().equals(playerStats.getEndurance()));
        Assert.assertFalse(playerStats2.getEnergy().equals(playerStats.getEnergy()));
        Assert.assertFalse(playerStats2.getForm().equals(playerStats.getForm()));

        // DELETE
        databaseService.deletePlayer(player.getId());

        playerList = databaseService.findAllPlayers();
        Assert.assertNotNull(playerList);
        Assert.assertTrue(playerList.isEmpty());

        playerStats = databaseService.findPlayerStatsByPlayerId(player.getId());
        Assert.assertNull(playerStats);
    }

    @Test
    public void insertUpdateAndDeleteTeamsTest() {
        final Team team1 = createTeam("Juventus", 1);
        final Team team2 = createTeam("AC Milan", 2);
        final Team team3 = createTeam("AS Roma", 3);

        final List<Team> teams = new ArrayList<Team>();
        teams.add(team1);
        teams.add(team2);
        teams.add(team3);

        databaseService.insertTeams(teams);

        List<Team> teamList = databaseService.findAllTeams();
        Assert.assertNotNull(teamList);
        Assert.assertFalse(teamList.isEmpty());

        // UPDATE
        team1.setStrength(57.0f);
        databaseService.updateTeam(team1);

        teamList = databaseService.findAllTeams();
        Assert.assertNotNull(teamList);
        Assert.assertFalse(teamList.isEmpty());

        // compare updated values
        Assert.assertFalse(team1.getStrength().equals(teamList.get(0).getStrength()));

        // DELETE
        databaseService.deleteAllTeams();

        teamList = databaseService.findAllTeams();
        Assert.assertNotNull(teamList);
        Assert.assertTrue(teamList.isEmpty());
    }

    /**
     * Creates and returns a new {@link Fixture} entity.
     *
     * @return a new entity
     */
    private Fixture createFixture() {
        final Fixture fixture = new Fixture();
        fixture.setMatchDay(new Date());
        fixture.setMatchResult("2:1");
        fixture.setAwayTeamId(5L);
        fixture.setHomeTeamId(3L);
        return fixture;
    }

    /**
     * Creates and returns a new {@link Player} entity.
     *
     * @return a new entity
     */
    private Player createPlayer() {
        final Random random = new Random();
        final Player player = new Player();
        player.setId(random.nextLong());
        player.setBirthday("08.08");
        player.setCountry(Country.CH);
        player.setHeight(185);
        player.setLastName("Foobar");
        player.setPersonality("hardworking");
        player.setStrongFoot(StrongFoot.L);
        player.setFirstName("John");
        player.setImportDate(new Date());
        return player;
    }

    /**
     * Creates and returns a new {@link PlayerStats} entity.
     *
     * @return a new entity
     */
    private PlayerHistory createPlayerHistory() {
        final PlayerHistory playerHistory = new PlayerHistory();
        playerHistory.setAverageStrength(3.4f);
        playerHistory.setEndurance(77);
        playerHistory.setEnergy(88);
        playerHistory.setExperience(100);
        playerHistory.setForm(90);
        playerHistory.setSkillGoalkeeping(3);
        playerHistory.setSkillPassing(19);
        playerHistory.setSkillPlaymaking(17);
        playerHistory.setSkillScoring(12);
        playerHistory.setSkillTackling(37);
        playerHistory.setImportDate(new Date());
        return playerHistory;
    }

    /**
     * Creates and returns a new {@link PlayerStats} entity.
     *
     * @return a new entity
     */
    private PlayerStats createPlayerStats() {
        final PlayerStats playerStats = new PlayerStats();
        playerStats.setAge(23);
        playerStats.setAssignments(33);
        playerStats.setAverageStrength(3.4f);
        playerStats.setEndurance(77);
        playerStats.setEnergy(88);
        playerStats.setExperience(100);
        playerStats.setForm(90);
        playerStats.setGoalsSeason(4);
        playerStats.setGoalsTotal(10);
        playerStats.setMarketValue(null);
        playerStats.setNumber(11);
        playerStats.setPosition(Position.DEF);
        playerStats.setRedCardsSeason(0);
        playerStats.setRedCardsTotal(1);
        playerStats.setSalary(4300);
        playerStats.setSkillGoalkeeping(3);
        playerStats.setSkillPassing(19);
        playerStats.setSkillPlaymaking(17);
        playerStats.setSkillScoring(12);
        playerStats.setSkillTackling(37);
        playerStats.setTalent(null);
        playerStats.setTalentLevel(0);
        playerStats.setTraining(null);
        playerStats.setYellowCardsSeason(2);
        playerStats.setYellowCardsTotal(7);
        playerStats.setImportDate(new Date());
        return playerStats;
    }

    /**
     * Creates and returns a new {@link Team} entity.
     *
     * @param name the team name
     * @param position the team's starting position
     * @return a new entity
     */
    private Team createTeam(final String name, final int position) {
        final Team team = new Team();
        team.setStartPos(position);
        team.setName(name);
        team.setStrength(55.0f);
        return team;
    }

}
