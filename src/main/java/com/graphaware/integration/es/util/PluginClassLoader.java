/*
 * Copyright (c) 2013-2016 GraphAware
 *
 * This file is part of the GraphAware Framework.
 *
 * GraphAware Framework is free software: you can redistribute it and/or modify it under the terms of
 * the GNU General Public License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details. You should have received a copy of
 * the GNU General Public License along with this program.  If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.graphaware.integration.es.util;

import java.lang.annotation.Annotation;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.reflections.Reflections;

public final class PluginClassLoader {

    private static Reflections reflections;

    public static <T, A extends Annotation> Map<String, Class<T>> loadClass(Class<T> type, Class<A> annotation) {
        return loadClassByAnnotation(type, annotation);
    }

    private static <T, A extends Annotation> Map<String, Class<T>> loadClassByAnnotation(Class<T> type, Class<A> annotation) {
        if (reflections == null) {
            loadReflections("com.graphaware.integration.es");
        }
        Map<String, Class<T>> loader = new HashMap<>();
        Set<Class<?>> providers = reflections.getTypesAnnotatedWith(annotation);
        for (Class<?> item : providers) {
            loader.put(item.getName(), (Class<T>) item);
        }
        return loader;
    }

    private static void loadReflections(final String packagePath) { //todo how to change package path?
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                reflections = new Reflections("com.graphaware.integration.es");
                return null; // nothing to return
            }
        });
    }

    private PluginClassLoader() {
    }
}
