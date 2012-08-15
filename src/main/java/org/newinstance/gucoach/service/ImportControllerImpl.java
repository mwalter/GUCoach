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

import org.newinstance.gucoach.exception.ImportException;
import org.newinstance.gucoach.exception.ValidationException;
import org.newinstance.gucoach.model.Player;
import org.newinstance.gucoach.model.PlayerStats;
import org.newinstance.gucoach.utility.MessageId;
import org.newinstance.gucoach.utility.ResourceLoader;

import javax.persistence.EntityManager;
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
public class ImportControllerImpl implements ImportController {

    private EntityManager em;
    private PlayerService playerService;
    private PlayerStatsService playerStatsService;
    private PlayerHistoryService playerHistoryService;
    private ImportService importService;
    private File importFile;

    /** The default constructor initialises the services. */
    public ImportControllerImpl(final EntityManager entityManager) {
        em = entityManager;
        playerService = new PlayerService(em);
        playerStatsService = new PlayerStatsService(em);
        playerHistoryService = new PlayerHistoryService(em);
        importService = new ImportServiceImpl();
    }

    @Override
    public void executeImport(final File file) throws ImportException, ValidationException {
        importFile = file;
        // -- 1 -- import data
        try {
            importService.importData(new InputStreamReader(new FileInputStream(file), ImportService.FILE_ENCODING));
            // TODO handle errors
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // -- 2 -- validate import data
        validateImportData();
        // -- 3 -- persist import data
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
                em.getTransaction().begin();
                playerService.removePlayer(player);
                em.getTransaction().commit();
            }
        }

        final Date latestImportDateInDb = playerHistoryService.findLatestImportDate();

        for (final Player player : players) {
            // INSERT
            // if player does not exist in the database insert all new player data
            if (!playersInDatabase.contains(player)) {
                em.getTransaction().begin();
                playerService.insertPlayer(player);
                playerStatsService.insertPlayerStats(importService.getStats().get(player.getId()));
                playerHistoryService.insertPlayerHistory(importService.getHistory().get(player.getId()));
                em.getTransaction().commit();
            } else {
                // UPDATE
                // if the import data is older than the latest import in the database insert player history records only
                if (importService.getImportDate().before(latestImportDateInDb)) {
                    em.getTransaction().begin();
                    playerHistoryService.insertPlayerHistory(importService.getHistory().get(player.getId()));
                    em.getTransaction().commit();
                } else {
                    // update player statistics only if there are changes
                    final PlayerStats playerStatsDb = playerStatsService.findPlayerStatsByPlayer(player);
                    if (!importService.getStats().get(player.getId()).equals(playerStatsDb)) {
                        em.getTransaction().begin();
                        playerStatsService.updatePlayerStats(importService.getStats().get(player.getId()));
                        em.getTransaction().commit();
                    }
                    // insert player history records regardless of player stats change
                    em.getTransaction().begin();
                    playerHistoryService.insertPlayerHistory(importService.getHistory().get(player.getId()));
                    em.getTransaction().commit();
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
