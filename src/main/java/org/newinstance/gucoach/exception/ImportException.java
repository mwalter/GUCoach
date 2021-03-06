/*
 * GUCoach - your personal coach for Goalunited (tm).
 * Licenced under General Public Licence v3 (GPLv3)
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

package org.newinstance.gucoach.exception;

import org.newinstance.gucoach.utility.MessageId;

/**
 * Thrown to indicate that an error occurred during file import. Holds the message id referencing the error message to display.
 *
 * @author mwalter
 */
public class ImportException extends Exception {

    /* Holds the message id. */
    private MessageId messageId;

    public ImportException(final MessageId messageId) {
        this.messageId = messageId;
    }

    public ImportException(final MessageId messageId, final Throwable cause) {
        super(cause);
        this.messageId = messageId;
    }

    /**
     * Returns the message id.
     *
     * @return the message id
     */
    public MessageId getMessageId() {
        return messageId;
    }
}
