/*
 * GUCoach - your personal coach for Goalunited (tm).
 * Licensed under General Public Licence v3 (GPLv3)
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

package org.newinstance.gucoach.base;

import java.util.Date;

import org.junit.runner.RunWith;
import org.newinstance.gucoach.entity.Country;
import org.newinstance.gucoach.entity.Fixture;
import org.newinstance.gucoach.entity.Player;
import org.newinstance.gucoach.entity.PlayerHistory;
import org.newinstance.gucoach.entity.PlayerStats;
import org.newinstance.gucoach.entity.Position;
import org.newinstance.gucoach.entity.StandingsHistory;
import org.newinstance.gucoach.entity.StrongFoot;
import org.newinstance.gucoach.entity.Team;
import org.newinstance.gucoach.gui.model.LeagueModel;
import org.newinstance.gucoach.gui.model.StandingsModel;
import org.newinstance.gucoach.gui.model.TeamModel;
import org.newinstance.gucoach.service.FixtureService;
import org.newinstance.gucoach.service.ImportController;
import org.newinstance.gucoach.service.ImportService;
import org.newinstance.gucoach.service.PlayerHistoryService;
import org.newinstance.gucoach.service.PlayerService;
import org.newinstance.gucoach.service.StandingsHistoryService;
import org.newinstance.gucoach.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Base test class for all tests.
 *
 * @author mwalter
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/META-INF/applicationContext-test.xml"})
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    protected LeagueModel leagueModel;

    @Autowired
    protected StandingsModel standingsModel;

    @Autowired
    protected TeamModel teamModel;

    @Autowired
    protected FixtureService fixtureService;

    @Autowired
    protected ImportController importController;

    @Autowired
    protected ImportService importService;

    @Autowired
    protected PlayerHistoryService playerHistoryService;

    @Autowired
    protected PlayerService playerService;

    @Autowired
    protected StandingsHistoryService standingsHistoryService;

    @Autowired
    protected TeamService teamService;

    protected static final String JUNIT = "JUnit";

    /**
     * Creates and returns a new {@link org.newinstance.gucoach.entity.Fixture} entity.
     *
     * @return a new entity
     */
    protected Fixture createFixture(final Team homeTeam, final Team awayTeam, final String result) {
        final Fixture fixture = new Fixture();
        fixture.setMatchDay(new Date());
        fixture.setHomeTeam(homeTeam);
        fixture.setAwayTeam(awayTeam);
        fixture.setMatchResult(result);
        return fixture;
    }

    /**
     * Creates and returns a new {@link Player} entity.
     *
     * @return a new entity
     */
    protected Player createPlayer() {
        final Player player = new Player();
        player.setId(7654321L);
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
     * Creates and returns a new {@link org.newinstance.gucoach.entity.Player} entity.
     *
     * @param playerId the player's id
     * @return a new entity
     */
    protected Player createPlayer(final Long playerId) {
        final Player player = new Player();
        player.setId(playerId);
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
     * Creates and returns a new {@link org.newinstance.gucoach.entity.PlayerHistory} entity.
     *
     * @param player the player the history record belongs to
     * @return a new entity
     */
    protected PlayerHistory createPlayerHistory(final Player player) {
        final PlayerHistory playerHistory = new PlayerHistory();
        playerHistory.setAverageStrength(3.4f);
        playerHistory.setEndurance(77);
        playerHistory.setEnergy(88);
        playerHistory.setExperience(100);
        playerHistory.setForm(90);
        playerHistory.setPlayer(player);
        playerHistory.setSkillGoalkeeping(3);
        playerHistory.setSkillPassing(19);
        playerHistory.setSkillPlaymaking(17);
        playerHistory.setSkillScoring(12);
        playerHistory.setSkillTackling(37);
        playerHistory.setImportDate(new Date());
        return playerHistory;
    }

    /**
     * Creates and returns a new {@link org.newinstance.gucoach.entity.PlayerStats} entity.
     *
     * @param player the player the statistics record belongs to
     * @return a new entity
     */
    protected PlayerStats createPlayerStats(final Player player) {
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
        playerStats.setPlayer(player);
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
     * Creates and returns a new {@link org.newinstance.gucoach.entity.StandingsHistory} entity.
     *
     * @param team the team
     * @param matchDay the date of the matchday
     * @return a new entity
     */
    protected StandingsHistory createStandingsHistory(final Team team, final Date matchDay) {
        final StandingsHistory standingsHistory = new StandingsHistory();
        standingsHistory.setMatchesWon(2);
        standingsHistory.setMatchesDrawn(1);
        standingsHistory.setMatchesLost(3);
        standingsHistory.setGoalsFor(4);
        standingsHistory.setGoalsAgainst(7);
        standingsHistory.setMatchDay(matchDay);
        standingsHistory.setPosition(6);
        standingsHistory.setTeam(team);
        return standingsHistory;
    }

    /**
     * Creates and returns a new {@link org.newinstance.gucoach.entity.Team} entity.
     *
     * @param name the team name
     * @param position the team's starting position
     * @return a new entity
     */
    protected Team createTeam(final String name, final int position) {
        final Team team = new Team();
        team.setStartPos(position);
        team.setName(name);
        team.setStrength(55.0f);
        return team;
    }
}
