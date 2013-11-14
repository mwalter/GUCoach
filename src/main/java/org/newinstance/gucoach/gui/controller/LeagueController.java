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

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newinstance.gucoach.gui.StandingsDataRow;
import org.newinstance.gucoach.gui.model.StandingsModel;
import org.newinstance.gucoach.utility.MessageId;
import org.newinstance.gucoach.utility.ResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Controls user interaction in the league tab pane.
 *
 * @author mwalter
 */
@Component
public class LeagueController {

    /** The log4j logger. */
    private static final Logger LOGGER = LogManager.getLogger(LeagueController.class.getName());

    @Autowired
    private StandingsModel standingsModel;

    @FXML
    private TableView<StandingsDataRow> tableViewStandings;

    @FXML
    public void initialize() {
        LOGGER.info("LeagueController is accessing standings model.");
        tableViewStandings.setItems(standingsModel.getStandingsData());

        // if there are no players yet display import player data message
        if (tableViewStandings.getItems().isEmpty()) {
            final Label message = new Label();
            message.setText(ResourceLoader.getMessage(MessageId.I001.getMessageKey()));
            tableViewStandings.setPlaceholder(message);
        }
    }

}
