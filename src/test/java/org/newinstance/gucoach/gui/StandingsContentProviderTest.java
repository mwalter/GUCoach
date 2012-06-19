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

package org.newinstance.gucoach.gui;

import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.newinstance.gucoach.model.Fixture;
import org.newinstance.gucoach.model.Team;
import org.newinstance.gucoach.service.DatabaseService;
import org.newinstance.gucoach.service.DatabaseServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Tests methods of class {@link StandingsContentProvider}.
 *
 * @author mwalter
 */
public class StandingsContentProviderTest {

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
    public void testGetStandingsData() {
        createSomeTeamsAndFixtures();
        final ObservableList<StandingsContentProvider.StandingsDataRow> standingsList = StandingsContentProvider.getStandingsData();
        Assert.assertNotNull(standingsList);
        Assert.assertFalse(standingsList.isEmpty());
        for (StandingsContentProvider.StandingsDataRow standingsDataRow : standingsList) {
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
        databaseService.insertTeams(newTeams);
        final List<Team> teams = databaseService.findAllTeams();

        // Acapulco - Miami Beach 2:1
        final Fixture fixture1 = new Fixture();
        fixture1.setHomeTeamId(teams.get(0).getId());
        fixture1.setAwayTeamId(teams.get(1).getId());
        fixture1.setMatchDay(new Date());
        fixture1.setMatchResult("2:1");
        // Miami Beach - Varese 0:3
        final Fixture fixture2 = new Fixture();
        fixture2.setHomeTeamId(teams.get(1).getId());
        fixture2.setAwayTeamId(teams.get(2).getId());
        fixture2.setMatchDay(new Date());
        fixture2.setMatchResult("0:3");
        // Varese - Acapulco 1:1
        final Fixture fixture3 = new Fixture();
        fixture3.setHomeTeamId(teams.get(2).getId());
        fixture3.setAwayTeamId(teams.get(0).getId());
        fixture3.setMatchDay(new Date());
        fixture3.setMatchResult("1:1");

        databaseService.insertFixture(fixture1);
        databaseService.insertFixture(fixture2);
        databaseService.insertFixture(fixture3);
    }
}
