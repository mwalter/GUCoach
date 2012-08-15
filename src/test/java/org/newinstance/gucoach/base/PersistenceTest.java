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

package org.newinstance.gucoach.base;

import org.junit.After;
import org.junit.Before;
import org.newinstance.gucoach.model.Country;
import org.newinstance.gucoach.model.Fixture;
import org.newinstance.gucoach.model.Player;
import org.newinstance.gucoach.model.PlayerHistory;
import org.newinstance.gucoach.model.PlayerStats;
import org.newinstance.gucoach.model.Position;
import org.newinstance.gucoach.model.StandingsHistory;
import org.newinstance.gucoach.model.StrongFoot;
import org.newinstance.gucoach.model.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

/**
 * Superclass for all tests related to persistence. Provides an {@link EntityManager} which uses a test Persistence Unit to be used in the test classes.
 * For each test a new {@link EntityManager} is created.
 *
 * @author mwalter
 */
public class PersistenceTest {

    /** The test persistence unit name. */
    private static final String GUCOACH_TEST_PERSISTENCE_UNIT = "gucoach-test-pu";
    private EntityManagerFactory emf;
    /** The entity manager uses a special persistence unit for testing. */
    protected EntityManager em;

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory(GUCOACH_TEST_PERSISTENCE_UNIT);
        em = emf.createEntityManager();
    }

    @After
    public void tearDown() {
        em.close();
        emf.close();
    }

    /**
     * Creates and returns a new {@link Fixture} entity.
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
     * Creates and returns a new {@link Player} entity.
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
     * Creates and returns a new {@link PlayerHistory} entity.
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
     * Creates and returns a new {@link PlayerStats} entity.
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
     * Creates and returns a new {@link StandingsHistory} entity.
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
     * Creates and returns a new {@link Team} entity.
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
