package com.pnc.mblapi.automation.api;

import com.pnc.mblapi.automation.requestBody.UserIdRequestBody;
import com.pnc.mblapi.automation.util.Constant;
import com.pnc.mblapi.automation.util.RestAPICookieFilter;
import com.pnc.mblapi.automation.util.RestUtil;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import static io.restassured.RestAssured.given;


/**
 * Created by GWITHARANA on 4/19/2017.
 */
public class UserIdApi {
    public static RequestSpecBuilder builder;
    public static RequestSpecification requestSpec;
    public static String UserIdUrl = "/api/mbl/v1/auth/validate/userid?saveUserId=false";
    public static Response res1;
    public static Response res2;



    public static UserIdRequestBody setUserIdRequestBody() {
        UserIdRequestBody userIdRequestBody = new UserIdRequestBody();
        userIdRequestBody.setUserId();
        userIdRequestBody.setDevicePrint();
        userIdRequestBody.setAppVersion();
        return userIdRequestBody;
    }

    public static RequestSpecification getRequestSpecBuilder() {
        builder = new RequestSpecBuilder();
        builder.addHeader("X-Api-Key", "mm6ppel5a49qco2vl20pnh9rn3");
        builder.addHeader("X-MBL-ENV","qa2");
        builder.setContentType("application/json");
        builder.setBody(setUserIdRequestBody());
        builder.setBaseUri(Constant.BaseURLDataPower);
        return builder.build();
    }

    public Response getResponse() {
        res1 = given()
                .filter(RestAPICookieFilter.instance())
                .spec(getRequestSpecBuilder())
                .when().post(UserIdUrl);
        return res1;

    }

    public Response getResponseDataPower(){
        res2 = RestAssured.given()
                .trustStore("C:\\RestAssured\\Certificates\\truststore.jks","gayal123")
                .filter(RestAPICookieFilter.instance())
                .spec(getRequestSpecBuilder()).when().post(UserIdUrl);
        return res2;
    }

    public static JSONObject getJsonResponseBody() throws JSONException {
        JSONObject JSONResponseBody = new JSONObject(res1.body().asString());
        return JSONResponseBody;
    }


    public RequestSpecification getRequestSpecification() {
        return given().spec(getRequestSpecBuilder());
    }

    public static String getAuthenticationStatus() throws JSONException, InterruptedException {
        String authenticationStatus = getJsonResponseBody().getString("authStatus");
        System.out.println("Authenticate Status is  " + authenticationStatus);
        return authenticationStatus;

    }


}



