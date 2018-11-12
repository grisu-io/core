package io.grisu.utils;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ListsTest {

    @Test
    public void shouldConcatTwoLists() throws Exception {
        List<Integer> list = Lists.concat(Arrays.asList(1, 2), Arrays.asList(3, 4), Arrays.asList(5, 6));
        assertEquals(list, Arrays.asList(1, 2, 3, 4, 5, 6));
    }
    
}
