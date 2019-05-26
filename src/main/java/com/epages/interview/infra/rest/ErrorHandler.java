package com.epages.interview.infra.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.epages.interview.infra.error.ApiErrorEnum;


/**
 * Class to capture and wrapp different errors and return specific for the business purpose
 * It can be populated with all the desire handle methods for each case
 */
@ControllerAdvice
public class ErrorHandler
{
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    ApiErrorRest handleException(final HttpServletRequest request, final Exception e)
    {
        return new ApiErrorRest(request, ApiErrorEnum.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    ApiErrorRest handleHttpRequestMethodNotSupportedException(final HttpServletRequest request,
                                                              final HttpRequestMethodNotSupportedException exception)
    {
        return new ApiErrorRest(request, ApiErrorEnum.METHOD_NOT_SUPPORTED, exception.getMethod());
    }
}
