package com.pnc.mblapi.automation.api;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.pnc.mblapi.automation.requestBody.ChallengeRequestBody;
import com.pnc.mblapi.automation.util.RestAPICookieFilter;
import org.json.JSONException;
import org.json.JSONObject;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by GWITHARANA on 4/21/2017.
 */
public class ChallengeApi {
    public static RequestSpecBuilder builder;
    public static RequestSpecification requestSpec;
    public static String ChallengeUrl= "/api/mbl/v1/auth/validate/challenge?bindDevice=false";
    public static Response res3;

    public ChallengeRequestBody setChallengeRequestBody(){
        ChallengeRequestBody challengeRequestBody = new ChallengeRequestBody();
        challengeRequestBody.setChallengeAnswer();
        challengeRequestBody.setDevicePrint();
        return challengeRequestBody;
    }

    public RequestSpecification getRequestSpecBuilder() {
        builder = new RequestSpecBuilder();
        builder.addHeader("X-Api-Key", "u134jppfdg4s3afhl0ajoduhd0");
        builder.setContentType("application/json");
        builder.setBody(setChallengeRequestBody());
        builder.setBaseUri("https://mobile-qa2.pnc.com");
        return builder.build();
    }

    public  Response getResponse() {
        res3  =  given()
                .filter(RestAPICookieFilter.instance())
                .spec(getRequestSpecBuilder()).
                 when().post(ChallengeUrl);
        return res3;

    }

    public  JSONObject getJsonResponseBody() throws JSONException, InterruptedException {
        JSONObject JSONResponseBody = new JSONObject(res3.body().asString());
        return JSONResponseBody;
    }


    public String getAuthenticationStatus() throws JSONException, InterruptedException {
        String authenticationStatus = getJsonResponseBody().getString("authStatus");
        System.out.println("Authenticate Status is  " + authenticationStatus);
        return authenticationStatus;

    }

}
