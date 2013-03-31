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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Loads JavaFX FXML files and initialises resources and related Spring beans (controllers) via Spring application context.
 *
 * @author mwalter
 */
@Component
public class SpringFxmlLoader {

    /** The application resources. */
    private static final String APPLICATION_RESOURCES = "ApplicationResources";
    /** Spring application context. */
    @Autowired
    private ApplicationContext context;

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
            loader.setResources(ResourceBundle.getBundle(APPLICATION_RESOURCES));
            // the controller is a Spring managed bean and hence loaded from the Spring context
            loader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> clazz) {
                    return context.getBean(clazz);
                }
            });
            return loader.load(location.openStream());
        } catch (final IOException e) {
            // should never happen
            throw new RuntimeException(e);
        }
    }
}
