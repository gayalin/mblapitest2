package com.pnc.mblapi.automation.api;

import com.pnc.mblapi.automation.api.CardServiceApi;
import groovy.util.logging.Log;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by GWITHARANA on 4/24/2017.
 */
public class CardServiceTest{

    private static Logger log = Logger.getLogger(Log.class);
    private static Authentication authentication;
    private static DeviceEnrollmentApi deviceEnrollmentApi;
    private CardServiceApi cardServiceApi;
    private static Response res;

    @BeforeTest
    public void beforeTest()throws JSONException, InterruptedException{
        log.info("Card service Api test is started");
        authentication = new Authentication();
        authentication.loginService();
        deviceEnrollmentApi=new DeviceEnrollmentApi();
        deviceEnrollmentApi.getResponse();
        cardServiceApi = new CardServiceApi();
    }

    @Test(priority = 1)
    public void verifyCardServiceResponseStatusCode() throws JSONException, InterruptedException {
        //Verify Device Enrollment Response status code
        res = cardServiceApi.getResponse();
        cardServiceApi.ResponseLogTest();
        Assert.assertEquals(res.getStatusCode(), 200, "Card Service Response Status code Check Failed!");
    }

    @Test(priority = 2,dependsOnMethods = {"verifyCardServiceResponseStatusCode"})
    public void verifyResponseStatus()throws JSONException, InterruptedException{
        String status = cardServiceApi.getStatus();
        Assert.assertEquals(status, "SUCCESS", "Card Service Response Status Check Failed!");
    }

}
