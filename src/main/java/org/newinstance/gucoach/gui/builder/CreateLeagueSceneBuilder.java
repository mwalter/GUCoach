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

package org.newinstance.gucoach.gui.builder;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFieldBuilder;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.ColumnConstraintsBuilder;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.GridPaneBuilder;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;
import org.newinstance.gucoach.model.Team;
import org.newinstance.gucoach.utility.ResourceLoader;
import org.newinstance.gucoach.utility.TextInputHelper;

/**
 * Builds the ui components related to the league.
 *
 * @author mwalter
 */
public final class CreateLeagueSceneBuilder extends AbstractSceneBuilder {

    @Override
    public Parent buildScene() {
        final BorderPane parent = BorderPaneBuilder.create().build();

        // text fields for team names
        final TextField tfTeamRnk1 = TextFieldBuilder.create().build();
        final TextField tfTeamRnk2 = TextFieldBuilder.create().build();
        final TextField tfTeamRnk3 = TextFieldBuilder.create().build();
        final TextField tfTeamRnk4 = TextFieldBuilder.create().build();
        final TextField tfTeamRnk5 = TextFieldBuilder.create().build();
        final TextField tfTeamRnk6 = TextFieldBuilder.create().build();
        final TextField tfTeamRnk7 = TextFieldBuilder.create().build();
        final TextField tfTeamRnk8 = TextFieldBuilder.create().build();
        final TextField tfTeamRnk9 = TextFieldBuilder.create().build();
        final TextField tfTeamRnk10 = TextFieldBuilder.create().build();
        final TextField tfTeamRnk11 = TextFieldBuilder.create().build();
        final TextField tfTeamRnk12 = TextFieldBuilder.create().build();

        // limit text input length
        TextInputHelper.addLengthListener(tfTeamRnk1, 50);
        TextInputHelper.addLengthListener(tfTeamRnk2, 50);
        TextInputHelper.addLengthListener(tfTeamRnk3, 50);
        TextInputHelper.addLengthListener(tfTeamRnk4, 50);
        TextInputHelper.addLengthListener(tfTeamRnk5, 50);
        TextInputHelper.addLengthListener(tfTeamRnk6, 50);
        TextInputHelper.addLengthListener(tfTeamRnk7, 50);
        TextInputHelper.addLengthListener(tfTeamRnk8, 50);
        TextInputHelper.addLengthListener(tfTeamRnk9, 50);
        TextInputHelper.addLengthListener(tfTeamRnk10, 50);
        TextInputHelper.addLengthListener(tfTeamRnk11, 50);
        TextInputHelper.addLengthListener(tfTeamRnk12, 50);

        final Label title = LabelBuilder.create().text(ResourceLoader.getResource("label.title.createLeague")).style("-fx-font: NORMAL 16 Tahoma;").build();
        final VBox vBoxTop = VBoxBuilder.create().children().spacing(5.0).padding(new Insets(10, 10, 10, 10)).children(title).build();

        // create league button
        final Button buttonCreate = ButtonBuilder.create().text(ResourceLoader.getResource("button.createLeague")).onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                // TODO validate and save data to database
                final Team team1 = new Team();
                team1.setStartPos(1);
                team1.setName(tfTeamRnk1.getText());
            }
        }).prefWidth(100.0).build();

        // cancel dialogue button
        final Button buttonCancel = ButtonBuilder.create().text(ResourceLoader.getResource("button.cancel")).onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                final Stage stage = (Stage) parent.getScene().getWindow();
                stage.close();
            }
        }).prefWidth(100.0).build();

        final HBox hBoxButtonArea = HBoxBuilder.create().alignment(Pos.CENTER).spacing(5.0).padding(new Insets(20)).children(buttonCreate, buttonCancel).build();

        final ColumnConstraints ccWidth20 = ColumnConstraintsBuilder.create().prefWidth(50.0).build();
        final ColumnConstraints ccWidth180 = ColumnConstraintsBuilder.create().prefWidth(150.0).build();

        // labels for team names
        final Label labelTeamRnk1 = LabelBuilder.create().text(ResourceLoader.getResource("label.team.rank1")).build();
        final Label labelTeamRnk2 = LabelBuilder.create().text(ResourceLoader.getResource("label.team.rank2")).build();
        final Label labelTeamRnk3 = LabelBuilder.create().text(ResourceLoader.getResource("label.team.rank3")).build();
        final Label labelTeamRnk4 = LabelBuilder.create().text(ResourceLoader.getResource("label.team.rank4")).build();
        final Label labelTeamRnk5 = LabelBuilder.create().text(ResourceLoader.getResource("label.team.rank5")).build();
        final Label labelTeamRnk6 = LabelBuilder.create().text(ResourceLoader.getResource("label.team.rank6")).build();
        final Label labelTeamRnk7 = LabelBuilder.create().text(ResourceLoader.getResource("label.team.rank7")).build();
        final Label labelTeamRnk8 = LabelBuilder.create().text(ResourceLoader.getResource("label.team.rank8")).build();
        final Label labelTeamRnk9 = LabelBuilder.create().text(ResourceLoader.getResource("label.team.rank9")).build();
        final Label labelTeamRnk10 = LabelBuilder.create().text(ResourceLoader.getResource("label.team.rank10")).build();
        final Label labelTeamRnk11 = LabelBuilder.create().text(ResourceLoader.getResource("label.team.rank11")).build();
        final Label labelTeamRnk12 = LabelBuilder.create().text(ResourceLoader.getResource("label.team.rank12")).build();

        final GridPane gpTeams = GridPaneBuilder.create().alignment(Pos.TOP_LEFT).hgap(5.0).vgap(5.0).columnConstraints(ccWidth20, ccWidth180).build();
        gpTeams.add(labelTeamRnk1, 0, 0);
        gpTeams.add(tfTeamRnk1, 1, 0);
        gpTeams.add(labelTeamRnk2, 0, 1);
        gpTeams.add(tfTeamRnk2, 1, 1);
        gpTeams.add(labelTeamRnk3, 0, 2);
        gpTeams.add(tfTeamRnk3, 1, 2);
        gpTeams.add(labelTeamRnk4, 0, 3);
        gpTeams.add(tfTeamRnk4, 1, 3);
        gpTeams.add(labelTeamRnk5, 0, 4);
        gpTeams.add(tfTeamRnk5, 1, 4);
        gpTeams.add(labelTeamRnk6, 0, 5);
        gpTeams.add(tfTeamRnk6, 1, 5);
        gpTeams.add(labelTeamRnk7, 0, 6);
        gpTeams.add(tfTeamRnk7, 1, 6);
        gpTeams.add(labelTeamRnk8, 0, 7);
        gpTeams.add(tfTeamRnk8, 1, 7);
        gpTeams.add(labelTeamRnk9, 0, 8);
        gpTeams.add(tfTeamRnk9, 1, 8);
        gpTeams.add(labelTeamRnk10, 0, 9);
        gpTeams.add(tfTeamRnk10, 1, 9);
        gpTeams.add(labelTeamRnk11, 0, 10);
        gpTeams.add(tfTeamRnk11, 1, 10);
        gpTeams.add(labelTeamRnk12, 0, 11);
        gpTeams.add(tfTeamRnk12, 1, 11);

        final ColumnConstraints ccWidth80 = ColumnConstraintsBuilder.create().prefWidth(80.0).build();
        final ColumnConstraints ccWidth120 = ColumnConstraintsBuilder.create().prefWidth(120.0).build();

        final Label labelMatchday = LabelBuilder.create().text(ResourceLoader.getResource("label.firstMatchday")).build();
        final TextField tfMatchday = TextFieldBuilder.create().id("idTfMatchday").build();

        final GridPane gpMatchday = GridPaneBuilder.create().alignment(Pos.TOP_LEFT).hgap(5.0).vgap(5.0).columnConstraints(ccWidth80, ccWidth120).build();
        gpMatchday.add(labelMatchday, 0, 0);
        gpMatchday.add(tfMatchday, 1, 0);

        final VBox vBoxCenter = VBoxBuilder.create().children(gpTeams, gpMatchday, hBoxButtonArea).spacing(5.0).padding(new Insets(10, 10, 10, 10)).build();

        parent.setTop(vBoxTop);
        parent.setCenter(vBoxCenter);
        return parent;
    }
}
