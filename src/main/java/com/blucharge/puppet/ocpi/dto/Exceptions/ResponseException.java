package com.blucharge.puppet.ocpi.dto.Exceptions;

import java.util.Map;

public class ResponseException extends RuntimeException {

    private final Integer appErrorCode;
    private final String errorMessage;
    private Map<String, Object> responseData;


    public ResponseException(Integer appErrorCode, String errorMessage) {
        super(errorMessage);
        this.appErrorCode = appErrorCode;
        this.errorMessage = errorMessage;
    }

    public ResponseException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.appErrorCode = 400;
        this.responseData = null;
    }

    public ResponseException(Integer appErrorCode, String errorMessage, Map<String, Object> responseData) {
        super(errorMessage);

        this.appErrorCode = appErrorCode;
        this.errorMessage = errorMessage;
        this.responseData = responseData;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Integer getErrorCode() {
        return appErrorCode;
    }

    public Map<String, Object> getResponseData() {
        return responseData;
    }
}