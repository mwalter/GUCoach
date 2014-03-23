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

package org.newinstance.gucoach.gui.component;

import javafx.scene.control.TableCell;
import org.newinstance.gucoach.entity.Player;

/**
 * Colours table cells depending on the numeric value. Low values are coloured in red, high values in green shades.
 *
 * @author mwalter
 */
public class SkillValueColorCell extends TableCell<Player, Integer> {

    @Override
    public void updateItem(final Integer item, final boolean empty) {
        super.updateItem(item, empty);
        if (!isEmpty()) {
            if (item == 100) {
                this.setStyle("-fx-background-color: #A6FFA6");
            } else if (item > 90) {
                this.setStyle("-fx-background-color: #B8FFA6");
            } else if (item > 80) {
                this.setStyle("-fx-background-color: #CAFFA6");
            } else if (item > 70) {
                this.setStyle("-fx-background-color: #DBFFA6");
            } else if (item > 60) {
                this.setStyle("-fx-background-color: #EDFFA6");
            } else if (item > 50) {
                this.setStyle("-fx-background-color: #FFFFA6");
            } else if (item > 40) {
                this.setStyle("-fx-background-color: #FFEDA6");
            } else if (item > 30) {
                this.setStyle("-fx-background-color: #FFDBA6");
            } else if (item > 20) {
                this.setStyle("-fx-background-color: #FFCAA6");
            } else if (item > 10) {
                this.setStyle("-fx-background-color: #FFB8A6");
            } else {
                this.setStyle("-fx-background-color: #FFA6A6");
            }
            setText(Integer.toString(item));
        }
    }
}
