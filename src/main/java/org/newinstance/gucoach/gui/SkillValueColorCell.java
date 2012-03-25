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

import javafx.scene.control.TableCell;

/**
 * Colours table cells depending on the numeric value. Low values are coloured in red, high values in green shades.
 *
 * @author mwalter
 */
public class SkillValueColorCell extends TableCell<PlayerDataRow, Integer> {

    @Override
    public void updateItem(final Integer item, final boolean empty) {
        super.updateItem(item, empty);
        if (!isEmpty()) {
            if (item == 100) {
                this.setStyle("-fx-background-color: #00FF00");
            } else if (item > 90) {
                this.setStyle("-fx-background-color: #33FF00");
            } else if (item > 80) {
                this.setStyle("-fx-background-color: #66FF00");
            } else if (item > 70) {
                this.setStyle("-fx-background-color: #99FF00");
            } else if (item > 60) {
                this.setStyle("-fx-background-color: #CCFF00");
            } else if (item > 50) {
                this.setStyle("-fx-background-color: #FFFF00");
            } else if (item > 40) {
                this.setStyle("-fx-background-color: #FFCC00");
            } else if (item > 30) {
                this.setStyle("-fx-background-color: #FF9900");
            } else if (item > 20) {
                this.setStyle("-fx-background-color: #FF6600");
            } else if (item > 10) {
                this.setStyle("-fx-background-color: #FF3300");
            } else {
                this.setStyle("-fx-background-color: #FF0000");
            }
            setText(Integer.toString(item));
        }
    }
}
