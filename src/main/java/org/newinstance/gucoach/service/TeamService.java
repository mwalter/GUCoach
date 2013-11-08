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

import java.util.List;

import org.newinstance.gucoach.entity.Team;

/**
 * Provides all service methods related to entity {@link Team}.
 *
 * @author mwalter
 */
public interface TeamService {

    /**
     * Returns all teams in the database.
     *
     * @return the list of teams
     */
    public List<Team> findAllTeams();

    /**
     * Persists teams to the database.
     *
     * @param teams the list of teams to persist
     */
    public void insertTeams(final List<Team> teams);

    /** Removes all teams from the database. */
    public void removeAllTeams();

    /**
     * Updates a team.
     *
     * @param team the team to update
     */
    public void updateTeam(final Team team);
}
