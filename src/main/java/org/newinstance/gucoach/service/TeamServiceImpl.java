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

import org.newinstance.gucoach.model.Team;
import org.newinstance.gucoach.persistence.PersistenceService;
import org.newinstance.gucoach.utility.NamedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implements all service methods related to entity {@link Team}.
 *
 * @author mwalter
 */
@Component
@Transactional
public class TeamServiceImpl implements TeamService {

    @Autowired
    private PersistenceService persistenceService;

    public List<Team> findAllTeams() {
        return persistenceService.findByNamedQuery(NamedQuery.FIND_ALL_TEAM.name(), Team.class);
    }

    public void insertTeams(final List<Team> teams) {
        for (final Team team : teams) {
            persistenceService.save(team);
        }
    }

    public void removeAllTeams() {
        final List<Team> teams = persistenceService.findByNamedQuery(NamedQuery.FIND_ALL_TEAM.name(), Team.class);
        for (final Team team : teams) {
            persistenceService.delete(team);
        }
    }

    public void updateTeam(final Team team) {
        persistenceService.update(team);
    }
}
