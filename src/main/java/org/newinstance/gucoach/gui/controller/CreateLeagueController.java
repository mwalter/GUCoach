/*
 * GUCoach - your personal coach for Goalunited (tm).
 * Licensed under General Public License v3 (GPLv3)
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

package org.newinstance.gucoach.gui.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newinstance.gucoach.entity.Fixture;
import org.newinstance.gucoach.entity.Team;
import org.newinstance.gucoach.gui.model.LeagueModel;
import org.newinstance.gucoach.service.FixtureService;
import org.newinstance.gucoach.service.TeamService;
import org.newinstance.gucoach.utility.DateHelper;
import org.newinstance.gucoach.utility.FixtureGenerator;
import org.newinstance.gucoach.utility.TextInputHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Controls the creation of a new league.
 *
 * @author mwalter
 */
@Component
public class CreateLeagueController implements Initializable {

    /** The log4j logger. */
    private static final Logger LOGGER = LogManager.getLogger(CreateLeagueController.class.getName());

    @Autowired
    private MainController mainController;

    @Autowired
    private LeagueModel leagueModel;

    @Autowired
    private FixtureService fixtureService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private FixtureGenerator fixtureGenerator;

    @Autowired
    private SpringFxmlLoader loader;

    @FXML
    private BorderPane root;

    @FXML
    private GridPane gpTeamNames;

    @FXML
    private TextField tfMatchday;

    @FXML
    private TextField tfTeam1;

    @FXML
    private TextField tfTeam2;

    @FXML
    private TextField tfTeam3;

    @FXML
    private TextField tfTeam4;

    @FXML
    private TextField tfTeam5;

    @FXML
    private TextField tfTeam6;

    @FXML
    private TextField tfTeam7;

    @FXML
    private TextField tfTeam8;

    @FXML
    private TextField tfTeam9;

    @FXML
    private TextField tfTeam10;

    @FXML
    private TextField tfTeam11;

    @FXML
    private TextField tfTeam12;

    @Override
    @SuppressWarnings("unchecked")
    public void initialize(final URL location, final ResourceBundle resources) {
        TextInputHelper.addLengthListener(tfTeam1, 50);
        TextInputHelper.addLengthListener(tfTeam2, 50);
        TextInputHelper.addLengthListener(tfTeam3, 50);
        TextInputHelper.addLengthListener(tfTeam4, 50);
        TextInputHelper.addLengthListener(tfTeam5, 50);
        TextInputHelper.addLengthListener(tfTeam6, 50);
        TextInputHelper.addLengthListener(tfTeam7, 50);
        TextInputHelper.addLengthListener(tfTeam8, 50);
        TextInputHelper.addLengthListener(tfTeam9, 50);
        TextInputHelper.addLengthListener(tfTeam10, 50);
        TextInputHelper.addLengthListener(tfTeam11, 50);
        TextInputHelper.addLengthListener(tfTeam12, 50);
    }

