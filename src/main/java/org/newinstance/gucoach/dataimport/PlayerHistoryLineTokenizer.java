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

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.file.transform.DefaultFieldSetFactory;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.FieldSetFactory;
import org.springframework.batch.item.file.transform.LineTokenizer;

/**
 * TODO document me
 *
 * @author mwalter
 */
public class PlayerHistoryLineTokenizer implements LineTokenizer {

    private String delimiter;

    @Override
    public FieldSet tokenize(final String line) {
        final String[] fields = line.split(delimiter);
        if (fields.length != 1 && fields.length != 17) {
            return null;
        }

        final List<String> parsedFields = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            if ((i != 1) && (i != 2) && (i != 3) && (i != 4) && (i != 5) && (i != 7)) {
                parsedFields.add(fields[i]);
            }
        }
        final FieldSetFactory fieldSetFactory = new DefaultFieldSetFactory();
        System.out.println("+++++" + parsedFields.toString());
        return fieldSetFactory.create(parsedFields.toArray(new String[parsedFields.size()]));
    }

    public void setDelimiter(final String delimiter) {
        this.delimiter = delimiter;
    }
}
