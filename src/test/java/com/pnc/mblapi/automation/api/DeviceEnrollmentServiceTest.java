package com.pnc.mblapi.automation.api;

import com.jayway.restassured.response.Response;
import groovy.util.logging.Log;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Created by GWITHARANA on 4/24/2017.
 */
public class DeviceEnrollmentServiceTest {

    private static Logger log = Logger.getLogger(Log.class);
    private static DeviceEnrollmentApi deviceEnrollmentApi;
    private static Authentication authentication;


    @BeforeClass
    public void beforeTest()throws JSONException, InterruptedException{
        log.info("Device enrollment API test is started");
        authentication = new Authentication();
        authentication.loginService();
        deviceEnrollmentApi = new DeviceEnrollmentApi();

    }

    @Test(priority = 1,groups = {"SmokeTest"})
    public void verifyDeviceEnrollmentResponseStatus() throws JSONException, InterruptedException {
        //Verify Device Enrollment Response status code
        log.info("DeviceEnrollment Api - Started & verify the response code");
        Response res = deviceEnrollmentApi.getResponse();
        log.info("DeviceEnrollment Api - Asserting the response code");
        Assert.assertEquals(res.getStatusCode(), 200, "Device Enrollment Response Status code Check Failed!");
    }

    @Test(priority = 2,dependsOnMethods = {"verifyDeviceEnrollmentResponseStatus"})
    public void verifyStatus()throws JSONException,InterruptedException{
        //Verify device enrollment status
        String status = deviceEnrollmentApi.getStatus();
        log.info("DeviceEnrollment Api - Asserting the response status");
        Assert.assertEquals(status, "SUCCESS", "Device Enrollment JsonResponse Status  Check Failed!");
    }

    @Test(priority = 3,dependsOnMethods = {"verifyStatus"})
    public void verifyDeviceIdfromResponse(){
        DeviceEnrollmentApi deviceEnrollmentApi = new DeviceEnrollmentApi();
        //Get Device ID "Actual"
        String actual = deviceEnrollmentApi.getDeviceIdActual();
        //Get Device ID "expected"
        String expected = deviceEnrollmentApi.getDeviceIdExpected();
        //Asserting the DeviceId
        log.info("DeviceEnrollment Api - Asserting the deviceID with request");
        Assert.assertEquals(actual, expected, "DeviceID is not matched with request!");
   }

   @AfterClass
    public void afterTest(){
       System.out.println("Device enrollment API test is closed");
   }


}
