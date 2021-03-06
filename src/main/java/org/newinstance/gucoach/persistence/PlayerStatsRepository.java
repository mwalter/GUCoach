/*
 * GUCoach - your personal coach for Goalunited (tm).
 * Licensed under General Public License v3 (GPLv3)
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

package org.newinstance.gucoach.persistence;

import org.newinstance.gucoach.entity.Player;
import org.newinstance.gucoach.entity.PlayerStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provides all repository methods related to entity {@link PlayerStats}.
 *
 * @author mwalter
 */
@Repository
public interface PlayerStatsRepository extends JpaRepository<PlayerStats, Long> {

    /**
     * Returns the player statistics for the player.
     *
     * @param player the player to find the statistics for
     * @return the player statistics
     */
    public PlayerStats findByPlayer(final Player player);
}
