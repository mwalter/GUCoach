/*
 * GUCoach - your personal coach for Goalunited (tm).
 * Licenced under General Public Licence v3 (GPLv3)
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

import org.newinstance.gucoach.exception.ImportException;
import org.newinstance.gucoach.model.Player;
import org.newinstance.gucoach.model.PlayerHistory;

import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The import service provides methods for the import process.
 *
 * @author mwalter
 */
public interface ImportService {

    public static final String FILE_ENCODING = "ISO-8859-1";

    /**
     * Returns the date of the import file.
     *
     * @return the date
     */
    public Date getImportDate();

    /**
     * Returns all imported player histories.
     *
     * @return the map with player histories
     */
    public Map<Long, PlayerHistory> getHistory();

    /**
     * Returns all imported players.
     *
     * @return the list of players
     */
    public List<Player> getPlayers();

    /**
     * Triggers an import of all player related data from an CSV file.
     *
     * @param inputStreamReader the reader to read the file
     * @throws ImportException if an error occurs
     */
    public void importData(final InputStreamReader inputStreamReader) throws ImportException;

    /**
     * Resets (deletes) all previously imported data.
     */
    public void reset();
}
