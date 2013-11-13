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

import java.util.Date;
import java.util.List;

import org.newinstance.gucoach.entity.Player;
import org.newinstance.gucoach.entity.PlayerHistory;
import org.newinstance.gucoach.persistence.PlayerHistoryRepository;
import org.newinstance.gucoach.service.PlayerHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *Implements all services related to the to entity {@link PlayerHistory}.
 *
 * @author mwalter
 */
@Service
public class PlayerHistoryServiceImpl implements PlayerHistoryService {

    @Autowired
    private PlayerHistoryRepository playerHistoryRepository;

    @Override
    public List<Date> findAllImportDates() {
        return playerHistoryRepository.findAllImportDates();
    }

    @Override
    public List<PlayerHistory> findByPlayer(final Player player) {
        return playerHistoryRepository.findByPlayer(player);
    }

    @Override
    public Date findLatestImportDate() {
        return playerHistoryRepository.findLatestImportDate();
    }

    @Override
    public PlayerHistory findOne(final Long id) {
        return playerHistoryRepository.findOne(id);
    }

    @Override
    public void save(final PlayerHistory playerHistory) {
        playerHistoryRepository.save(playerHistory);
    }
}
