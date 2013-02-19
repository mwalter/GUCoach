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

package org.newinstance.gucoach.service;

import org.newinstance.gucoach.model.Player;
import org.newinstance.gucoach.model.PlayerStats;

/**
 * Provides all service methods related to entity {@link PlayerStats}.
 *
 * @author mwalter
 */
public interface PlayerStatsService {

    /**
     * Returns the player statistics for the given player.
     *
     * @param player the player
     * @return the player statistics
     */
    public PlayerStats findPlayerStatsByPlayer(final Player player);

    /**
     * Persists player statistics to the database.
     *
     * @param playerStats the player statistics to persist
     */
    public void insertPlayerStats(final PlayerStats playerStats);

    /**
     * Updates the player statistics.
     *
     * @param playerStats the player statistics to update
     */
    public void updatePlayerStats(final PlayerStats playerStats);
}
