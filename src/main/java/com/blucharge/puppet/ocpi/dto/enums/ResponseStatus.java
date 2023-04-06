package com.blucharge.puppet.ocpi.dto.enums;

public enum ResponseStatus {
    OK(200, "SUCCESS"),
    BAD_REQUEST(400, "Invalid Request"),
    NOT_AUTHORIZED(401, "Access Denied"),
    NOT_FOUND(404, "Resource Not Found"),
    INTERNAL_ERROR(500, "Internal Error"),

    // Microservice errors codes are (generic error codes + 10)
    MICROSERVICE_INTERNAL_ERROR(510, "Internal Error while communication with microservice"),
    MICROSERVICE_EXCHANGE_ERROR(410, "Error communicating with third party"),
    MICROSERVICE_AUTHORIZATION_ERROR(411, "Error in authorizing with third party"),
    MICROSERVICE_RESOURCE_NOT_FOUND(414, "Error locating resource in third party");

    private int errorCode;

    private String errorMessage;

    ResponseStatus(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
