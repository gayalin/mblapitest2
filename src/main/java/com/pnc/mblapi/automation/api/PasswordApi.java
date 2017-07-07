package com.pnc.mblapi.automation.api;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.pnc.mblapi.automation.requestBody.PasswordRequestBody;
import com.pnc.mblapi.automation.util.RestAPICookieFilter;
import com.pnc.mblapi.automation.util.RestUtil;
import org.json.JSONException;
import com.jayway.restassured.path.json.JsonPath;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by GWITHARANA on 4/19/2017.
 */

public class PasswordApi {

    public static RequestSpecBuilder builder;
    public static RequestSpecification requestSpec;
    public static String PassWordApiUrl= "/api/mbl/v1/auth/validate/password";
    public static Response res2;
    private JsonPath jp;


    public PasswordRequestBody setPasswordRequestBody(){
        PasswordRequestBody passwordRequestBody = new PasswordRequestBody();
        passwordRequestBody.setPassWord();
        return passwordRequestBody;
    }

    public  RequestSpecification getRequestSpecBuilder(){
        builder = new RequestSpecBuilder();
        builder.addHeader("X-Api-Key", "u134jppfdg4s3afhl0ajoduhd0");
        //builder.setAuthentication("Authorization",  )
        builder.setContentType("application/json");
        builder.setBody(setPasswordRequestBody());
        builder.setBaseUri("https://mobile-qa2.pnc.com");
        return builder.build();
    }

    public  Response getResponse() {
        res2  =  given()
                .filter(RestAPICookieFilter.instance())
                .spec(getRequestSpecBuilder()).
                 when().post(PassWordApiUrl);
        return res2;

    }
    public JsonPath setJsonPath(){
        RestUtil restUtil = new RestUtil();
        jp = restUtil.getJsonPath(res2);
        return jp;
    }

    public  String getAuthenticationStatus ()throws JSONException,InterruptedException{

        String authStatus =setJsonPath().getString("authStatus");
        System.out.println(authStatus);

        System.out.println("Authenticate Status is  " + authStatus);
        return authStatus;
    }
}
