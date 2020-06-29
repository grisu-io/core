package io.grisu.core.exceptions;

import io.grisu.core.GrisuConstants;
import io.grisu.core.utils.MapUtils;

import java.util.HashMap;
import java.util.Map;

public class GrisuException extends RuntimeException {

    protected Map<String, Object> _errors = new HashMap<>();
    protected Integer errorCode = null;

    protected String errorMessage = null;

    protected GrisuException() {
    }

    public GrisuException(Throwable e) {
        super(e);
    }

    public GrisuException(Integer errorCode, String errorMessage, Map<String, Object> _errors) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this._errors = _errors;
    }

    public GrisuException(int errorCode, String errorMessage, String... errorsArray) {
        if (errorsArray.length % 2 != 0) {
            throw new RuntimeException("Errors array must be even");
        }

        for (int i = 0; i < errorsArray.length; i = i + 2) {
            this._errors.put(errorsArray[i], errorsArray[i + 1]);
        }

        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Map<String, Object> getErrors() {
        return _errors;
    }

    @Override
    public String getMessage() {
        if (_errors != null && !_errors.isEmpty()) {
            return _errors.toString();
        }
        return "no provided errors";
    }

    public Map<String, Object> serialize() {
        Map<String, Object> serialized = new HashMap<>();

        if (errorCode != null) {
            serialized.put(GrisuConstants.ERROR_CODE, errorCode);
        }

        if (errorMessage != null) {
            serialized.put(GrisuConstants.ERROR_MESSAGE, errorMessage);
        }

        if (_errors != null) {
            serialized.put(GrisuConstants.ERRORS, _errors);
        }

        return serialized;
    }

    public static GrisuException build(Map<String, Object> serialized) {
        GrisuException grisuException = new GrisuException();
        if (serialized != null) {

            if (serialized.containsKey(GrisuConstants.ERRORS)) {
                grisuException._errors = MapUtils.get(serialized, GrisuConstants.ERRORS, new HashMap<>());
            }

            if (serialized.containsKey(GrisuConstants.ERROR_CODE)) {
                grisuException.errorCode = (Integer) serialized.get(GrisuConstants.ERROR_CODE);
            }

            if (serialized.containsKey(GrisuConstants.ERROR_MESSAGE)) {
                grisuException.errorMessage = (String) serialized.get(GrisuConstants.ERROR_MESSAGE);
            }

        }
        return grisuException;
    }

}

