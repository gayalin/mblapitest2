package com.pnc.mblapi.automation.api;

import static io.restassured.RestAssured.given;
import com.pnc.mblapi.automation.util.RestAPICookieFilter;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by GWITHARANA on 4/24/2017.
 */
public class CardServiceApi {
    public static RequestSpecBuilder builder;
    public static RequestSpecification requestSpec;
    public static String CardServiceUrl= "/api/mbl/pncpay/cards?device-id=cc81c5f714e0e27f676c0fy1";
    public static Response res5;

    public  RequestSpecification getRequestSpecBuilder(){
        builder = new RequestSpecBuilder();
        builder.addHeader("X-Api-Key", "u134jppfdg4s3afhl0ajoduhd0");
        //builder.setAuthentication("Authorization",  )
        //builder.setContentType("application/json");
        builder.setBaseUri("https://mobile-qa2.pnc.com");
        return builder.build();
    }

    public void RequestLogTest(){
        given()
                .filter(RestAPICookieFilter.instance())
                .spec(getRequestSpecBuilder())
                .when()
                .log()
                .all()
                .get(CardServiceUrl);

    }
    public void ResponseLogTest(){
        given()
                .filter(RestAPICookieFilter.instance())
                .spec(getRequestSpecBuilder())
                .when().get(CardServiceUrl)
                .then().log().ifError().assertThat().statusCode(200);
    }

    public  Response getResponse() {
        res5  =  given()
                .filter(RestAPICookieFilter.instance())
                .spec(getRequestSpecBuilder())
                .when().get(CardServiceUrl);

        return res5;

    }

    //Get Json response as a string and transform it to JSONObject
    public JSONObject getJsonResponse()throws JSONException,InterruptedException{
        JSONObject JSONResponse = new JSONObject(res5.body().asString());
        return JSONResponse;
    }

    public String getStatus() throws JSONException, InterruptedException {
        String status = getJsonResponse().getString("status");
        return status;

    }
}
