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

package org.newinstance.gucoach.service;

import java.util.Date;
import java.util.List;

import org.newinstance.gucoach.entity.Player;
import org.newinstance.gucoach.entity.PlayerHistory;

/**
 * Provides all service methods related to entity {@link PlayerHistory}.
 *
 * @author mwalter
 */
public interface PlayerHistoryService {

    /**
     * Returns all import dates.
     *
     * @return the list of all import dates
     */
    public List<Date> findAllImportDates();

    /**
     * Returns the player history records for a certain player.
     *
     * @param player the player
     * @return the player history of the player
     */
    public List<PlayerHistory> findByPlayer(final Player player);

    /**
     * Returns the latest import date of all player history records.
     *
     * @return the latest import date
     */
    public Date findLatestImportDate();

    /**
     * Returns the player history with the id.
     *
     * @param id the id of the player history to find
     * @return the player history
     */
    public PlayerHistory findOne(final Long id);

    /**
     * Saves the history of a player.
     *
     * @param playerHistory the player history to save
     */
    public void save(final PlayerHistory playerHistory);

}
