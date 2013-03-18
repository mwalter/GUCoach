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

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.newinstance.gucoach.gui.StandingsContentProvider;
import org.newinstance.gucoach.gui.StandingsDataRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Controls user interaction in the league tab pane.
 *
 * @author mwalter
 */
@Component
public class LeagueController {

    @Autowired
    private StandingsContentProvider standingsContentProvider;

    /** The window title. */
    private static final String TITLE_CREATE_LEAGUE = "label.title.createLeague";
    /** The layout of the main application window. */
    private static final String SCENE_CREATE_LEAGUE = "../fxml/windowCreateLeague.fxml";

    private ObservableList<StandingsDataRow> standingsData;

    @FXML
    private TableView tableViewStandings;

    public void editFixture(final ActionEvent event) {
    }

}
