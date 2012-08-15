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
import org.newinstance.gucoach.model.StandingsHistory;
import org.newinstance.gucoach.model.Team;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Tests the methods of the {@link StandingsHistoryService}.
 *
 * @author mwalter
 */
public class StandingsHistoryServiceTest extends PersistenceTest {

    private StandingsHistoryService standingsHistoryService;
    private TeamService teamService;

    @Before
    public void init() {
        standingsHistoryService = new StandingsHistoryService(em);
        teamService = new TeamService(em);
    }

    @Test(expected = NoResultException.class)
    public void insertAndDeleteStandingsHistoryTest() {
        final List<Team> teams = new ArrayList<Team>();
        Team team = createTeam("Olympique Marseille", 1);
        teams.add(team);

        em.getTransaction().begin();
        teamService.insertTeams(teams);
        em.getTransaction().commit();

        final Date matchDay = new Date();
        team = teamService.findAllTeams().get(0);

        final StandingsHistory standingsHistory = createStandingsHistory(team, matchDay);
        standingsHistoryService.insertStandingsHistory(standingsHistory);

        StandingsHistory result = standingsHistoryService.findStandingsHistoryByTeamAndDate(team, matchDay);
        Assert.assertNotNull(result);
        Assert.assertEquals(team.getId(), result.getTeam().getId());

        // DELETE
        em.getTransaction().begin();
        standingsHistoryService.removeAllStandingsHistory();
        em.getTransaction().commit();

        standingsHistoryService.findStandingsHistoryByTeamAndDate(team, new Date());
    }

    @Test
    public void removeAllStandingsHistoryTest() {
        final List<Team> teams = new ArrayList<Team>();
        Team team = createTeam("Olympique Marseille", 1);
        teams.add(team);

        final Date matchDay1 = Calendar.getInstance().getTime();
        final StandingsHistory standingsHistory1 = createStandingsHistory(team, matchDay1);

        final Calendar matchDay2 = Calendar.getInstance();
        matchDay2.set(Calendar.DAY_OF_MONTH, -1);
        final StandingsHistory standingsHistory2 = createStandingsHistory(team, matchDay2.getTime());

        em.getTransaction().begin();
        teamService.insertTeams(teams);
        standingsHistoryService.insertStandingsHistory(standingsHistory1);
        standingsHistoryService.insertStandingsHistory(standingsHistory2);
        em.getTransaction().commit();

        StandingsHistory result = standingsHistoryService.findStandingsHistoryByTeamAndDate(team, standingsHistory1.getMatchDay());
        Assert.assertEquals(team, result.getTeam());
        result = standingsHistoryService.findStandingsHistoryByTeamAndDate(team, standingsHistory2.getMatchDay());
        Assert.assertEquals(team, result.getTeam());

        // DELETE
        em.getTransaction().begin();
        standingsHistoryService.removeAllStandingsHistory();
        em.getTransaction().commit();

        // clear entity manager to force JPA to fetch records from database
        em.clear();

        // do not use service finder method here because it expects a single result
        result = em.find(StandingsHistory.class, standingsHistory1.getId());
        Assert.assertNull(result);
        result = em.find(StandingsHistory.class, standingsHistory2.getId());
        Assert.assertNull(result);
    }

    @Test
    public void updateStandingsHistoryTest() {
        final List<Team> teams = new ArrayList<Team>();
        Team team = createTeam("Olympique Marseille", 1);
        teams.add(team);

        final Date matchDay1 = Calendar.getInstance().getTime();
        final StandingsHistory standingsHistory = createStandingsHistory(team, matchDay1);

        em.getTransaction().begin();
        teamService.insertTeams(teams);
        standingsHistoryService.insertStandingsHistory(standingsHistory);
        em.getTransaction().commit();

        Assert.assertEquals(4, standingsHistory.getGoalsFor().intValue());

        // UPDATE
        standingsHistory.setGoalsFor(20);
        standingsHistory.setPosition(1);

        em.getTransaction().commit();
        standingsHistoryService.updateStandingsHistory(standingsHistory);
        em.getTransaction().commit();

        final StandingsHistory result = standingsHistoryService.findStandingsHistoryByTeamAndDate(team, matchDay1);
        Assert.assertEquals(20, result.getGoalsFor().intValue());
        Assert.assertEquals(1, result.getPosition().intValue());
    }

    @Test(expected = PersistenceException.class)
    // TODO test only works if executed last
    public void insertStandingsHistoryTwiceTest() {
        final List<Team> teams = new ArrayList<Team>();
        Team team = createTeam("Paris SG", 1);
        teams.add(team);

        final Date matchDay1 = Calendar.getInstance().getTime();
        final StandingsHistory standingsHistory1 = createStandingsHistory(team, matchDay1);

        final StandingsHistory standingsHistory2 = createStandingsHistory(team, matchDay1);

        em.getTransaction().begin();
        teamService.insertTeams(teams);
        standingsHistoryService.insertStandingsHistory(standingsHistory1);
        standingsHistoryService.insertStandingsHistory(standingsHistory2);
        em.getTransaction().commit();
    }

}
