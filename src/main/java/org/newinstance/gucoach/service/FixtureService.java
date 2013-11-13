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

import java.util.List;

import org.newinstance.gucoach.entity.Fixture;

/**
 * Provides all service methods related to entity {@link Fixture}.
 *
 * @author mwalter
 */
public interface FixtureService {

    /**
     * Finds all fixtures.
     *
     * @return all fixtures
     */
    public List<Fixture> findAll();

    /**
     * Finds the fixture with id.
     *
     * @param id the id of the fixture to find
     * @return the fixture with the id
     */
    public Fixture findOne(final Long id);

    /**
     * Deletes all fixtures.
     */
    public void deleteAll();

    /**
     * Saves a fixture.
     *
     * @param fixture the fixture to save
     */
    public void save(final Fixture fixture);

    /**
     * Saves a list of fixtures.
     *
     * @param fixtures the fixtures to save
     */
    public void save(final List<Fixture> fixtures);
}
