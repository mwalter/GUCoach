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

import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests methods of class {@link PlayerContentProvider}.
 *
 * @author mwalter
 */
public class PlayerContentProviderTest {

    @Test
    public void testGetPlayerData() {
        final ObservableList<PlayerDataRow> playerList = PlayerContentProvider.getPlayerData();
        Assert.assertNotNull(playerList);
        Assert.assertFalse(playerList.isEmpty());
        for (final PlayerDataRow playerDataRow : playerList) {
            Assert.assertNotNull(playerDataRow.getAge());
            Assert.assertNotNull(playerDataRow.getFullName());
        }
    }
}
