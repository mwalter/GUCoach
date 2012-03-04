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

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.newinstance.gucoach.model.Player;

/**
 * Tests the methods of the {@link ConnectionFactory}.
 *
 * @author mwalter
 * @version 07.02.12
 */
public class ConnectionFactoryTest {

    @Test
    public void initialiseConnectionTest() {
        final SqlSessionFactory session = ConnectionFactory.getInstance();
        Assert.assertNotNull(session);
        final Class type = session.getConfiguration().getTypeAliasRegistry().resolveAlias("Player");
        Assert.assertEquals(Player.class.getCanonicalName(), type.getName());
    }
}
