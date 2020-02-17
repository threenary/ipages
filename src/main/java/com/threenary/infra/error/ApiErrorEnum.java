package com.threenary.infra.error;

import org.springframework.http.HttpStatus;


/**
 * Enumeration of the possible error codes and messages for the entire API
 */
public enum ApiErrorEnum
{
    // Default error code
    INTERNAL_SERVER_ERROR("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),

    // Common error codes for request handling
    METHOD_NOT_SUPPORTED("%s is not supported", HttpStatus.METHOD_NOT_ALLOWED);

    private final String errorText;
    private final HttpStatus httpStatus;

    ApiErrorEnum(final String errorText, final HttpStatus httpStatus)
    {
        this.errorText = errorText;
        this.httpStatus = httpStatus;
    }

    public String getErrorCode()
    {
        return name();
    }

    public String getErrorDescription(final String... parameters)
    {
        return String.format(errorText, (Object[]) parameters);
    }

    public HttpStatus getHttpStatus()
    {
        return httpStatus;
    }

    @Override
    public String toString()
    {
        return "API Error [errorText=" + errorText + ", Http Status=" + httpStatus + "]";
    }
}
