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

package org.newinstance.gucoach.gui.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Test;
import org.newinstance.gucoach.base.BaseTest;
import org.newinstance.gucoach.entity.Fixture;
import org.newinstance.gucoach.entity.Team;
import org.newinstance.gucoach.gui.StandingsDataRow;

/**
 * Tests methods of class {@link org.newinstance.gucoach.gui.model.StandingsModel}.
 *
 * @author mwalter
 */
public class StandingsModelTest extends BaseTest {

    @Test
    public void getStandingsData() {
        createSomeTeamsAndFixtures();
        final ObservableList<StandingsDataRow> standingsList = standingsModel.getStandingsData();
        Assert.assertNotNull(standingsList);
        Assert.assertFalse(standingsList.isEmpty());
        for (StandingsDataRow standingsDataRow : standingsList) {
            System.out.println(standingsDataRow.toString());
        }
    }

    /** Creates some test teams and fixtures. */
    private void createSomeTeamsAndFixtures() {
        final List<Team> newTeams = new ArrayList<Team>();
        final Team team1 = new Team();
        team1.setName("Acapulco");
        newTeams.add(team1);
        final Team team2 = new Team();
        team2.setName("Miami Beach");
        newTeams.add(team2);
        final Team team3 = new Team();
        team3.setName("Varese");
        newTeams.add(team3);

        // insert teams first and get them back from database in order to get team ids
        teamService.insertTeams(newTeams);
        leagueModel.setTeams(teamService.findAllTeams());
        final List<Team> teams = leagueModel.getTeams();

        final List<Fixture> fixtures = new ArrayList<Fixture>();
        // Acapulco - Miami Beach 2:1
        final Fixture fixture1 = new Fixture();
        fixture1.setHomeTeam(teams.get(0));
        fixture1.setAwayTeam(teams.get(1));
        fixture1.setMatchDay(new Date());
        fixture1.setMatchResult("2:1");
        // Miami Beach - Varese 0:3
        final Fixture fixture2 = new Fixture();
        fixture2.setHomeTeam(teams.get(1));
        fixture2.setAwayTeam(teams.get(2));
        fixture2.setMatchDay(new Date());
        fixture2.setMatchResult("0:3");
        // Varese - Acapulco 1:1
        final Fixture fixture3 = new Fixture();
        fixture3.setHomeTeam(teams.get(2));
        fixture3.setAwayTeam(teams.get(0));
        fixture3.setMatchDay(new Date());
        fixture3.setMatchResult("1:1");

        fixtures.add(fixture1);
        fixtures.add(fixture2);
        fixtures.add(fixture3);
        fixtureService.insertFixtures(fixtures);
        leagueModel.setFixtures(fixtureService.findAllFixtures());
    }
}
