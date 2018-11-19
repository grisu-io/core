package io.grisu.core.utils;

import java.time.LocalDate;
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
    public void shouldReturnANullLongFromANullValue() {
        Map<String, Object> map = new HashMap<>();
        map.put("myNull", null);
        assertEquals(null, MapUtils.l(map, "myNull"));
    }

    @Test
    public void shouldReturnADoubleFromAString() {
        Map<String, Object> map = new HashMap<>();
        map.put("myDouble", "25");
        assertEquals(new Double(25), MapUtils.d(map, "myDouble"));
    }

    @Test
    public void shouldReturnADoubleFromANumber() {
        Map<String, Object> map = new HashMap<>();
        map.put("myDouble", 26);
        assertEquals(new Double(26), MapUtils.d(map, "myDouble"));
    }

    @Test
    public void shouldReturnANullDoubleFromANullValue() {
        Map<String, Object> map = new HashMap<>();
        map.put("myDouble", null);
        assertEquals(null, MapUtils.d(map, "myNull"));
    }

    @Test
    public void shouldReturnALocalDate() {
        Map<String, Object> map = new HashMap<>();
        map.put("myDate", "1972-09-01");
        final LocalDate myDate = MapUtils.localDate(map, "myDate");

        assertEquals(9, myDate.getMonthValue());
        assertEquals(1972, myDate.getYear());
        assertEquals(1, myDate.getDayOfMonth());
    }

    @Test
    public void shouldReturnAnIntegerFromAString() {
        Map<String, Object> map = new HashMap<>();
        map.put("myInt", "25");
        assertEquals(new Integer(25), MapUtils.i(map, "myInt"));
    }

    @Test
    public void shouldReturnAnIntegerFromANumber() {
        Map<String, Object> map = new HashMap<>();
        map.put("myInt", 26);
        assertEquals(new Integer(26), MapUtils.i(map, "myInt"));
    }

    @Test
    public void shouldReturnANullIntegerFromANullValue() {
        Map<String, Object> map = new HashMap<>();
        map.put("myInt", null);
        assertEquals(null, MapUtils.i(map, "myInt"));
    }
}