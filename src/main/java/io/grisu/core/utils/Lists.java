package io.grisu.core.utils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Lists {

    public static <I> List<I> of(I elem) {
        List<I> list = new LinkedList<>();
        list.add(elem);
        return list;
    }

    public static <I> List<I> of(I elem1, I elem2) {
        List<I> list = of(elem1);
        list.add(elem2);
        return list;
    }

    public static <I> List<I> of(I... elems) {
        List<I> list = new LinkedList<>();
        Collections.addAll(list, elems);
        return list;
    }

    public static <I> List<I> concat(List<I> list1, List<I> list2) {
        List<I> list = new LinkedList<>();
        list.addAll(list1);
        list.addAll(list2);
        return list;
    }

    public static <I> List<I> concat(List<I> list1, List<I> list2, List<I> list3) {
        List<I> list = concat(list1, list2);
        list.addAll(list3);
        return list;
    }

    public static void requireNotEmpty(List<?> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Empty list");
        }
    }

    public static <T> T firstOrNull(List list) {
        return firstOr(list, null);
    }

    public static <T> T firstOr(List list, T defaultValue) {
        if (list != null && list.size() > 0 ) {
            return (T) list.get(0);
        }
        return defaultValue;
    }

    public static <T> T firstOrThrow(List list, RuntimeException e) throws RuntimeException {
        if (list != null && list.size() > 0 ) {
            return (T) list.get(0);
        }
        throw e;
    }

    public static <T> Optional<T> optionallyFirst(List list) {
        if (list != null && list.size() > 0 ) {
            return Optional.of((T) list.get(0));
        }
        return Optional.empty();
    }

    public static boolean isEmpty(List list) {
        if (list != null && list.size() > 0) {
            return false;
        }
        return true;
    }
}
