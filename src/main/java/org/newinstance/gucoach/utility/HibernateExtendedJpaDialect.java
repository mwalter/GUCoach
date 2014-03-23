/*
 * GUCoach - your personal coach for Goalunited (tm).
 * Licensed under General Public License v3 (GPLv3)
 * newInstance.org, 2012-2014
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

import java.sql.Connection;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.logging.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;

/**
 * Extension for {@link HibernateJpaDialect} in order to get Hibernate JPA to work with Spring Batch. This class
 * supports custom isolation levels with JPA and avoids the error "Standard JPA does not support custom isolation levels
 * – use a special JpaDialect for your JPA implementation".<br/>
 * http://amitstechblog.wordpress.com/2011/05/31/supporting-custom-isolation-levels-with-jpa
 *
 * @author mwalter
 */
public class HibernateExtendedJpaDialect extends HibernateJpaDialect {

    /** The log4j logger. */
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(HibernateExtendedJpaDialect.class.getName());

    /**
     * This method is overridden to set custom isolation levels on the connection.
     *
     * @param entityManager the entity manager used for this transaction
     * @param definition the Spring transaction definition
     * @return an arbitrary object that holds transaction data, if any (to be passed into cleanupTransaction)
     * @throws PersistenceException
     * @throws SQLException
     * @throws TransactionException
     */
    @Override
    public Object beginTransaction(final EntityManager entityManager, final TransactionDefinition definition) throws PersistenceException, SQLException, TransactionException {
        final Session session = (Session) entityManager.getDelegate();
        if (definition.getTimeout() != TransactionDefinition.TIMEOUT_DEFAULT) {
            getSession(entityManager).getTransaction().setTimeout(definition.getTimeout());
        }

        entityManager.getTransaction().begin();
        LOGGER.debug("Transaction started.");

        session.doWork(new Work() {

            public void execute(Connection connection) throws SQLException {
                LOGGER.debug("The connection instance is {}.", connection);
                LOGGER.debug("The isolation level of the connection is {} and the isolation level set on the transaction is {}.", connection.getTransactionIsolation(), definition.getIsolationLevel());
                DataSourceUtils.prepareConnectionForTransaction(connection, definition);
            }
        });

        return prepareTransaction(entityManager, definition.isReadOnly(), definition.getName());
    }

}