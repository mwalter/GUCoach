/*
 * GUCoach - your personal coach for Goalunited (tm).
 * Licensed under General Public License v3 (GPLv3)
 * newInstance.org, 2013
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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newinstance.gucoach.entity.Fixture;
import org.newinstance.gucoach.entity.Team;
import org.newinstance.gucoach.service.FixtureService;
import org.newinstance.gucoach.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains all league and related fixture data.
 *
 * @author mwalter
 */
@Component
public class LeagueModel {

    /** The log4j logger. */
    private static final Logger LOGGER = LogManager.getLogger(LeagueModel.class.getName());

    @Autowired
    private FixtureService fixtureService;

    @Autowired
    private TeamService teamService;

    /** Holds all teams. */
    private ObservableList<Team> teams = FXCollections.observableArrayList(new ArrayList<Team>());

    /** Holds all fixtures. */
    private ObservableList<Fixture> fixtures = FXCollections.observableArrayList(new ArrayList<Fixture>());

    @PostConstruct
    protected void init() {
        LOGGER.info("Initializing league model with teams and fixtures.");
        this.setTeams(teamService.findAllTeams());
        this.setFixtures(fixtureService.findAllFixtures());
    }

    /**
     * Returns all fixtures.
     *
     * @return the observable list of fixtures
     */
    public ObservableList<Fixture> getFixtures() {
        return fixtures;
    }

    /**
     * Returns all teams.
     *
     * @return the observable list of teams
     */
    public ObservableList<Team> getTeams() {
        return teams;
    }

    /**
     * Sets all fixtures.
     *
     * @param fixtures the fixtures to set
     */
    public void setFixtures(final List<Fixture> fixtures) {
        // first remove all fixtures then add new fixture data
        this.fixtures.removeAll(this.fixtures);
        for (final Fixture fixture : fixtures) {
            this.fixtures.add(fixture);
        }
        LOGGER.info("League model has been set with {} fixtures.", fixtures.size());
    }

    /**
     * Sets all teams.
     *
     * @param teams the teams to set
     */
    public void setTeams(final List<Team> teams) {
        // first remove all teams then add new team data
        this.teams.removeAll(this.teams);
        for (final Team team : teams) {
            this.teams.add(team);
        }
        LOGGER.info("League model has been set with {} teams.", teams.size());
    }

}
