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
import org.junit.Test;
import org.newinstance.gucoach.base.BaseTest;
import org.newinstance.gucoach.entity.Team;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests the methods of the {@link TeamService}.
 *
 * @author mwalter
 */
public class TeamServiceTest extends BaseTest {

    @Test
    public void insertTeams() {
        final Team team1 = createTeam("FC Barcelona", 1);
        final Team team2 = createTeam("Manchester United", 2);
        final Team team3 = createTeam("Real Madrid", 3);

        final List<Team> teams = new ArrayList<Team>();
        teams.add(team1);
        teams.add(team2);
        teams.add(team3);

        teamService.insertTeams(teams);

        final List<Team> result = teamService.findAllTeams();
        Assert.assertNotNull(result);
        Assert.assertEquals(3, result.size());
    }

    @Test
    public void updateTeam() {
        final Team team1 = createTeam("FC Sion", 1);

        final List<Team> teams = new ArrayList<Team>();
        teams.add(team1);

        teamService.insertTeams(teams);

        // update team name
        team1.setName("Manchester United");
        teamService.updateTeam(team1);

        final List<Team> result = teamService.findAllTeams();
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("Manchester United", result.get(0).getName());
    }

    @Test
    public void removeTeam() {
        final Team team1 = createTeam("FC Barcelona", 1);
        final List<Team> teams = new ArrayList<Team>();
        teams.add(team1);

        teamService.insertTeams(teams);

        List<Team> result = teamService.findAllTeams();
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());

        // remove teams
        teamService.removeAllTeams();

        result = teamService.findAllTeams();
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }

    @Test(expected = PersistenceException.class)
    public void insertTeamTwice() throws Exception {
        final Team team1 = createTeam("FC Liverpool", 1);
        final Team team2 = createTeam("FC Liverpool", 2);

        final List<Team> teams = new ArrayList<Team>();
        teams.add(team1);
        teams.add(team2);

        teamService.insertTeams(teams);
    }

}
