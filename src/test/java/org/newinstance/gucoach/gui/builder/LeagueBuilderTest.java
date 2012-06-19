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

package org.newinstance.gucoach.gui.builder;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests methods of class {@link LeagueBuilder}.
 *
 * @author mwalter
 */
public class LeagueBuilderTest {

    @Test
    public void buildCreateLeagueDialogueTest() {
        final Parent root = LeagueBuilder.buildCreateLeagueDialogue();
        Assert.assertNotNull(root);
        final ObservableList<Node> children = root.getChildrenUnmodifiable();
        // two VBoxes
        Assert.assertEquals(2, children.size());
        final Node tfMatchday = root.lookup("#idTfMatchday");
        Assert.assertNotNull(tfMatchday);
        final TextField matchday = (TextField) tfMatchday;
        final String date = "04.06.2012";
        matchday.setText(date);
        Assert.assertEquals(date, matchday.getText());
    }
}
