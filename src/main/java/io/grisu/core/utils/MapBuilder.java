package io.grisu.core.utils;

import java.util.HashMap;
import java.util.Map;

public class MapBuilder {
    Map map;

    public MapBuilder(Map map) {
        this.map = map;
    }

    public static MapBuilder instance() {
        return new MapBuilder(new HashMap());
    }

    public static MapBuilder instance(Map map, String... keys) {
        MapBuilder mapBuilder = instance();
        if (map != null && keys != null) {
            for (String k : keys) {
                Object v = map.get(k);
                if (v != null) {
                    mapBuilder.add(k, map.get(k));
                }
            }
        }
        return mapBuilder;
    }

    @SuppressWarnings(value = "unchecked")
    public <K, V> MapBuilder add(K k, V v) {
        if (k != null) {
            map.put(k, v);
        }
        return this;
    }

    public MapBuilder addAll(Map s) {
        if (s != null) {
            map.putAll(s);
        }
        return this;
    }

    public <K, V> Map<K, V> build() {
        return map;
    }

}

