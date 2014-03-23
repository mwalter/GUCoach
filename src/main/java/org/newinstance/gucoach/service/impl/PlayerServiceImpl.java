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

import java.util.List;

import org.newinstance.gucoach.entity.Player;
import org.newinstance.gucoach.entity.PlayerHistory;
import org.newinstance.gucoach.persistence.PlayerHistoryRepository;
import org.newinstance.gucoach.persistence.PlayerRepository;
import org.newinstance.gucoach.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implements all services related to the to entity {@link Player}.
 *
 * @author mwalter
 */
@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerHistoryRepository playerHistoryRepository;

    @Override
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public Player findOne(final Long id) {
        return playerRepository.findOne(id);
    }

    @Override
    public void delete(final Player player) {
        final Player playerToRemove = playerRepository.findOne(player.getId());
        // do we have player history records? if so remove them as well
        // this is done here and not in the player entity with a relation because the player does not need to know about history records
        final List<PlayerHistory> phResult = playerHistoryRepository.findByPlayer(playerToRemove);
        playerHistoryRepository.delete(phResult);

        // finally delete player
        playerRepository.delete(playerToRemove);
    }

    @Override
    public void save(final Player player) {
        playerRepository.save(player);
    }

    @Override
    public void save(final List<Player> players) {
        playerRepository.save(players);
    }
}
