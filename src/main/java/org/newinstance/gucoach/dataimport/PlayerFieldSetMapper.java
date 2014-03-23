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
import org.newinstance.gucoach.entity.Country;
import org.newinstance.gucoach.entity.Player;
import org.newinstance.gucoach.entity.PlayerStats;
import org.newinstance.gucoach.entity.Position;
import org.newinstance.gucoach.entity.StrongFoot;
import org.newinstance.gucoach.utility.PlayerAttributePosition;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 * Maps fields from the import file to player attributes.
 *
 * @author mwalter
 */
public class PlayerFieldSetMapper implements FieldSetMapper<Player> {

    /** The log4j logger. */
    private static final Logger LOGGER = LogManager.getLogger(PlayerFieldSetMapper.class.getName());

    private Date importDate = null;

    @Override
    public Player mapFieldSet(final FieldSet fieldSet) throws BindException {
        // if there's no import date yet try to find it
        if (importDate == null) {
            parseImportDate(fieldSet);
        }
        return mapPlayerFields(fieldSet);
    }

    /**
     * Maps fields from the import file to player attributes.
     *
     * @param fieldSet the fields to map
     * @return a {@link Player} entity
     */
    private Player mapPlayerFields(final FieldSet fieldSet) {
        final Player player = new Player();
        player.setId(fieldSet.readLong(PlayerAttributePosition.ID));
        player.setFirstName(fieldSet.readString(PlayerAttributePosition.FIRSTNAME));
        player.setLastName(fieldSet.readString(PlayerAttributePosition.LASTNAME));
        try {
            player.setCountry(Country.valueOf(fieldSet.readString(PlayerAttributePosition.COUNTRY)));
        } catch (final IllegalArgumentException e) {
            // TODO remove if all countries are provided
            LOGGER.info("Country [{}] not recognized.", fieldSet.readString(PlayerAttributePosition.COUNTRY), e);
        }
        player.setHeight(fieldSet.readInt(PlayerAttributePosition.HEIGHT));
        player.setPersonality(fieldSet.readString(PlayerAttributePosition.PERSONALITY));
        player.setBirthday(fieldSet.readString(PlayerAttributePosition.BIRTHDAY));
        player.setStrongFoot(StrongFoot.valueOf(fieldSet.readString(PlayerAttributePosition.STRONG_FOOT).toUpperCase()));

        player.setImportDate(importDate);

        // map statistic fields for player
        mapPlayerStatsFields(fieldSet, player);

        LOGGER.info(player.toString());
        return player;
    }

    /**
     * Maps fields from the import file to player statistics attributes.
     *
     * @param fieldSet the fields to map
     */
    private void mapPlayerStatsFields(final FieldSet fieldSet, final Player player) {
        final PlayerStats playerStats = new PlayerStats();
        // associate player stats with player
        playerStats.setPlayer(player);
        // and vice versa
        playerStats.getPlayer().setPlayerStats(playerStats);

        playerStats.setNumber(fieldSet.readInt(PlayerAttributePosition.NUMBER));
        // is training filled?
        final String training = fieldSet.readString(PlayerAttributePosition.TRAINING);
        playerStats.setTraining(StringUtils.isEmpty(training) ? null : training);
        // erase apostrophe in strength value
        final String strength = StringUtils.remove(fieldSet.readString(PlayerAttributePosition.STRENGTH), '\'');
        playerStats.setAverageStrength(new Float(strength));
        playerStats.setPosition(Position.valueOf(fieldSet.readString(PlayerAttributePosition.POSITION)));
        playerStats.setForm(fieldSet.readInt(PlayerAttributePosition.FORM));
        playerStats.setEnergy(fieldSet.readInt(PlayerAttributePosition.ENERGY));
        playerStats.setEndurance(fieldSet.readInt(PlayerAttributePosition.ENDURANCE));
        playerStats.setExperience(fieldSet.readInt(PlayerAttributePosition.EXPERIENCE));
        playerStats.setSkillGoalkeeping(fieldSet.readInt(PlayerAttributePosition.SKILL_GOALKEEPING));
        playerStats.setSkillTackling(fieldSet.readInt(PlayerAttributePosition.SKILL_TACKLING));
        playerStats.setSkillPlaymaking(fieldSet.readInt(PlayerAttributePosition.SKILL_PLAYMAKING));
        playerStats.setSkillPassing(fieldSet.readInt(PlayerAttributePosition.SKILL_PASSING));
        playerStats.setSkillScoring(fieldSet.readInt(PlayerAttributePosition.SKILL_SCORING));
        playerStats.setTalent(fieldSet.readString(PlayerAttributePosition.TALENT));
        playerStats.setTalentLevel(fieldSet.readInt(PlayerAttributePosition.TALENT_LEVEL));
        playerStats.setAge(fieldSet.readInt(PlayerAttributePosition.AGE));
        playerStats.setSalary(fieldSet.readInt(PlayerAttributePosition.SALARY));
        playerStats.setAssignments(fieldSet.readInt(PlayerAttributePosition.ASSIGNMENTS));
        playerStats.setGoalsSeason(fieldSet.readInt(PlayerAttributePosition.GOALS_SEASON));
        playerStats.setGoalsTotal(fieldSet.readInt(PlayerAttributePosition.GOALS_TOTAL));
        // is market value filled?
        final String marketValue = fieldSet.readString(PlayerAttributePosition.MARKET_VALUE);
        playerStats.setMarketValue(StringUtils.isEmpty(marketValue) ? null : new Integer(marketValue));
        playerStats.setYellowCardsSeason(fieldSet.readInt(PlayerAttributePosition.YELLOW_CARDS_SEASON));
        playerStats.setYellowCardsTotal(fieldSet.readInt(PlayerAttributePosition.YELLOW_CARDS_TOTAL));
        playerStats.setRedCardsSeason(fieldSet.readInt(PlayerAttributePosition.RED_CARDS_SEASON));
        playerStats.setRedCardsTotal(fieldSet.readInt(PlayerAttributePosition.RED_CARDS_TOTAL));

        playerStats.setImportDate(importDate);
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
