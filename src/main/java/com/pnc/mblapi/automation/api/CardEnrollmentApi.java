package com.pnc.mblapi.automation.api;


import com.pnc.mblapi.automation.requestBody.CardEnrollmentRequestBody;
import com.pnc.mblapi.automation.util.RestAPICookieFilter;
import com.pnc.mblapi.automation.util.RestUtil;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import static io.restassured.RestAssured.given;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by GWITHARANA on 4/21/2017.
 */
public class CardEnrollmentApi {

    public static RequestSpecBuilder builder;
    public static RequestSpecification requestSpec;
    public static String CardEnrollmentUrl = "/api/mbl/pncpay/devices/cc81c5f714e0e27f676c0fy1/tokens";
    public static Response res6;
    private JsonPath jp;
    private String cardId = "365edaa63e34b182579d67c9cbe7897426ad3d22d8fb61bcf29b70b4172c7a576d1ffd61004975d0437e1ce59a4a6e79,DCA,001";


    public List<CardEnrollmentRequestBody> setCardEnrollmentRequestBody() {
        List<CardEnrollmentRequestBody> requestBodyList = new ArrayList<>();

        CardEnrollmentRequestBody cardEnrollmentRequestBody = new CardEnrollmentRequestBody();
        cardEnrollmentRequestBody.setCardId();
        cardEnrollmentRequestBody.setLast4Digits();
        cardEnrollmentRequestBody.setCardType();
        cardEnrollmentRequestBody.setpaymentNetwork();
        cardEnrollmentRequestBody.setAccountName();
        cardEnrollmentRequestBody.setAccountClassification();

        requestBodyList.add(cardEnrollmentRequestBody);
        return requestBodyList;
    }

    public RequestSpecification getRequestSpecBuilder() {
        builder = new RequestSpecBuilder();
        builder.addHeader("X-Api-Key", "u134jppfdg4s3afhl0ajoduhd0");
        //builder.setAuthentication("Authorization",  )
        builder.setContentType("application/json");
        builder.setBody(setCardEnrollmentRequestBody());
        builder.setBaseUri("https://mobile-qa2.pnc.com");
        return builder.build();
    }

    public void RequestLogTest() {
        given()
                .filter(RestAPICookieFilter.instance())
                .spec(getRequestSpecBuilder())
                .when()
                .log()
                .all()
                .post(CardEnrollmentUrl);

    }

    public void ResponseLogTest() {
        given()
                .filter(RestAPICookieFilter.instance())
                .spec(getRequestSpecBuilder()).
                when().post(CardEnrollmentUrl)
                .then().log().ifError().assertThat().statusCode(200);
    }

    public Response getResponse() {
        res6 = given()
                .filter(RestAPICookieFilter.instance())
                .spec(getRequestSpecBuilder()).
                        when().post(CardEnrollmentUrl);

        return res6;

    }

    public JsonPath setJsonPath() {
        RestUtil restUtil = new RestUtil();
        jp = restUtil.getJsonPath(res6);
        return jp;
    }

    public String getCardEnrollmentStatus() {
        String status = setJsonPath().get("data.cardId.status");
        return status;
    }


    public static JSONObject getJsonResponseBody() throws JSONException, InterruptedException {
        JSONObject JSONResponseBody = new JSONObject(res6.body().asString());
        return JSONResponseBody;
    }

    public String getAuthenticationStatus() throws JSONException, InterruptedException {
        String authenticationStatus = getJsonResponseBody().getString("message");
        System.out.println("Authenticate Status is  " + authenticationStatus);
        return authenticationStatus;
    }

}
