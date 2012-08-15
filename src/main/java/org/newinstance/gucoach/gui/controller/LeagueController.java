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

package org.newinstance.gucoach.gui.controller;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.newinstance.gucoach.gui.StandingsContentProvider;
import org.newinstance.gucoach.gui.builder.CreateLeagueSceneBuilder;
import org.newinstance.gucoach.utility.MessageId;
import org.newinstance.gucoach.utility.ResourceLoader;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controls user interaction in the league tab pane.
 *
 * @author mwalter
 */
public class LeagueController implements Initializable {

    /** The window title. */
    private static final String TITLE_CREATE_LEAGUE = "label.title.createLeague";
    /** The layout of the main application window. */
    private static final String SCENE_CREATE_LEAGUE = "../fxml/windowCreateLeague.fxml";

    private ObservableList<StandingsContentProvider.StandingsDataRow> standingsData;

    @FXML
    private TableView tableViewStandings;

    @Override
    @SuppressWarnings("unchecked")
    public void initialize(final URL location, final ResourceBundle resources) {
        // set controller into provider
        ControllerProvider.getInstance().setLeagueController(this);

        // load standings data and fill standings table
        standingsData = StandingsContentProvider.getStandingsData();
        tableViewStandings.setItems(standingsData);

        // if there are no standings yet display create league message
        if (standingsData.isEmpty()) {
            final Label message = new Label();
            message.setText(ResourceLoader.getMessage(MessageId.I002.getMessageKey()));
            tableViewStandings.setPlaceholder(message);
        }

        // add listener for standings list changes
        standingsData.addListener(new ListChangeListener<StandingsContentProvider.StandingsDataRow>() {

            @Override
            public void onChanged(final Change<? extends StandingsContentProvider.StandingsDataRow> change) {
                tableViewStandings.setItems(change.getList());
            }
        });

    }

    /**
     * Builds and shows the dialogue to create a new league.
     *
     * @param event the action event
     */
    public void showCreateLeagueWindow(final ActionEvent event) {
        // build dialogue with builder
        final Parent root = new CreateLeagueSceneBuilder().buildScene();

        // build dialogue with FXML
        //        Parent root = null;
        //        try {
        //            root = FXMLLoader.load(getClass().getResource(SCENE_CREATE_LEAGUE), ResourceBundle.getBundle("ApplicationResources"));
        //        } catch (final IOException e) {
        //            e.printStackTrace();
        //        }

        final Scene scene = new Scene(root);
        scene.getStylesheets().add("stylesheet.css");
        final Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(ResourceLoader.getResource(TITLE_CREATE_LEAGUE));
        stage.show();
    }

    public void editFixture(final ActionEvent event) {

    }

}
