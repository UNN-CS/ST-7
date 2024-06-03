package com.stepanew.entities;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Paths {

    private final Map<String, String> paths;

    {
        paths = new HashMap<>();
    }

    public void addPath(String key, String value) {
        paths.put(key, value);
    }

    public String getPath(String key) {
        return paths.get(key);
    }

    public Map<String, String> getPaths() {
        return Collections.unmodifiableMap(paths);
    }

}
