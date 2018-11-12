package io.grisu.utils;

import java.util.concurrent.CompletionException;

public class ExceptionUtils {

    private static final int MAX_DEPTH = 3;

    public static Throwable findRootException(Throwable throwable) {
        for (int i = 0; i < MAX_DEPTH && throwable instanceof CompletionException && throwable.getCause() != null; i++) {
            throwable = throwable.getCause();
        }
        return throwable;
    }

}