package io.grisu.core.exceptions;

import java.util.HashMap;
import java.util.Map;

import io.grisu.core.GrisuConstants;
import io.grisu.core.utils.MapBuilder;
import io.grisu.core.utils.MapUtils;

public class GrisuException extends RuntimeException {

    private static final int DEFAULT_ERROR_STATUS_CODE = 500;

    public final static String ERROR = "error";
    public final static String ERRORS = "errors";
    public static final String ERROR_CODE = "errorCode";

    protected Map<String, Object> _errors;

    protected Integer errorCode;

    protected GrisuException() {
    }

    public GrisuException(Throwable e) {
        super(e);
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

    public Integer getErrorCode() {
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

    public Map<String, Object> serialize() {
        Map<String, Object> serialized = new HashMap<>();
        serialized.put(ERROR, "ServiceException");
        if (errorCode != null) {
            serialized.put(ERROR_CODE, errorCode);
        }
        if (_errors != null) {
            serialized.put(ERRORS, _errors);
        }
        return serialized;
    }

    public static GrisuException build(Map<String, Object> serialized) {
        GrisuException serviceException = new GrisuException();
        if (serialized != null) {
            if (serialized.containsKey(ERRORS)) {
                serviceException._errors = MapUtils.get(serialized, ERRORS, new HashMap<>());
            }
            if (serialized.containsKey(ERROR_CODE)) {
                serviceException.errorCode = (Integer) serialized.get(ERROR_CODE);
            }
        }
        return serviceException;
    }

}

