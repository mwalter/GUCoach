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

package org.newinstance.gucoach.persistence;

import java.util.List;
import java.util.Map;

/**
 * Provides methods to CRUD services managed by JPA.
 *
 * @author mwalter
 */
public interface PersistenceService {

    /**
     * Removes an entity from the database.
     *
     * @param entity the entity to remove
     * @param <T> the type of the entity
     */
    <T> void delete(final T entity);

    /**
     * Finds an entity by primary key.
     *
     * @param entity the entity type to find
     * @param id the primary key
     * @param <T> the type of the entity
     * @return the found entity
     */
    <T> T find(final Class entity, long id);

    /**
     * Finds entities by a named query.
     *
     * @param queryName the named query
     * @param entity the entity type to find
     * @param <T> the type of the entity
     * @return the list of found entities
     */
    <T> List<T> findByNamedQuery(final String queryName, final Class entity);

    /**
     * Finds entities by a named query and parameters.
     *
     * @param queryName the named query
     * @param entity the entity type to find
     * @param params some search parameters
     * @param <T> the type of the entity
     * @return the list of found entities
     */
    <T> List<T> findByNamedQuery(final String queryName, final Class entity, final Map<String, Object> params);

    /**
     * Finds an unique entity by a named query.
     *
     * @param queryName the named query
     * @param entity the entity type to find
     * @param <T> the type of the entity
     * @return the unique entity
     */
    <T> T findUniqueByNamedQuery(final String queryName, final Class entity);

    /**
     * Finds an unique entity by a named query and parameters.
     *
     * @param queryName the named query
     * @param entity the entity type to find
     * @param params some search parameters
     * @param <T> the type of the entity
     * @return the unique entity
     */
    <T> T findUniqueByNamedQuery(final String queryName, final Class entity, final Map<String, Object> params);

    /**
     * Persists an entity to the database.
     *
     * @param entity the entity to persist
     * @param <T> the type of the entity
     */
    <T> void save(final T entity);

    /**
     * Updates an entity.
     *
     * @param entity the entity to update
     * @param <T> the type of the entity
     */
    <T> void update(T entity);
}
