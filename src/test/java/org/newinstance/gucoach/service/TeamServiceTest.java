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
import org.newinstance.gucoach.entity.Team;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * Tests the methods of the {@link TeamService}.
 *
 * @author mwalter
 */
public class TeamServiceTest extends BaseTestClass {

    @Test
    public void insertTeams() {
        final Team team1 = createTeam("FC Barcelona", 1);
        final Team team2 = createTeam("Manchester United", 2);
        final Team team3 = createTeam("Real Madrid", 3);

        final List<Team> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);
        teams.add(team3);

        teamService.save(teams);

        final List<Team> result = teamService.findAll();
        Assert.assertNotNull(result);
        Assert.assertEquals(3, result.size());
    }

    @Test
    public void updateTeam() {
        final Team team1 = createTeam("FC Sion", 1);

        final List<Team> teams = new ArrayList<>();
        teams.add(team1);

        teamService.save(teams);

        // update team name
        team1.setName("Manchester United");
        teamService.save(team1);

        final List<Team> result = teamService.findAll();
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("Manchester United", result.get(0).getName());
    }

    @Test
    public void removeTeam() {
        final Team team1 = createTeam("FC Barcelona", 1);
        final List<Team> teams = new ArrayList<>();
        teams.add(team1);

        teamService.save(teams);

        List<Team> result = teamService.findAll();
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());

        // remove teams
        teamService.deleteAll();

        result = teamService.findAll();
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void insertTeamTwice() throws Exception {
        final Team team1 = createTeam("FC Liverpool", 1);
        final Team team2 = createTeam("FC Liverpool", 2);

        final List<Team> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);

        teamService.save(teams);
    }

}
