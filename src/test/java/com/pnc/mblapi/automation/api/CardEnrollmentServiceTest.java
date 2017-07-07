package com.pnc.mblapi.automation.api;

import com.jayway.restassured.response.Response;
import com.pnc.mblapi.automation.api.CardEnrollmentApi;
import groovy.util.logging.Log;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by GWITHARANA on 4/21/2017.
 */
public class CardEnrollmentServiceTest {
    private Authentication authentication;
    private DeviceEnrollmentApi deviceEnrollmentApi;
    private CardEnrollmentApi cardEnrollmentApi;
    private static Logger log = Logger.getLogger(Log.class);

    @BeforeTest
    public void beforeTest()throws JSONException, InterruptedException{
        log.info("Card Enrollment Service Test is started");
        authentication = new Authentication();
        authentication.loginService();
        deviceEnrollmentApi = new DeviceEnrollmentApi();
        deviceEnrollmentApi.getResponse();
        cardEnrollmentApi = new CardEnrollmentApi();
    }

    @Test(priority = 1)
    public void verifyCardEnrollmentResponseStatusTest()throws JSONException,InterruptedException {
        //Verify Card Enrollment Response status code
        cardEnrollmentApi.RequestLogTest();
        Response res = cardEnrollmentApi.getResponse();
        Assert.assertEquals(res.getStatusCode(), 200, "Card Enrollment Response Status code Check Failed!");
    }
    @Test (priority = 2,dependsOnMethods = {"verifyCardEnrollmentResponseStatusTest"})
    public void verifyCardEnrollmentStatus(){
        String status = cardEnrollmentApi.getCardEnrollmentStatus();
        Assert.assertEquals (status, "SUCCESS", "Card Enrollment is Failed!");
    }




}
