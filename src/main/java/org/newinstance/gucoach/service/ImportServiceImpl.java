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

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;
import org.apache.commons.lang3.StringUtils;
import org.newinstance.gucoach.exception.ImportException;
import org.newinstance.gucoach.model.Country;
import org.newinstance.gucoach.model.Player;
import org.newinstance.gucoach.model.PlayerHistory;
import org.newinstance.gucoach.model.PlayerStats;
import org.newinstance.gucoach.model.Position;
import org.newinstance.gucoach.model.StrongFoot;
import org.newinstance.gucoach.utility.MessageId;
import org.newinstance.gucoach.utility.ResourceLoader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Implements all services related to the import process.
 *
 * @author mwalter
 */
public class ImportServiceImpl implements ImportService {

    private static final char SEPARATOR = ';';
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private List<String[]> fileContent = new ArrayList<String[]>();
    private List<Player> players = new ArrayList<Player>();
    private Map<Long, PlayerHistory> history = new HashMap<Long, PlayerHistory>();
    private Map<Long, PlayerStats> stats = new HashMap<Long, PlayerStats>();
    private Date importDate;

    @Override
    public Date getImportDate() {
        return importDate;
    }

    @Override
    public Map<Long, PlayerHistory> getHistory() {
        return history;
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public Map<Long, PlayerStats> getStats() {
        return stats;
    }

    @Override
    public void importData(final InputStreamReader inputStreamReader) throws ImportException {
        readCSVFile(inputStreamReader);
        extractImportDate();
        importPlayerData();
        importPlayerHistory();
    }

    /**
     * Parses and imports the main player data section from the given CSV file content. Generates {@link Player} and {@link PlayerStats} entities.
     *
     * @throws ImportException if an error occurs
     */
    private void importPlayerData() throws ImportException {
        final CSVParser parser = new CSVParser(SEPARATOR);
        // use iterator because we are going to remove lines
        for (final Iterator<String[]> iterator = fileContent.iterator(); iterator.hasNext(); ) {
            final String[] line = iterator.next();
            // the array with player's playerRecord
            String[] playerRecord;
            try {
                // parse player's record and fill it into an array
                playerRecord = parser.parseLine(line[0]);
            } catch (final IOException ioe) {
                throw new ImportException(ResourceLoader.getMessage(MessageId.E002.getMessageKey()), ioe);
            }

            // if first part is numeric it is actually a player
            if (StringUtils.isNumeric(playerRecord[0])) {
                convertRecordToPlayer(playerRecord);
                convertRecordToPlayerStats(playerRecord);
                // make sure the line is not parsed again
                iterator.remove();
            }

            // stop parsing at empty line because main player data section ends here
            if (line[0].isEmpty()) {
                break;
            }
        }
    }

    /**
     * Parses and imports the player history data section from the given CSV file content. Generates {@link PlayerHistory} entities.
     *
     * @throws ImportException if an error occurs
     */
    private void importPlayerHistory() throws ImportException {
        final CSVParser parser = new CSVParser(SEPARATOR);
        boolean historySectionFound = false;
        // use iterator because we are going to remove lines
        for (final Iterator<String[]> iterator = fileContent.iterator(); iterator.hasNext(); ) {
            final String[] line = iterator.next();
            // the array with player's history
            String[] playerHistory;
            try {
                // parse player's history and fill it into an array
                playerHistory = parser.parseLine(line[0]);
            } catch (final IOException ioe) {
                throw new ImportException(ResourceLoader.getMessage(MessageId.E002.getMessageKey()), ioe);
            }

            // if first part is numeric it is actually a player's history
            if (StringUtils.isNumeric(playerHistory[0])) {
                // use a boolean variable to stop parsing after history section
                historySectionFound = true;
                convertRecordToPlayerHistory(playerHistory);
                // make sure the line is not parsed again
                iterator.remove();
            }

            // end parsing at empty line only if the history data section was found and processed
            if (line[0].isEmpty() && historySectionFound) {
                break;
            }
        }
    }

    /**
     * Reads the content of a CSV file.
     *
     * @param inputStreamReader the reader to read the file
     * @throws ImportException if an error occurs
     */
    private void readCSVFile(final InputStreamReader inputStreamReader) throws ImportException {
        CSVReader reader = null;
        List<String[]> content;
        try {
            reader = new CSVReader(inputStreamReader);
            // read all content from CSV file
            content = reader.readAll();
            if (content.isEmpty()) {
                // nothing to import
                throw new ImportException(ResourceLoader.getMessage(MessageId.E003.getMessageKey()));
            }
            fileContent.addAll(content);
        } catch (final IOException ioe) {
            throw new ImportException(ResourceLoader.getMessage(MessageId.E004.getMessageKey()), ioe);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    // do nothing
                }
            }
        }
    }

    /**
     * Extracts the date of the import file. The first line in the file contains the date.
     *
     * @throws ImportException if an error occurs
     */
    private void extractImportDate() throws ImportException {
        final String[] firstLineInFile = fileContent.get(0);
        // data is in first cell
        final String dateLine = firstLineInFile[0];

        if (StringUtils.isBlank(dateLine)) {
            throw new ImportException(ResourceLoader.getMessage(MessageId.E005.getMessageKey()));
        } else {
            int dateStartPosition = 0;
            for (int i = 0; i < dateLine.length(); i++) {
                if (Character.isDigit(dateLine.charAt(i))) {
                    dateStartPosition = i;
                    break;
                }
            }

            // TODO this date format is valid in some countries only - maybe we have to try other formats as well
            final SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
            final Date date = df.parse(dateLine, new ParsePosition(dateStartPosition));
            if (date == null) {
                final String message = ResourceLoader.getMessage(MessageId.E006.getMessageKey(), dateLine.substring(dateStartPosition, dateLine.length() + 1));
                throw new ImportException(message);
            }
            // set import date for later use
            importDate = date;
        }
    }

    /**
     * Converts a data record of a player into a player entity.
     *
     * @param record the record to convert
     */
    private void convertRecordToPlayer(final String[] record) {
        final Player player = new Player();
        player.setId(new Long(record[AttributePosition.ID]));
        player.setFirstName(record[AttributePosition.FIRSTNAME]);
        player.setLastName(record[AttributePosition.LASTNAME]);
        try {
            player.setCountry(Country.valueOf(record[AttributePosition.COUNTRY]));
        } catch (final IllegalArgumentException e) {
            // TODO remove if all countries are provided
            System.out.println("Country [" + record[AttributePosition.COUNTRY] + "] is missing in model.");
            e.printStackTrace();
        }
        player.setHeight(new Integer(record[AttributePosition.HEIGHT]));
        player.setPersonality(record[AttributePosition.PERSONALITY]);
        player.setBirthday(record[AttributePosition.BIRTHDAY]);
        player.setStrongFoot(StrongFoot.valueOf(record[AttributePosition.STRONG_FOOT].toUpperCase()));
        player.setImportDate(importDate);
        players.add(player);
    }

    /**
     * Converts a data record of a player's statistics into a player stats entity.
     *
     * @param record the record to convert
     */
    private void convertRecordToPlayerStats(final String[] record) {
        final PlayerStats playerStats = new PlayerStats();
        playerStats.setPlayerId(new Long(record[AttributePosition.ID]));
        playerStats.setNumber(new Integer(record[AttributePosition.NUMBER]));
        // is training filled?
        final String training = record[AttributePosition.TRAINING];
        playerStats.setTraining(StringUtils.isEmpty(training) ? null : training);
        // erase apostrophe in strength value
        final String strength = StringUtils.remove(record[AttributePosition.STRENGTH], '\'');
        playerStats.setAverageStrength(new Float(strength));
        playerStats.setPosition(Position.valueOf(record[AttributePosition.POSITION]));
        playerStats.setForm(new Integer(record[AttributePosition.FORM]));
        playerStats.setEnergy(new Integer(record[AttributePosition.ENERGY]));
        playerStats.setEndurance(new Integer(record[AttributePosition.ENDURANCE]));
        playerStats.setExperience(new Integer(record[AttributePosition.EXPERIENCE]));
        playerStats.setSkillGoalkeeping(new Integer(record[AttributePosition.SKILL_GOALKEEPING]));
        playerStats.setSkillTackling(new Integer(record[AttributePosition.SKILL_TACKLING]));
        playerStats.setSkillPlaymaking(new Integer(record[AttributePosition.SKILL_PLAYMAKING]));
        playerStats.setSkillPassing(new Integer(record[AttributePosition.SKILL_PASSING]));
        playerStats.setSkillScoring(new Integer(record[AttributePosition.SKILL_SCORING]));
        playerStats.setTalent(record[AttributePosition.TALENT]);
        playerStats.setTalentLevel(new Integer(record[AttributePosition.TALENT_LEVEL]));
        playerStats.setAge(new Integer(record[AttributePosition.AGE]));
        playerStats.setSalary(new Integer(record[AttributePosition.SALARY]));
        playerStats.setAssignments(new Integer(record[AttributePosition.ASSIGNMENTS]));
        playerStats.setGoalsSeason(new Integer(record[AttributePosition.GOALS_SEASON]));
        playerStats.setGoalsTotal(new Integer(record[AttributePosition.GOALS_TOTAL]));
        // is market value filled?
        final String marketValue = record[AttributePosition.MARKET_VALUE];
        playerStats.setMarketValue(StringUtils.isEmpty(marketValue) ? null : new Integer(marketValue));
        playerStats.setYellowCardsSeason(new Integer(record[AttributePosition.YELLOW_CARDS_SEASON]));
        playerStats.setYellowCardsTotal(new Integer(record[AttributePosition.YELLOW_CARDS_TOTAL]));
        playerStats.setRedCardsSeason(new Integer(record[AttributePosition.RED_CARDS_SEASON]));
        playerStats.setRedCardsTotal(new Integer(record[AttributePosition.RED_CARDS_TOTAL]));
        playerStats.setImportDate(importDate);
        stats.put(playerStats.getPlayerId(), playerStats);
    }

    /**
     * Converts a data record of a player's history into a player history entity.
     *
     * @param record the record to convert
     */
    private void convertRecordToPlayerHistory(final String[] record) {
        final PlayerHistory playerHistory = new PlayerHistory();
        playerHistory.setPlayerId(new Long(record[AttributePosition.ID]));
        // erase apostrophe in strength value
        final String strength = StringUtils.remove(record[AttributePosition.STRENGTH], '\'');
        playerHistory.setAverageStrength(new Float(strength));
        playerHistory.setForm(new Integer(record[AttributePosition.FORM]));
        playerHistory.setEnergy(new Integer(record[AttributePosition.ENERGY]));
        playerHistory.setEndurance(new Integer(record[AttributePosition.ENDURANCE]));
        playerHistory.setExperience(new Integer(record[AttributePosition.EXPERIENCE]));
        playerHistory.setSkillGoalkeeping(new Integer(record[AttributePosition.SKILL_GOALKEEPING]));
        playerHistory.setSkillTackling(new Integer(record[AttributePosition.SKILL_TACKLING]));
        playerHistory.setSkillPlaymaking(new Integer(record[AttributePosition.SKILL_PLAYMAKING]));
        playerHistory.setSkillPassing(new Integer(record[AttributePosition.SKILL_PASSING]));
        playerHistory.setSkillScoring(new Integer(record[AttributePosition.SKILL_SCORING]));
        playerHistory.setImportDate(importDate);
        history.put(playerHistory.getPlayerId(), playerHistory);
    }
}
