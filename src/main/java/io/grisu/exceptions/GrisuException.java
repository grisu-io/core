package io.grisu.exceptions;

import java.util.HashMap;
import java.util.Map;

import io.grisu.GrisuConstants;
import io.grisu.utils.MapBuilder;
import io.grisu.utils.MapUtils;

public class GrisuException extends RuntimeException {

    private static final int DEFAULT_ERROR_STATUS_CODE = 500;

    protected Map<String, Object> _errors;

    protected int errorCode;

    protected GrisuException() {
    }

    public GrisuException(String error, String... errors) {
        this(DEFAULT_ERROR_STATUS_CODE, error, errors);
    }

    public GrisuException(int errorCode, String error, String... errors) {
        if (errors.length % 2 != 0) {
            throw new RuntimeException("Errors array must be even");
        }
        _errors = MapBuilder.instance().add(GrisuConstants.ERROR, error).build();
        for (int i = 0; i < errors.length; i = i + 2) {
            _errors.put(errors[i], errors[i + 1]);
        }
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public Map<String, Object> getErrors() {
        return _errors;
    }

    @Override
    public String getMessage() {
        if (_errors != null) {
            return _errors.toString();
        }
        return "no provided errors";
    }

    public static GrisuException build(Map<String, Object> serialized) {
        GrisuException serviceException = new GrisuException();
        if (serialized != null) {
            serviceException._errors = MapUtils.get(serialized, "errors", new HashMap<String, Object>());
            serviceException.errorCode = (Integer) serialized.get("errorCode");
        }
        return serviceException;
    }

}

