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

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.newinstance.gucoach.base.BaseTestClass;
import org.newinstance.gucoach.entity.Fixture;
import org.newinstance.gucoach.entity.Team;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * Tests the methods of the {@link FixtureService}.
 *
 * @author mwalter
 */
public class FixtureServiceTest extends BaseTestClass {

    @Test
    public void insertFixtures() {
        final Team team1 = createTeam("FC Barcelona", 1);
        final Team team2 = createTeam("Manchester United", 2);

        final List<Team> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);

        final Fixture fixture1 = createFixture(team1, team2, null);
        final Fixture fixture2 = createFixture(team2, team1, null);
        final List<Fixture> fixtures = new ArrayList<>();
        fixtures.add(fixture1);
        fixtures.add(fixture2);

        teamService.save(teams);
        fixtureService.save(fixtures);

        final List<Fixture> result = fixtureService.findAll();
        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void updateFixture() {
        final Team team1 = createTeam("Young Boys", 1);
        final Team team2 = createTeam("Old Girls", 2);

        final List<Team> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);

        final Fixture fixture1 = createFixture(team1, team2, null);
        final List<Fixture> fixtures = new ArrayList<>();
        fixtures.add(fixture1);

        teamService.save(teams);
        fixtureService.save(fixtures);

        // update fixture
        fixture1.setMatchResult("2:0");

        fixtureService.save(fixture1);

        final List<Fixture> result = fixtureService.findAll();
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("2:0", result.get(0).getMatchResult());
    }

    @Test
    public void removeFixture() {
        final Team team1 = createTeam("FC Barcelona", 1);
        final Team team2 = createTeam("Manchester United", 2);

        final List<Team> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);

        final Fixture fixture1 = createFixture(team1, team2, null);
        final Fixture fixture2 = createFixture(team2, team1, null);
        final List<Fixture> fixtures = new ArrayList<>();
        fixtures.add(fixture1);
        fixtures.add(fixture2);

        teamService.save(teams);
        fixtureService.save(fixtures);

        List<Fixture> result = fixtureService.findAll();
        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.size());

        // remove fixtures
        fixtureService.deleteAll();

        result = fixtureService.findAll();
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void insertFixtureTwice() {
        final Team team1 = createTeam("FC Barcelona", 1);
        final Team team2 = createTeam("Manchester United", 2);

        final List<Team> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);

        final Fixture fixture1 = createFixture(team1, team2, null);
        final Fixture fixture2 = createFixture(team1, team2, null);
        final List<Fixture> fixtures = new ArrayList<>();
        fixtures.add(fixture1);
        fixtures.add(fixture2);

        teamService.save(teams);
        fixtureService.save(fixtures);
    }

}
