package com.pnc.mblapi.automation.api;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.pnc.mblapi.automation.requestBody.DeviceEnrollmentRequestBody;
import com.pnc.mblapi.automation.util.RestAPICookieFilter;
import com.pnc.mblapi.automation.util.RestUtil;
import groovy.util.logging.Log;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by GWITHARANA on 4/24/2017.
 */
public class DeviceEnrollmentApi {

    private static Logger log = Logger.getLogger(Log.class);
    public static RequestSpecBuilder builder;
    public static RequestSpecification requestSpec;
    public static String DeviceEnrollmentApiUrl= "/api/mbl/pncpay/devices";
    public static Response res4;
    private JsonPath jp;


    public DeviceEnrollmentRequestBody setDeviceEnrollmentRequestBody(){
       DeviceEnrollmentRequestBody deviceEnrollmentRequestBody = new DeviceEnrollmentRequestBody();
       deviceEnrollmentRequestBody.setDeviceBrand();
       deviceEnrollmentRequestBody.setDeviceIDType();
       deviceEnrollmentRequestBody.setDeviceManufacturer();
       deviceEnrollmentRequestBody.setDeviceModel();
       deviceEnrollmentRequestBody.setDeviceName();
       deviceEnrollmentRequestBody.setDeviceType();
       deviceEnrollmentRequestBody.setHostDeviceID();
       deviceEnrollmentRequestBody.setOsBuildID();
       deviceEnrollmentRequestBody.setOsType();
       deviceEnrollmentRequestBody.setOsVersion();
       deviceEnrollmentRequestBody.setPhoneNumber();
       return deviceEnrollmentRequestBody;
    }

    public  RequestSpecification getRequestSpecBuilder(){
        builder = new RequestSpecBuilder();
        builder.addHeader("X-Api-Key", "u134jppfdg4s3afhl0ajoduhd0");
        //builder.setAuthentication("Authorization",  )
        builder.setContentType("application/json");
        builder.setBody(setDeviceEnrollmentRequestBody());
        builder.setBaseUri("https://mobile-qa2.pnc.com");
        return builder.build();
    }

    public  Response getResponse() {
        res4  =  given()
                .filter(RestAPICookieFilter.instance())
                .spec(getRequestSpecBuilder()).
                        when().post(DeviceEnrollmentApiUrl);
        return res4;

    }

    public JsonPath setJsonPath(){
        RestUtil restUtil = new RestUtil();
        jp = restUtil.getJsonPath(res4);
        return jp;
    }

    public  String getStatus ()throws JSONException,InterruptedException{
        String status= getJsonObjectFromResponse().getString("status");
        log.info("Status is   " + status);
        return status;
    }

    public String getErrorMessage()throws JSONException, InterruptedException{
        //extract the JSONArray in the Error field
        JSONArray errorsArray =getJsonObjectFromResponse().getJSONArray("errors");
        //converting JsonArray to JsonObject
        JSONObject jsonResponseErrors = new JSONObject(errorsArray.getString(0));
        String message = jsonResponseErrors.getString("message");
        return message;
    }

    public String getDeviceIdActual(){
      String deviceIdActual =  setJsonPath().get("data.vtsEncryptionData.deviceId");
      String token = setJsonPath().get("data.androidpay.visatoken[1].device.devicetype")
        System.out.println(deviceIdActual);
        return deviceIdActual;
    }
//Get Json response as a string and transform it to JSONObject
    public static JSONObject getJsonObjectFromResponse() throws JSONException, InterruptedException {
        JSONObject JSONResponse = new JSONObject(res4.body().asString());
        return JSONResponse;
    }


    public String getDeviceIdExpected(){
        DeviceEnrollmentRequestBody deviceEnrollmentRequestBody = new DeviceEnrollmentRequestBody();
        String deviceIdExpected = deviceEnrollmentRequestBody.getHostDeviceID();
        return deviceIdExpected;
    }
}
