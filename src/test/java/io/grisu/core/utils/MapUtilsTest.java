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

    @Test
    public void shouldReturnALongFromAString() {
        Map<String, Object> map = new HashMap<>();
        map.put("myLong", "25");
        assertEquals(new Long(25), MapUtils.l(map, "myLong"));
    }

    @Test
    public void shouldReturnALongFromANumber() {
        Map<String, Object> map = new HashMap<>();
        map.put("myNumber", 26);
        assertEquals(new Long(26), MapUtils.l(map, "myNumber"));
    }

    @Test
    public void shouldReturnANullNumberFromANullValue() {
        Map<String, Object> map = new HashMap<>();
        map.put("myNull", null);
        assertEquals(null, MapUtils.l(map, "myNull"));
    }
    
}