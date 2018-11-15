package io.grisu.core.utils;

import java.util.HashSet;
import java.util.Set;

public class Sets {

    public static <T> Set<T> of(T elem) {
        Set<T> set = new HashSet<>();
        set.add(elem);
        return set;
    }

    public static <T> Set<T> of(T elem1, T elem2) {
        Set<T> set = of(elem1);
        set.add(elem2);
        return set;
    }
    
    public static <T> Set<T> of(T... elems) {
        Set<T> set = new HashSet<>();
        for (int i = 0; i < elems.length; i++) {
            set.add(elems[i]);
        }
        return set;
    }

}
