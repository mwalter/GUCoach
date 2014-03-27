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

package org.newinstance.gucoach.utility;

/**
 * Provides all message ids used by the application. The keys map to the corresponding messages in the resource bundle.
 *
 * @author mwalter
 */
public enum MessageId {

    E001("error.information"),
    E002("error.import.file.parsing"),
    E003("error.import.file.empty"),
    E004("error.import.file.reading"),
    E005("error.import.date.missing"),
    E006("error.import.date.invalid"),
    E007("error.import.encoding.invalid"),
    V001("validation.import.file.already.imported"),
    V002("validation.import.missing.player.record"),
    V003("validation.import.missing.player.data"),
    V004("validation.date.invalid"),
    V005("validation.teams.incomplete"),
    V006("validation.teams.duplicate.team.name"),
    I001("info.player.data.unavailable"),
    I002("info.standings.data.unavailable");

    /** The message key in the resource bundle. */
    private String messageKey;

    MessageId(final String messageKey) {
        this.messageKey = messageKey;
    }

    /**
     * Returns the message key.
     *
     * @return the message key
     */
    public String getMessageKey() {
        return messageKey;
    }
}
