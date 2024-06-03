package com.stepanew.entities;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс для хранения и управления путями (ключ-значение).
 * Предоставляет методы для добавления пути, получения пути по ключу и
 * получения неизменяемой мапы всех путей.
 */
public class Paths {

    private final Map<String, String> paths;

    {
        paths = new HashMap<>();
    }

    /**
     * Добавляет путь в карту.
     *
     * @param key   ключ пути
     * @param value значение пути
     */
    public void addPath(String key, String value) {
        paths.put(key, value);
    }

    /**
     * Возвращает значение пути по заданному ключу.
     *
     * @param key ключ пути
     * @return значение пути
     */
    public String getPath(String key) {
        return paths.get(key);
    }

    /**
     * Возвращает неизменяемую мапу всех путей.
     *
     * @return неизменяемая мапа путей
     */
    public Map<String, String> getPaths() {
        return Collections.unmodifiableMap(paths);
    }

}
