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

import org.newinstance.gucoach.entity.Player;
import org.newinstance.gucoach.exception.ImportException;
import org.newinstance.gucoach.exception.ValidationException;
import org.newinstance.gucoach.utility.MessageId;
import org.newinstance.gucoach.utility.ResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * Controls the process of importing, validating and persisting player data. Communicates with import and database services. The import controller should be
 * used for only one import and discarded after use.
 *
 * @author mwalter
 */
@Component
public class ImportControllerImpl implements ImportController {

    @Autowired
    private ImportService importService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private PlayerHistoryService playerHistoryService;

    private File importFile;

    @Override
    public void executeImport(final File file) throws ImportException, ValidationException {
        importFile = file;

        // -- 1 -- reset imported data
        importService.reset();
        // -- 2 -- import data
        try {
            importService.importData(new InputStreamReader(new FileInputStream(file), ImportService.FILE_ENCODING));
            // TODO handle errors
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // -- 3 -- validate import data
        validateImportData();
        // -- 4 -- persist import data
        persistImportData();
    }

    /**
     * Makes player data persistent. In detail three operations are performed:
     * <ol>
     * <li>Iterates through all players in the database. If a player is not contained in the list of the current import the player data is deleted.</li>
     * <li>Iterates through all players in the current import. If a player is not contained in the database already the data is inserted.</li>
     * <li>Iterates through all players in the current import. If a player is contained in the database already the data is updated.</li>
     * </ol>
     */
    private void persistImportData() {
        final List<Player> playersInDatabase = playerService.findAllPlayers();
        final List<Player> players = importService.getPlayers();

        for (final Player player : playersInDatabase) {
            // DELETE
            // if player exists in database but not in import list delete all player data from database
            if (!players.contains(player)) {
                playerService.removePlayer(player);
            }
        }

        final Date latestImportDateInDb = playerHistoryService.findLatestImportDate();

        for (final Player player : players) {
            // INSERT
            // if player does not exist in the database insert all new player data
            if (!playersInDatabase.contains(player)) {
                playerService.insertPlayer(player);
                playerHistoryService.insertPlayerHistory(importService.getHistory().get(player.getId()));
            } else {
                // UPDATE
                // if the import data is older than the latest import in the database insert player history records only
                if (importService.getImportDate().before(latestImportDateInDb)) {
                    playerHistoryService.insertPlayerHistory(importService.getHistory().get(player.getId()));
                } else {
                    // update player statistics
                    playerService.updatePlayer(player);
                    // insert player history records regardless of player stats change
                    playerHistoryService.insertPlayerHistory(importService.getHistory().get(player.getId()));
                }
            }
        }
    }

    /**
     * Validates the imported data.
     *
     * @throws ValidationException if the import data is not valid
     */
    private void validateImportData() throws ValidationException {
        // does the file contain player and player history records?
        if (importService.getPlayers().isEmpty() || importService.getHistory().isEmpty()) {
            throw new ValidationException(ResourceLoader.getMessage(MessageId.V003.getMessageKey()));
        }

        // was the data already imported?
        final List<Date> allImportDatesInDb = playerHistoryService.findAllImportDates();
        final Date importDateOfFile = importService.getImportDate();
        if (allImportDatesInDb.contains(importDateOfFile)) {
            final String message = ResourceLoader.getMessage(MessageId.V001.getMessageKey(), importFile.getName());
            throw new ValidationException(message);
        }

        // does every player have his own history data record?
        final List<Player> players = importService.getPlayers();
        for (final Player player : players) {
            if (!importService.getHistory().containsKey(player.getId())) {
                final String message = ResourceLoader.getMessage(MessageId.V002.getMessageKey(), player.getLastName());
                throw new ValidationException(message);
            }
        }
    }

}
