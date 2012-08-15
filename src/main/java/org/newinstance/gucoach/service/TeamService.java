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
import org.newinstance.gucoach.utility.NamedQuery;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Provides all service methods related to entity {@link Team}.
 *
 * @author mwalter
 */
public class TeamService extends PersistenceService {

    /**
     * The service needs an {@link EntityManager}.
     *
     * @param entityManager the entity manager
     */
    public TeamService(final EntityManager entityManager) {
        super(entityManager);
    }

    /**
     * Returns all teams in the database.
     *
     * @return the list of teams
     */
    public List<Team> findAllTeams() {
        return em.createNamedQuery(NamedQuery.FIND_ALL_TEAM.name(), Team.class).getResultList();
    }

    /**
     * Persists teams to the database.
     *
     * @param teams the list of teams to persist
     */
    public void insertTeams(final List<Team> teams) {
        for (final Team team : teams) {
            em.persist(team);
        }
    }

    /** Removes all teams from the database. */
    public void removeAllTeams() {
        em.createNamedQuery(NamedQuery.REMOVE_ALL_TEAM.name()).executeUpdate();
    }

    /**
     * Updates a team.
     *
     * @param team the team to update
     */
    public void updateTeam(final Team team) {
        em.merge(team);
    }
}
