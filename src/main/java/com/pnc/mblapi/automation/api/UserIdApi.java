package com.pnc.mblapi.automation.api;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.pnc.mblapi.automation.requestBody.UserIdRequestBody;
import com.pnc.mblapi.automation.util.RestAPICookieFilter;
import org.json.JSONException;
import org.json.JSONObject;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by GWITHARANA on 4/19/2017.
 */
public class UserIdApi {
    public static RequestSpecBuilder builder;
    public static RequestSpecification requestSpec;
    public static String UserIdUrl= "/api/mbl/v1/auth/validate/userid?saveUserId=false";
    public static Response res1;


    public UserIdRequestBody setUserIdRequestBody(){
        UserIdRequestBody userIdRequestBody = new UserIdRequestBody();
        userIdRequestBody.setUserId();
        userIdRequestBody.setDevicePrint();
        userIdRequestBody.setAppVersion();
        return userIdRequestBody;
    }

    public RequestSpecification getRequestSpecBuilder() {
        builder = new RequestSpecBuilder();
        builder.addHeader("X-Api-Key", "u134jppfdg4s3afhl0ajoduhd0");
        builder.setContentType("application/json");
        builder.setBody(setUserIdRequestBody());
        builder.setBaseUri("https://mobile-qa2.pnc.com");
        return builder.build();
    }

    public  Response getResponse() {
        res1  =  given()
                .filter(RestAPICookieFilter.instance())
                .spec(getRequestSpecBuilder()).
                 when().post(UserIdUrl);
        return res1;

    }

    public static JSONObject getJsonResponseBody()throws JSONException   {
        JSONObject JSONResponseBody = new JSONObject(res1.body().asString());
        return JSONResponseBody;
    }


    public  RequestSpecification getRequestSpecification() {
        return given().spec(getRequestSpecBuilder());
    }

    public static String getAuthenticationStatus() throws JSONException, InterruptedException {
        String authenticationStatus = getJsonResponseBody().getString("authStatus");
        System.out.println("Authenticate Status is  " + authenticationStatus);
        return authenticationStatus;

    }


    }



