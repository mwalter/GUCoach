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

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.newinstance.gucoach.entity.Team;
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
        final BorderPane parent = new BorderPane();

        // text fields for team names
        final TextField tfTeamRnk1 = new TextField();
        final TextField tfTeamRnk2 = new TextField();
        final TextField tfTeamRnk3 = new TextField();
        final TextField tfTeamRnk4 = new TextField();
        final TextField tfTeamRnk5 = new TextField();
        final TextField tfTeamRnk6 = new TextField();
        final TextField tfTeamRnk7 = new TextField();
        final TextField tfTeamRnk8 = new TextField();
        final TextField tfTeamRnk9 = new TextField();
        final TextField tfTeamRnk10 = new TextField();
        final TextField tfTeamRnk11 = new TextField();
        final TextField tfTeamRnk12 = new TextField();

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

        final Label title = new Label();
        title.setText(ResourceLoader.getResource("label.title.createLeague"));
        title.setStyle("-fx-font: NORMAL 16 Tahoma;");
        final VBox vBoxTop = new VBox();
        vBoxTop.setSpacing(5.0);
        vBoxTop.setPadding(new Insets(10, 10, 10, 10));
        vBoxTop.getChildren().add(title);

        // create league button
        final Button buttonCreate = new Button();
        buttonCreate.setText(ResourceLoader.getResource("button.createLeague"));
        buttonCreate.setOnAction(event -> {
            // TODO validate and save data to database
            final Team team1 = new Team();
            team1.setStartPos(1);
            team1.setName(tfTeamRnk1.getText());
        });
        buttonCreate.setPrefWidth(100.0);

        // cancel dialogue button
        final Button buttonCancel = new Button();
        buttonCancel.setText(ResourceLoader.getResource("button.cancel"));
        buttonCancel.setOnAction(event -> {
            final Stage stage = (Stage) parent.getScene().getWindow();
            stage.close();
        });
        buttonCancel.setPrefWidth(100.0);

        final HBox hBoxButtonArea = new HBox();
        hBoxButtonArea.setAlignment(Pos.CENTER);
        hBoxButtonArea.setSpacing(5.0);
        hBoxButtonArea.setPadding(new Insets(20));
        hBoxButtonArea.getChildren().addAll(buttonCreate, buttonCancel);

        final ColumnConstraints ccWidth20 = new ColumnConstraints();
        ccWidth20.setPrefWidth(50.0);
        final ColumnConstraints ccWidth180 = new ColumnConstraints();
        ccWidth180.setPrefWidth(150.0);

        // labels for team names
        final Label labelTeamRnk1 = new Label();
        labelTeamRnk1.setText(ResourceLoader.getResource("label.team.rank1"));
        final Label labelTeamRnk2 = new Label();
        labelTeamRnk2.setText(ResourceLoader.getResource("label.team.rank2"));
        final Label labelTeamRnk3 = new Label();
        labelTeamRnk3.setText(ResourceLoader.getResource("label.team.rank3"));
        final Label labelTeamRnk4 = new Label();
        labelTeamRnk4.setText(ResourceLoader.getResource("label.team.rank4"));
        final Label labelTeamRnk5 = new Label();
        labelTeamRnk5.setText(ResourceLoader.getResource("label.team.rank5"));
        final Label labelTeamRnk6 = new Label();
        labelTeamRnk6.setText(ResourceLoader.getResource("label.team.rank6"));
        final Label labelTeamRnk7 = new Label();
        labelTeamRnk7.setText(ResourceLoader.getResource("label.team.rank7"));
        final Label labelTeamRnk8 = new Label();
        labelTeamRnk8.setText(ResourceLoader.getResource("label.team.rank8"));
        final Label labelTeamRnk9 = new Label();
        labelTeamRnk9.setText(ResourceLoader.getResource("label.team.rank9"));
        final Label labelTeamRnk10 = new Label();
        labelTeamRnk10.setText(ResourceLoader.getResource("label.team.rank10"));
        final Label labelTeamRnk11 = new Label();
        labelTeamRnk11.setText(ResourceLoader.getResource("label.team.rank11"));
        final Label labelTeamRnk12 = new Label();
        labelTeamRnk12.setText(ResourceLoader.getResource("label.team.rank12"));

        final GridPane gpTeams = new GridPane();
        gpTeams.setAlignment(Pos.TOP_LEFT);
        gpTeams.setHgap(5.0);
        gpTeams.setVgap(5.0);
        gpTeams.getColumnConstraints().addAll(ccWidth20, ccWidth180);
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

        final ColumnConstraints ccWidth80 = new ColumnConstraints();
        ccWidth80.setPrefWidth(80.0);
        final ColumnConstraints ccWidth120 = new ColumnConstraints();
        ccWidth120.setPrefWidth(120.0);

        final Label labelMatchday = new Label();
        labelMatchday.setText(ResourceLoader.getResource("label.firstMatchday"));
        final TextField tfMatchday = new TextField();
        tfMatchday.setId("idTfMatchday");

        final GridPane gpMatchday = new GridPane();
        gpMatchday.setAlignment(Pos.TOP_LEFT);
        gpMatchday.setHgap(5.0);
        gpMatchday.setVgap(5.0);
        gpMatchday.getColumnConstraints().addAll(ccWidth80, ccWidth120);
        gpMatchday.add(labelMatchday, 0, 0);
        gpMatchday.add(tfMatchday, 1, 0);

        final VBox vBoxCenter = new VBox();
        vBoxCenter.getChildren().addAll(gpTeams, gpMatchday, hBoxButtonArea);
        vBoxCenter.setSpacing(5.0);
        vBoxCenter.setPadding(new Insets(10, 10, 10, 10));

        parent.setTop(vBoxTop);
        parent.setCenter(vBoxCenter);
        return parent;
    }
}
