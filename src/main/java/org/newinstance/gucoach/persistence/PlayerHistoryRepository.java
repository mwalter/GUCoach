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

import java.util.Date;
import java.util.List;

import org.newinstance.gucoach.entity.Player;
import org.newinstance.gucoach.entity.PlayerHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Provides all repository methods related to entity {@link PlayerHistory}.
 *
 * @author mwalter
 */
@Repository
public interface PlayerHistoryRepository extends JpaRepository<PlayerHistory, Long> {

    /**
     * Returns all import dates in the database.
     *
     * @return the list of import dates
     */
    @Query(value = "SELECT DISTINCT ph.importDate FROM PlayerHistory ph")
    public List<Date> findAllImportDates();

    /**
     * Returns the latest import date in the database.
     *
     * @return the latest import dates
     */
    @Query(value = "SELECT MAX(ph.importDate) FROM PlayerHistory ph")
    public Date findLatestImportDate();

    /**
     * Returns the player history for the given player.
     *
     * @param player the player
     * @return the player history
     */
    public List<PlayerHistory> findByPlayer(final Player player);

}
