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

import java.io.File;

/**
 * The import controller provides methods for the import process.
 *
 * @author mwalter
 */
public interface ImportController {

    /**
     * Executes an import process for a given file. Validates and persists the data.
     *
     * @param file the file to import
     * @throws ImportException if an import error occurs
     * @throws ValidationException if a validation error occurs
     */
    public void executeImport(final File file) throws ImportException, ValidationException;
}
