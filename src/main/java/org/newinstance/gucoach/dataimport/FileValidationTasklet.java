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

package org.newinstance.gucoach.dataimport;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newinstance.gucoach.exception.ValidationException;
import org.newinstance.gucoach.service.PlayerHistoryService;
import org.newinstance.gucoach.utility.MessageId;
import org.newinstance.gucoach.utility.ResourceLoader;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * TODO document me
 *
 * @author mwalter
 */
@Component
public class FileValidationTasklet implements Tasklet {

    /** The log4j logger. */
    private static final Logger LOGGER = LogManager.getLogger(FileValidationTasklet.class.getName());

    @Autowired
    private PlayerHistoryService playerHistoryService;

    private Resource inputResource;

    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
        final FileInputStream fis = new FileInputStream(inputResource.getFile());
        final InputStreamReader isr = new InputStreamReader(fis, "ISO-8859-1");
        final BufferedReader br = new BufferedReader(isr);

        int playerRecordCounter = 0;
        Date importDate = null;
        String line;

        while ((line = br.readLine()) != null) {
            System.out.println(line);
            if (line.matches(".+([0-9]{2})\\.([0-9]{2})\\.([0-9]{4}).*")) {
                final DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
                final String date = line.substring(line.length() - 11, line.length() - 1);
                try {
                    importDate = df.parse(date);
                } catch (final ParseException pe) {
                    LOGGER.error("Import date could not be parsed.", pe);
                    throw pe;
                }
                LOGGER.info("Found import date {}.", df.format(importDate));
            }
            if (line.matches("^\\d+.*")) {
                playerRecordCounter++;
            }
        }

        System.out.println(playerRecordCounter);

        if (playerRecordCounter % 3 != 0) {
            System.out.println("Geht nicht auf.");
        }

        if (importDate == null) {
            System.out.println("Kein Import Datum in der Datei.");
        }

        if (importDate != null) {
            final List<Date> allImportDatesInDb = playerHistoryService.findAllImportDates();
            if (allImportDatesInDb.contains(importDate)) {
                final String message = ResourceLoader.getMessage(MessageId.V001.getMessageKey(), inputResource.getFile().getName());
                throw new ValidationException(message);
            }
        }

        fis.close();
        return RepeatStatus.FINISHED;
    }

    public void setInputFile(final Resource inputFile) {
        inputResource = inputFile;
    }
}
