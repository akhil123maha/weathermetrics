package org.finleap.exception;


public final class ValidationException extends RuntimeException {
    private String errorMessage;

    public ValidationException(ApiException apiException){
        super(apiException.message());
        this.errorMessage = apiException.message();
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
