package org.finleap.exception;

public enum ApiException {

    VALIDATION_EMPTY_REQUEST_BODY("Empty city"),
    VALIDATION_INVALID_CITY("Invalid City"),
    VALIDATION_CITY_NOT_FOUND("City Not Found"),
    UNEXPECTED_ERROR("Bad Request");

    private String message;

    ApiException(String message){
        this.message = message;
    }

    public String message(){
        return this.message;
    }
}
