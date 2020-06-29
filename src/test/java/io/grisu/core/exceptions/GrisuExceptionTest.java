package io.grisu.core.exceptions;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class GrisuExceptionTest {
    static class ObjectWithToString {
        String prop1;
        Double prop2;

        public ObjectWithToString(String prop1, Double prop2) {
            this.prop1 = prop1;
            this.prop2 = prop2;
        }

        @Override
        public String toString() {
            return "ObjectWithToString{" +
                "prop1='" + prop1 + '\'' +
                ", prop2=" + prop2 +
                '}';
        }
    }

    @Test
    public void getMessage_nullErrors() {
        GrisuException gex = new GrisuException(123, "errorMessage", (Map<String, Object>) null);
        assertEquals("no provided errors", gex.getMessage());
    }

    @Test
    public void getMessage_emptyErrors() {
        GrisuException gex = new GrisuException(123, "errorMessage", new HashMap<>());
        assertEquals("no provided errors", gex.getMessage());
    }

    @Test
    public void getMessage_withErrors() {
        Map<String, Object> errors = new HashMap<>();
        errors.put("stringError", "error one");
        errors.put("objectError", new ObjectWithToString("prop2", 123.45));

        GrisuException gex = new GrisuException(123, "errorMessage", errors);
        String message = gex.getMessage();

        assertTrue(message.contains("stringError=error one"));
        assertTrue(message.contains("objectError=ObjectWithToString{prop1='prop2', prop2=123.45}"));
    }
}