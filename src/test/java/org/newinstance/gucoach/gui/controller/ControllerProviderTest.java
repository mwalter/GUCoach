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

package org.newinstance.gucoach.gui.controller;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests methods of class {@link ControllerProvider}.
 *
 * @author mwalter
 */
public class ControllerProviderTest {

    @Test
    public void testSingletonInstance() {
        final ControllerProvider controllerProvider = ControllerProvider.getInstance();
        Assert.assertEquals(controllerProvider, ControllerProvider.getInstance());
    }

    @Test
    public void testMainController() {
        final MainController mainController = new MainController();
        ControllerProvider.getInstance().setMainController(mainController);
        Assert.assertNotNull(ControllerProvider.getInstance().getMainController());
        Assert.assertEquals(mainController, ControllerProvider.getInstance().getMainController());
    }

    @Test
    public void testTeamController() {
        final TeamController teamController = new TeamController();
        ControllerProvider.getInstance().setTeamController(teamController);
        Assert.assertNotNull(ControllerProvider.getInstance().getTeamController());
        Assert.assertEquals(teamController, ControllerProvider.getInstance().getTeamController());
    }
}
