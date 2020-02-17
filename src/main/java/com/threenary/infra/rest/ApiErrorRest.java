package com.threenary.infra.rest;

import javax.servlet.http.HttpServletRequest;

import com.threenary.infra.error.ApiErrorEnum;


/**
 * Wraps API Error to be returned as a Rest Resource
 */
public class ApiErrorRest
{
    private final String description;
    private final String url;

    ApiErrorRest(final HttpServletRequest request, final ApiErrorEnum error, final String... errorParameters)
    {
        this.description = error.getErrorDescription(errorParameters);
        this.url = request.getRequestURL().toString();
    }

    public String getDescription()
    {
        return description;
    }

    public String getUrl()
    {
        return url;
    }
}

