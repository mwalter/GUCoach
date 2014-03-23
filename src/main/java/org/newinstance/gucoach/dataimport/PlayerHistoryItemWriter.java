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

package org.newinstance.gucoach.dataimport;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newinstance.gucoach.entity.PlayerHistory;
import org.newinstance.gucoach.service.PlayerHistoryService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Writes {@link PlayerHistory} entities to the database.
 *
 * @author mwalter
 */
public class PlayerHistoryItemWriter implements ItemWriter<PlayerHistory> {

    /** The log4j logger. */
    private static final Logger LOGGER = LogManager.getLogger(PlayerHistoryItemWriter.class.getName());

    @Autowired
    private PlayerHistoryService playerHistoryService;

    /**
     * Writes {@link PlayerHistory} entities to the database.
     *
     * @param playerHistoryList the list of {@link PlayerHistory} entities to write.
     * @throws Exception if an error occurs
     */
    public void write(List<? extends PlayerHistory> playerHistoryList) throws Exception {
        for (final PlayerHistory playerHistory : playerHistoryList) {
            playerHistoryService.save(playerHistory);
        }
    }
}
