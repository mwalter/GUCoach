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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.newinstance.gucoach.base.PersistenceTest;
import org.newinstance.gucoach.model.Fixture;
import org.newinstance.gucoach.model.Team;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests the methods of the {@link FixtureService}.
 *
 * @author mwalter
 */
public class FixtureServiceTest extends PersistenceTest {

    private FixtureService fixtureService;
    private TeamService teamService;

    @Before
    public void init() {
        fixtureService = new FixtureService(em);
        teamService = new TeamService(em);
    }

    @Test
    public void insertFixturesTest() {
        final Team team1 = createTeam("FC Barcelona", 1);
        final Team team2 = createTeam("Manchester United", 2);

        final List<Team> teams = new ArrayList<Team>();
        teams.add(team1);
        teams.add(team2);

        final Fixture fixture1 = createFixture(team1, team2, null);
        final Fixture fixture2 = createFixture(team2, team1, null);
        final List<Fixture> fixtures = new ArrayList<Fixture>();
        fixtures.add(fixture1);
        fixtures.add(fixture2);

        em.getTransaction().begin();
        teamService.insertTeams(teams);
        fixtureService.insertFixtures(fixtures);
        em.getTransaction().commit();

        final List<Fixture> result = fixtureService.findAllFixtures();
        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void updateFixtureTest() {
        final Team team1 = createTeam("Young Boys", 1);
        final Team team2 = createTeam("Old Girls", 2);

        final List<Team> teams = new ArrayList<Team>();
        teams.add(team1);
        teams.add(team2);

        final Fixture fixture1 = createFixture(team1, team2, null);
        final List<Fixture> fixtures = new ArrayList<Fixture>();
        fixtures.add(fixture1);

        em.getTransaction().begin();
        teamService.insertTeams(teams);
        fixtureService.insertFixtures(fixtures);
        em.getTransaction().commit();

        // update fixture
        fixture1.setMatchResult("2:0");

        em.getTransaction().begin();
        fixtureService.updateFixture(fixture1);
        em.getTransaction().commit();

        final List<Fixture> result = fixtureService.findAllFixtures();
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("2:0", result.get(0).getMatchResult());
    }

    @Test
    public void removeFixtureTest() {
        final Team team1 = createTeam("FC Barcelona", 1);
        final Team team2 = createTeam("Manchester United", 2);

        final List<Team> teams = new ArrayList<Team>();
        teams.add(team1);
        teams.add(team2);

        final Fixture fixture1 = createFixture(team1, team2, null);
        final Fixture fixture2 = createFixture(team2, team1, null);
        final List<Fixture> fixtures = new ArrayList<Fixture>();
        fixtures.add(fixture1);
        fixtures.add(fixture2);

        em.getTransaction().begin();
        teamService.insertTeams(teams);
        fixtureService.insertFixtures(fixtures);
        em.getTransaction().commit();

        List<Fixture> result = fixtureService.findAllFixtures();
        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.size());

        // remove fixtures
        em.getTransaction().begin();
        fixtureService.removeAllFixtures();
        em.getTransaction().commit();

        result = fixtureService.findAllFixtures();
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }

    @Test(expected = PersistenceException.class)
    // TODO test only works if executed last
    public void insertFixtureTwiceTest() {
        final Team team1 = createTeam("FC Barcelona", 1);
        final Team team2 = createTeam("Manchester United", 2);

        final List<Team> teams = new ArrayList<Team>();
        teams.add(team1);
        teams.add(team2);

        final Fixture fixture1 = createFixture(team1, team2, null);
        final Fixture fixture2 = createFixture(team1, team2, null);
        final List<Fixture> fixtures = new ArrayList<Fixture>();
        fixtures.add(fixture1);
        fixtures.add(fixture2);

        em.getTransaction().begin();
        teamService.insertTeams(teams);
        fixtureService.insertFixtures(fixtures);
        em.getTransaction().commit();
    }

}
