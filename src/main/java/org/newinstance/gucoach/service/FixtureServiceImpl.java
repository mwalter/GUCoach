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
import org.newinstance.gucoach.persistence.PersistenceService;
import org.newinstance.gucoach.utility.NamedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implements all service methods related to entity {@link Fixture}.
 *
 * @author mwalter
 */
@Transactional
public class FixtureServiceImpl implements FixtureService {

    @Autowired
    private PersistenceService persistenceService;

    public void insertFixtures(final List<Fixture> fixtures) {
        for (final Fixture fixture : fixtures) {
            persistenceService.save(fixture);
        }
    }

    public List<Fixture> findAllFixtures() {
        return persistenceService.findByNamedQuery(NamedQuery.FIND_ALL_FIXTURE.name(), Fixture.class);
    }

    public void removeAllFixtures() {
        final List<Fixture> fixtures = persistenceService.findByNamedQuery(NamedQuery.FIND_ALL_FIXTURE.name(), Fixture.class);
        for (final Fixture fixture : fixtures) {
            persistenceService.delete(fixture);
        }
    }

    public void updateFixture(final Fixture fixture) {
        persistenceService.update(fixture);
    }

}
