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

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import org.newinstance.gucoach.model.Team;
import org.newinstance.gucoach.service.DatabaseService;
import org.newinstance.gucoach.service.DatabaseServiceImpl;
import org.newinstance.gucoach.utility.DateHelper;
import org.newinstance.gucoach.utility.MessageId;
import org.newinstance.gucoach.utility.ResourceLoader;
import org.newinstance.gucoach.utility.TextInputHelper;

import java.net.URL;
import java.text.ParseException;
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

    /** The database service. */
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
    @FXML
    private VBox vbContent;

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
        Date matchday = null;
        try {
            matchday = DateHelper.parseDate(tfMatchday.getText());
        } catch (final ParseException e) {
            // invalid date format
            final TextArea message = new TextArea();
            message.setMaxWidth(Integer.MAX_VALUE);
            message.setWrapText(true);
            message.setEditable(false);
            message.setText(ResourceLoader.getMessage(MessageId.V004.getMessageKey()));
            vbContent.getChildren().add(message);
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
                    // showError(ResourceLoader.getMessage(MessageId.V005.getMessageKey()));
                    return;
                }
                // team names must be unique
                if (teamNames.contains(teamName)) {
                    // showError(ResourceLoader.getMessage(MessageId.V006.getMessageKey(), teamName));
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
