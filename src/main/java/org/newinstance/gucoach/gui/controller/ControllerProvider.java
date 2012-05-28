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

/**
 * Provides singleton access to all JavaFX GUI controllers.
 *
 * @author mwalter
 */
public final class ControllerProvider {

    /** The singleton instance. */
    private static ControllerProvider instance;
    /** The league controller. */
    private LeagueController leagueController;
    /** The main controller. */
    private MainController mainController;
    /** The team controller. */
    private TeamController teamController;

    static {
        instance = new ControllerProvider();
    }

    /**
     * Returns the singleton instance.
     *
     * @return the ControllerProvider singleton
     */
    public static ControllerProvider getInstance() {
        return instance;
    }

    /**
     * Returns the league controller.
     *
     * @return the league controller
     */
    public LeagueController getLeagueController() {
        return leagueController;
    }

    /**
     * Sets the league controller.
     *
     * @param controller the league controller to set
     */
    public void setLeagueController(final LeagueController controller) {
        leagueController = controller;
    }

    /**
     * Returns the main controller.
     *
     * @return the main controller
     */
    public MainController getMainController() {
        return mainController;
    }

    /**
     * Sets the main controller.
     *
     * @param controller the main controller to set
     */
    public void setMainController(final MainController controller) {
        mainController = controller;
    }

    /**
     * Returns the team controller.
     *
     * @return the team controller
     */
    public TeamController getTeamController() {
        return teamController;
    }

    /**
     * Sets the team controller.
     *
     * @param controller the team controller to set
     */
    public void setTeamController(final TeamController controller) {
        teamController = controller;
    }

    private ControllerProvider() {
        // hide constructor
    }
}
