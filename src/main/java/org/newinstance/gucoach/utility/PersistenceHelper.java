/*
 * GUCoach - your personal coach for Goalunited (tm).
 * Copyright (C) 2012 newInstance.org
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

package org.newinstance.gucoach.utility;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Provides singleton access to the {@link EntityManager} used by the application.
 *
 * @author mwalter
 */
public final class PersistenceHelper {

    private static final PersistenceHelper INSTANCE = new PersistenceHelper();
    private static final String GUCOACH_PERSISTENCE_UNIT = "gucoach-pu";
    private static EntityManagerFactory factory;

    private PersistenceHelper() {
        // hide constructor
    }

    /**
     * Returns the singleton instance of PersistenceHelper. The {@link EntityManagerFactory} is created the first time this method id called.
     *
     * @return the persistence helper instance
     */
    public static PersistenceHelper getInstance() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory(GUCOACH_PERSISTENCE_UNIT);
        }
        return INSTANCE;
    }

    /**
     * Creates a new {@link EntityManager}.
     *
     * @return the new entity manager
     */
    public EntityManager createEntityManager() {
        return factory.createEntityManager();
    }

    /** Closes the {@link EntityManagerFactory} if open. Should be called before the application is closed. */
    public void closeEntityManagerFactory() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }

}
