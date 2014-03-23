/*
 * GUCoach - your personal coach for Goalunited (tm).
 * Licensed under General Public License v3 (GPLv3)
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

/*
 * GUCoach - your personal coach for Goalunited (tm).
 * Licensed under General Public License v3 (GPLv3)
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

package org.newinstance.gucoach.dataimport;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newinstance.gucoach.entity.PlayerHistory;
import org.newinstance.gucoach.service.PlayerService;
import org.newinstance.gucoach.utility.PlayerHistoryAttributePosition;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;

/**
 * Maps fields from the import file to player history attributes.
 *
 * @author mwalter
 */
public class PlayerHistoryFieldSetMapper implements FieldSetMapper<PlayerHistory> {

    /** The log4j logger. */
    private static final Logger LOGGER = LogManager.getLogger(PlayerHistoryFieldSetMapper.class.getName());

    @Autowired
    private PlayerService playerService;

    private Date importDate = null;

    @Override
    public PlayerHistory mapFieldSet(final FieldSet fieldSet) throws BindException {
        // if there's no import date yet try to find it
        if (importDate == null) {
            parseImportDate(fieldSet);
        }
        return mapPlayerHistoryFields(fieldSet);
    }

    /**
     * Maps fields from the import file to player history attributes.
     *
     * @param fieldSet the fields to map
     * @return a {@link PlayerHistory} entity
     */
    private PlayerHistory mapPlayerHistoryFields(final FieldSet fieldSet) {
        LOGGER.info("MAPPING >> " + fieldSet.toString());
        LOGGER.info(playerService.findAll());
        final PlayerHistory playerHistory = new PlayerHistory();
        playerHistory.setPlayer(playerService.findOne(fieldSet.readLong(PlayerHistoryAttributePosition.ID)));
        // erase apostrophe in strength value
        final String strength = StringUtils.remove(fieldSet.readString(PlayerHistoryAttributePosition.STRENGTH), '\'');
        playerHistory.setAverageStrength(new Float(strength));
        playerHistory.setForm(fieldSet.readInt(PlayerHistoryAttributePosition.FORM));
        playerHistory.setEnergy(fieldSet.readInt(PlayerHistoryAttributePosition.ENERGY));
        playerHistory.setEndurance(fieldSet.readInt(PlayerHistoryAttributePosition.ENDURANCE));
        playerHistory.setExperience(fieldSet.readInt(PlayerHistoryAttributePosition.EXPERIENCE));
        playerHistory.setSkillGoalkeeping(fieldSet.readInt(PlayerHistoryAttributePosition.SKILL_GOALKEEPING));
        playerHistory.setSkillTackling(fieldSet.readInt(PlayerHistoryAttributePosition.SKILL_TACKLING));
        playerHistory.setSkillPlaymaking(fieldSet.readInt(PlayerHistoryAttributePosition.SKILL_PLAYMAKING));
        playerHistory.setSkillPassing(fieldSet.readInt(PlayerHistoryAttributePosition.SKILL_PASSING));
        playerHistory.setSkillScoring(fieldSet.readInt(PlayerHistoryAttributePosition.SKILL_SCORING));
        playerHistory.setImportDate(importDate);

        LOGGER.info(playerHistory.toString());
        return playerHistory;
    }

    /**
     * Parse the date of the file.
     *
     * @param fieldSet the record to parse the file from
     */
    private void parseImportDate(final FieldSet fieldSet) {
        final String line = fieldSet.toString();
        // TODO should work with all locale date formats used in the import file
        if (line.matches(".+([0-9]{2}).([0-9]{2}).([0-9]{4}).+")) {
            final DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            final String date = line.substring(line.length() - 11, line.length() - 1);
            try {
                importDate = df.parse(date);
            } catch (final ParseException pe) {
                LOGGER.error("Import date could not be parsed.", pe);
                // TODO handle error
            }
            LOGGER.info("Found import date {}.", df.format(importDate));
        }
    }
}