    /**
     * Cancels the create league process and closes the window.
     *
     * @param event the event
     */
    public void closeWindow(final ActionEvent event) {
        final Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    /**
     * Creates a new league.
     *
     * @param event the action event
     */
    public void createLeague(final ActionEvent event) {
        // get matchday
        Date matchday = null;
        try {
            matchday = DateHelper.parseDate(tfMatchday.getText());
        } catch (final ParseException e) {
            // invalid date format
            // TODO error message
            // Dialogs.showErrorDialog((Stage) root.getScene().getWindow(), ResourceLoader.getMessage(MessageId.V004.getMessageKey()), "Es ist ein Fehler aufgetreten", null);
            return;
        }

        // collect team names from input text fields
        final List<String> teamNames = new ArrayList<String>();
        final ObservableList<Node> gridPaneChildren = gpTeamNames.getChildren();
        for (final Node child : gridPaneChildren) {
            if (child instanceof TextField) {
                final String teamName = ((TextField) child).getText().trim();
                // team names must not be empty
                if (StringUtils.isBlank(teamName)) {
                    // TODO error message
                    // Dialogs.showErrorDialog((Stage) root.getScene().getWindow(), ResourceLoader.getMessage(MessageId.V005.getMessageKey()), "Es ist ein Fehler aufgetreten", null);
                    return;
                }
                // team names must be unique
                if (teamNames.contains(teamName)) {
                    // TODO error message
                    // Dialogs.showErrorDialog((Stage) root.getScene().getWindow(), ResourceLoader.getMessage(MessageId.V006.getMessageKey()), "Es ist ein Fehler aufgetreten", null);
                    return;
                }
                teamNames.add(teamName);
            }
        }

        // initialise team entities
        final List<Team> teams = new ArrayList<Team>();
        int position = 1;
        for (final String teamName : teamNames) {
            final Team team = createTeam(position, teamName);
            teams.add(team);
            position++;
        }

        // use task to insert teams into database
        final Task<Void> saveTeamsTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    LOGGER.info("Inserting teams into database.");
                    teamService.insertTeams(teams);
                } catch (final Exception e) {
                    // TODO error handling with bean validation?
                    e.printStackTrace();
                }
                return null;
            }
        };

        saveTeamsTask.stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(final ObservableValue<? extends Worker.State> source, final Worker.State oldState, final Worker.State newState) {
                if (newState.equals(Worker.State.SUCCEEDED)) {
                    // calculate fixtures
                    createFixtures();
                    // close window after database work is done
                    final Stage stage = (Stage) root.getScene().getWindow();
                    stage.close();
                }
                if (newState.equals(Worker.State.FAILED)) {
                    // TODO print error and exit
                }
            }
        });

        new Thread(saveTeamsTask).start();
    }

    /** Calculates fixtures, creates fixture instances and inserts them into the database. */
    private void createFixtures() {
        final List<Team> teams = teamService.findAllTeams();
        // create a map where team id is the key
        final Map<Long, Team> teamMap = new HashMap<Long, Team>();
        final List<Long> teamIds = new ArrayList<Long>();
        for (final Team team : teams) {
            teamIds.add(team.getId());
            teamMap.put(team.getId(), team);
        }

        // get first matchday
        Calendar matchday = null;
        try {
            matchday = DateHelper.convertToCalendar(DateHelper.parseDate(tfMatchday.getText()));
        } catch (final ParseException e) {
            // shouldn't never happen here because it has been checked before
        }

        // generate fixtures
        final Map<Calendar, Map<Integer, Long[]>> result = fixtureGenerator.generateFixtures(teamIds, matchday);

        final List<Fixture> fixtures = new ArrayList<Fixture>();
        for (final Map.Entry<Calendar, Map<Integer, Long[]>> matchdayEntry : result.entrySet()) {
            final Map<Integer, Long[]> matchesMap = matchdayEntry.getValue();
            for (final Map.Entry<Integer, Long[]> matchesEntry : matchesMap.entrySet()) {
                // create new fixture entities
                final Fixture fixture = new Fixture();
                fixture.setMatchDay(matchdayEntry.getKey().getTime());
                fixture.setHomeTeam(teamMap.get(matchesEntry.getValue()[0]));
                fixture.setAwayTeam(teamMap.get(matchesEntry.getValue()[1]));
                fixtures.add(fixture);
            }
        }

        // use task to insert fixtures into database
        final Task<Void> saveFixturesTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    LOGGER.info("Inserting fixtures into database.");
                    fixtureService.insertFixtures(fixtures);
                } catch (final Exception e) {
                    // TODO error handling with bean validation?
                    e.printStackTrace();
                }
                return null;
            }
        };

        saveFixturesTask.stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(final ObservableValue<? extends Worker.State> source, final Worker.State oldState, final Worker.State newState) {
                if (newState.equals(Worker.State.SUCCEEDED)) {
                    // set fixtures into model and notify main controller
                    LOGGER.info("Updating fixture model.");
                    leagueModel.setFixtures(fixtures);
                    mainController.handleLeagueUpdate();
                }
                if (newState.equals(Worker.State.FAILED)) {
                    // TODO print error and exit
                }
            }
        });

        new Thread(saveFixturesTask).start();
    }

    /**
     * Creates a new team entity. Besides starting position and name all values are set to zero.
     *
     * @param position the team's starting position in the league
     * @param name the unique team's name
     * @return the new team
     */
    private Team createTeam(final int position, final String name) {
        final Team team = new Team();
        team.setStartPos(position);
        team.setName(name);
        team.setStrength(0f);
        return team;
    }
}
