package io.grisu.core.utils;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.*;

public class MapUtilsTest {

    @Test
    public void shouldFindANestedProperty() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();

        map.put("l1", map1);
        map1.put("l2", map2);

        map2.put("prop", "here you are!");

        assertEquals("here you are!", MapUtils.get(map, "l1.l2.prop"));
        assertNull( MapUtils.get(map, "l1.l2.propNotExisting"));
        assertNull( MapUtils.get(map, "l1.levelNotExisting.propNotExisting"));
    }

}