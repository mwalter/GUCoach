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

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

/**
 * Implements CRUD services managed by JPA.
 *
 * @author mwalter
 */
@Repository
public class PersistenceServiceImpl implements PersistenceService {

    @PersistenceContext
    private EntityManager em;

    public <T> void delete(final T entity) {
        em.remove(entity);
    }

    @SuppressWarnings("unchecked")
    public <T> T find(final Class entity, long id) {
        return (T) em.find(entity, id);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> findByNamedQuery(final String queryName, final Class entity) {
        return em.createNamedQuery(queryName, entity).getResultList();
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> findByNamedQuery(final String queryName, final Class entity, final Map<String, Object> params) {
        final TypedQuery<T> query = em.createNamedQuery(queryName, entity);
        for (final Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public <T> T findUniqueByNamedQuery(final String queryName, final Class entity) {
        return (T) em.createNamedQuery(queryName, entity).getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public <T> T findUniqueByNamedQuery(final String queryName, final Class entity, final Map<String, Object> params) {
        final TypedQuery<T> query = em.createNamedQuery(queryName, entity);
        for (final Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getSingleResult();
    }

    public <T> void save(final T entity) {
        em.persist(entity);
    }

    public <T> void update(T entity) {
        em.merge(entity);
    }
}
