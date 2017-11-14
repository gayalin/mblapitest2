package com.pnc.mblapi.automation.api;

import static io.restassured.RestAssured.given;

import com.pnc.mblapi.automation.util.RestAPICookieFilter;
import com.pnc.mblapi.automation.util.RestUtil;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by GWITHARANA on 5/1/2017.
 */
public class CardEnrollableApi {

    public static RequestSpecBuilder builder;
    public static RequestSpecification requestSpec;
    public static String CardEnrollableUrl = "/api/mbl/pncpay/cards/enrollable";
    public static Response res7;


    public RequestSpecification getRequestSpecBuilder() {
        builder = new RequestSpecBuilder();
        builder.addHeader("X-Api-Key", "u134jppfdg4s3afhl0ajoduhd0");
        //builder.setAuthentication("Authorization",  )
        builder.setBaseUri("https://mobile-qa2.pnc.com");
        return builder.build();
    }

    public void RequestLogTest() {
        RestUtil.https()
                .filter(RestAPICookieFilter.instance())
                .spec(getRequestSpecBuilder())
                .when()
                .log()
                .all()
                .get(CardEnrollableUrl);

    }

    public void ResponseLogTest() {
        given()
                .filter(RestAPICookieFilter.instance())
                .spec(getRequestSpecBuilder()).
                when().get(CardEnrollableUrl)
                .then().log().ifError().assertThat().statusCode(200);
    }

    public Response getResponse() {
        res7 = given()
                .filter(RestAPICookieFilter.instance())
                .spec(getRequestSpecBuilder()).
                        when().get(CardEnrollableUrl);

        return res7;

    }

    public static JSONObject getJsonResponseBody() throws JSONException, InterruptedException {
        JSONObject JSONResponseBody = new JSONObject(res7.body().asString());
        return JSONResponseBody;
    }

    public String getOverallStatus() throws JSONException, InterruptedException {
        String status = getJsonResponseBody().getString("status");
        System.out.println("Authenticate Status is  " + status);
        return status;
    }
}
