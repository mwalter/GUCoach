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
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.newinstance.gucoach.gui.controller.MainController;
import org.newinstance.gucoach.model.Country;

/**
 * Displays flag symbols instead of country name.
 *
 * @author mwalter
 */
public class CountryValueFlagCell extends TableCell<PlayerDataRow, Country> {

    public static final String FLAG_ICON_LOCATION = "images/flags/";
    public static final String FLAG_ICON_EXTENSION = ".png";

    @Override
    public void updateItem(final Country item, final boolean empty) {
        super.updateItem(item, empty);
        if (!isEmpty()) {
            final String filename = FLAG_ICON_LOCATION + item.name().toLowerCase() + FLAG_ICON_EXTENSION;
            final Image countryImage = new Image(MainController.class.getClassLoader().getResourceAsStream(filename));
            final ImageView imageView = new ImageView();
            imageView.setImage(countryImage);
            // create tooltip with country code
            final Tooltip tooltip = new Tooltip();
            tooltip.setText(item.name());
            setGraphic(imageView);
            setTooltip(tooltip);
        }
    }
}
