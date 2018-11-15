package io.grisu.core.utils;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class SetsTest {

    @Test
    public void shouldReturnASingleElementSet() {
        final Set<String> of = Sets.of("a");

        String[] ofArray = of.toArray(new String[] {});
        Assert.assertEquals(1, of.size());
        Assert.assertEquals("a", ofArray[0]);
    }

    @Test
    public void shouldReturnACoupleOfElementSet() {
        final Set<String> of = Sets.of("a", "b");

        String[] ofArray = of.toArray(new String[] {});
        Assert.assertEquals(2, of.size());
        Assert.assertEquals("a", ofArray[0]);
        Assert.assertEquals("b", ofArray[1]);
    }

    @Test
    public void shouldReturnATripletOfElementSet() {
        final Set<String> of = Sets.of("a", "b", "c");

        String[] ofArray = of.toArray(new String[] {});
        Assert.assertEquals(3, of.size());
        Assert.assertEquals("a", ofArray[0]);
        Assert.assertEquals("b", ofArray[1]);
        Assert.assertEquals("c", ofArray[2]);
    }

}
