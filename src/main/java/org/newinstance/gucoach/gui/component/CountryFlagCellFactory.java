/*
 * GUCoach - your personal coach for Goalunited (tm).
 * Licenced under General Public Licence v3 (GPLv3)
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

package org.newinstance.gucoach.gui.component;

import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import org.apache.commons.lang3.StringUtils;
import org.newinstance.gucoach.entity.Player;
import org.newinstance.gucoach.gui.controller.MainController;

/**
 * Creates table cells containing country flag symbols.
 *
 * @author mwalter
 */
public class CountryFlagCellFactory<S, T> implements Callback<TableColumn<Player, String>, TableCell<Player, String>> {

    private static final String FLAG_ICON_LOCATION = "images/flags/";
    private static final String FLAG_ICON_EXTENSION = ".png";

    @Override
    public TableCell<Player, String> call(TableColumn<Player, String> p) {
        final TableCell<Player, String> cell = new TableCell<Player, String>() {

            @Override
            public void updateItem(final String item, final boolean empty) {
                if (item.equals(getItem())) {
                    return;
                }
                super.updateItem(item, empty);
                if (StringUtils.isBlank(item)) {
                    super.setText(null);
                    super.setGraphic(null);
                } else {
                    final String filename = FLAG_ICON_LOCATION + item.toLowerCase() + FLAG_ICON_EXTENSION;
                    final Image countryImage = new Image(MainController.class.getClassLoader().getResourceAsStream(filename));
                    final ImageView imageView = new ImageView();
                    imageView.setImage(countryImage);
                    // create tooltip with country code
                    final Tooltip tooltip = new Tooltip();
                    tooltip.setText(item);
                    super.setGraphic(imageView);
                    super.setTooltip(tooltip);
                }
            }
        };
        cell.setAlignment(Pos.CENTER);
        return cell;
    }
}
