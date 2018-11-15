package io.grisu.core.utils;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

public class MapUtils {

    public static TriFunction<Map, String, Object, Map> addToMap = (map, key, value) -> {
        map.put(key, value);
        return map;
    };

    public static <T> T get(Map map, String key, T defaultValue) {
        T retValue = get(map, key);
        if (retValue != null) {
            return retValue;
        }
        return defaultValue;
    }

    public static <T> T get(Map map, String key) {
        if (map != null && key != null) {
            final String[] split = key.split("\\.");

            Object loopValue = map;
            for (int i = 0; i < split.length; i++) {
                if (((Map) loopValue).containsKey(split[i])) {
                    loopValue = ((Map) loopValue).get(split[i]);
                } else {
                    return null;
                }
            }

            return (T) loopValue;
        }
        return null;
    }

    public static UUID uuid(Map map, String key) {

        final Object o = get(map, key);
        if (o != null) {
            if (o instanceof UUID) {
                return (UUID) o;
            }
            if (o instanceof String) {
                return java.util.UUID.fromString((String) o);
            }
        }

        return null;
    }

    public static Boolean b(Map<String, Object> map, String key) {
        final Object o = get(map, key);
        if (o != null) {
            if (o instanceof Boolean) {
                return (Boolean) o;
            }
            if (o instanceof String) {
                return Boolean.parseBoolean((String) o);
            }
        }

        return null;
    }

    public static Long l(Map<String, Object> map, String key) {
        final Object o = get(map, key);
        if (o != null) {
            if (o instanceof Long) {
                return (Long) o;
            }
            if (o instanceof String) {
                return Long.parseLong((String) o);
            }
            if (o instanceof Number) {
                return ((Number) o).longValue();
            }
        }

        return null;
    }

    public static Double d(Map<String, Object> map, String key) {
        final Object o = get(map, key);
        if (o != null) {
            if (o instanceof Double) {
                return (Double) o;
            }
            if (o instanceof String) {
                return Double.parseDouble((String) o);
            }
            if (o instanceof Number) {
                return ((Number) o).doubleValue();
            }
        }

        return null;
    }

    public static LocalDate localDate(Map<String, Object> map, String key) {
        final Object o = get(map, key);
        if (o != null) {
            if (o instanceof LocalDate) {
                return (LocalDate) o;
            }
            if (o instanceof String) {
                return LocalDate.parse((String) o);
            }
        }

        return null;
    }

}
