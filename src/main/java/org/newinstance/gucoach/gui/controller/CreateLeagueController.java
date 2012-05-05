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

package org.newinstance.gucoach.gui.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.lang3.StringUtils;
import org.newinstance.gucoach.model.Team;
import org.newinstance.gucoach.service.DatabaseService;
import org.newinstance.gucoach.service.DatabaseServiceImpl;
import org.newinstance.gucoach.utility.MessageId;
import org.newinstance.gucoach.utility.ResourceLoader;
import org.newinstance.gucoach.utility.TextInputHelper;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controls the creation of a new league.
 *
 * @author mwalter
 */
public class CreateLeagueController implements Initializable {

    /** The pattern of the date to be entered. */
    private static final String DATE_PATTERN = "dd.MM.yyyy";

    private Tooltip searchErrorTooltip = new Tooltip();
    private Timeline searchErrorTooltipHidder = null;

    private DatabaseService databaseService = new DatabaseServiceImpl();
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

    public void createLeague(final ActionEvent event) {
        // get matchday
        final String matchdayString = tfMatchday.getText();
        try {
            final SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
            final Date matchdayDate = format.parse(matchdayString);
        } catch (final ParseException e) {
            // invalid date format
            showError(ResourceLoader.getMessage(MessageId.V004.getMessageKey()));
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
                    showError(ResourceLoader.getMessage(MessageId.V005.getMessageKey()));
                    return;
                }
                // team names must be unique
                if (teamNames.contains(teamName)) {
                    showError(ResourceLoader.getMessage(MessageId.V006.getMessageKey(), teamName));
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
        Task<Void> saveTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    databaseService.insertTeams(teams);
                } catch (final Exception e) {
                    // TODO error handling with bean validation?
                    e.printStackTrace();
                }
                return null;
            }
        };

        saveTask.stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(final ObservableValue<? extends Worker.State> source, final Worker.State oldState, final Worker.State newState) {
                if (newState.equals(Worker.State.SUCCEEDED)) {
                    // close window after database work is done
                    final Stage stage = (Stage) root.getScene().getWindow();
                    stage.close();
                }
                if (newState.equals(Worker.State.FAILED)) {
                    // TODO print error and exit
                }
            }
        });

        new Thread(saveTask).start();
    }

    // TODO should be moved to an utility class
    private void showError(final String message) {
        searchErrorTooltip.setText(message);
        if (searchErrorTooltipHidder != null) {
            searchErrorTooltipHidder.stop();
        }
        if (message != null) {
            final Point2D toolTipPos = tfMatchday.localToScene(0, tfMatchday.getLayoutBounds().getHeight());
            final double x = toolTipPos.getX() + tfMatchday.getScene().getX() + tfMatchday.getScene().getWindow().getX();
            final double y = toolTipPos.getY() + tfMatchday.getScene().getY() + tfMatchday.getScene().getWindow().getY();
            searchErrorTooltip.show(tfMatchday.getScene().getWindow(), x, y);
            searchErrorTooltipHidder = new Timeline();
            searchErrorTooltipHidder.getKeyFrames().add(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
                public void handle(ActionEvent t) {
                    searchErrorTooltip.hide();
                    searchErrorTooltip.setText(null);
                }
            }));
            searchErrorTooltipHidder.play();
        } else {
            searchErrorTooltip.hide();
        }
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
        team.setPosition(position);
        team.setName(name);
        team.setGoalsFor(0);
        team.setGoalsAgainst(0);
        team.setMatchesWon(0);
        team.setMatchesDrawn(0);
        team.setMatchesLost(0);
        team.setPoints(0);
        team.setStrength(0f);
        return team;
    }
}