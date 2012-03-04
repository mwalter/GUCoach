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

package org.newinstance.gucoach.service;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Provides connections to the database. Singleton implementation.
 *
 * @author mwalter
 */
public final class ConnectionFactory {

    private static final String MYBATIS_CONFIG_FILE = "cfg/mybatis-config.xml";
    private static SqlSessionFactory sqlSessionFactory;

    private ConnectionFactory() {
        // hide constructor
    }

    static {
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream(MYBATIS_CONFIG_FILE);
        } catch (final IOException e) {
            throw new ExceptionInInitializerError("Error initialising Mybatis configuration.");
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * Returns the {@code SqlSessionFactory} singleton instance.
     *
     * @return the SqlSessionFactory
     */
    public static SqlSessionFactory getInstance() {
        return sqlSessionFactory;
    }

}
