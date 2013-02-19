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

import org.newinstance.gucoach.model.Fixture;

import java.util.List;

/**
 * Provides all service methods related to entity {@link Fixture}.
 *
 * @author mwalter
 */
public interface FixtureService {

    /**
     * Persists fixtures to the database.
     *
     * @param fixtures the list of fixtures to persist
     */
    public void insertFixtures(final List<Fixture> fixtures);

    /**
     * Returns all fixtures in the database.
     *
     * @return the list of fixtures
     */
    public List<Fixture> findAllFixtures();

    /** Removes all fixtures from the database. */
    public void removeAllFixtures();

    /**
     * Updates a fixture.
     *
     * @param fixture the fixture to update
     */
    public void updateFixture(final Fixture fixture);

}
