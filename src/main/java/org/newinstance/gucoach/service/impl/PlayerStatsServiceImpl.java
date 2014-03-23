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

package org.newinstance.gucoach.service.impl;

import org.newinstance.gucoach.entity.Player;
import org.newinstance.gucoach.entity.PlayerStats;
import org.newinstance.gucoach.persistence.PlayerStatsRepository;
import org.newinstance.gucoach.service.PlayerStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implements all services related to the to entity {@link PlayerStats}.
 *
 * @author mwalter
 */
@Service
public class PlayerStatsServiceImpl implements PlayerStatsService {

    @Autowired
    private PlayerStatsRepository playerStatsRepository;

    @Override
    public PlayerStats findByPlayer(final Player player) {
        return playerStatsRepository.findByPlayer(player);
    }
}
