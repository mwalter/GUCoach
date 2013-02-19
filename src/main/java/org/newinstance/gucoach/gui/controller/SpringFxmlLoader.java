/*
 * GUCoach - your personal coach for Goalunited (tm).
 * Licenced under General Public Licence v3 (GPLv3)
 * newInstance.org, 2013
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

package org.newinstance.gucoach.gui.controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.util.Callback;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Loads JavaFX FXML files and initialises resources and related Spring beans (controllers) via Spring application context.
 *
 * @author mwalter
 */
public class SpringFxmlLoader {

    /** Spring application context. */
    private static ApplicationContext context;

    /**
     * The constructor need the Spring application context.
     *
     * @param context the Spring application context
     */
    public SpringFxmlLoader(final ApplicationContext context) {
        this.context = context;
    }

    /**
     * Loads a JavaFX FXML resource file.
     *
     * @param url the URL to load the file from
     * @return the object hierarchy from the FXML document
     */
    public Object load(final String url) {
        try {
            final URL location = getClass().getResource(url);
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setResources(ResourceBundle.getBundle("ApplicationResources"));
            loader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> clazz) {
                    return context.getBean(clazz);
                }
            });
            return loader.load(location.openStream());
        } catch (final IOException e) {
            // TODO handle exception
            throw new RuntimeException(e);
        }
    }
}
