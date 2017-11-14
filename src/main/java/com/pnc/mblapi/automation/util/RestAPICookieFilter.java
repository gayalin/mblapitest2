package com.pnc.mblapi.automation.util;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by GWITHARANA on 4/20/2017.
 */
public class RestAPICookieFilter implements Filter {

    private Map<String, String> cookies = new HashMap<String, String>();

    private static RestAPICookieFilter instance;



    public static RestAPICookieFilter instance() {
        if (instance == null) {
            instance = new RestAPICookieFilter();
        }
        return instance;
    }

    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec, FilterContext ctx) {

        // add all previously stored cookies to subsequent requests
        // but only if they're not already in the request spec
        for (Map.Entry<String, String> cookie : cookies.entrySet()) {
            if (!requestSpec.getCookies().hasCookieWithName(cookie.getKey())) {
                requestSpec.cookie(cookie.getKey(), cookie.getValue());
            }
        }

        final Response response = ctx.next(requestSpec, responseSpec);

        System.out.println("Response Cookies: " + response.getCookies());
        cookies.putAll(response.getCookies());

        return response;
    }
}