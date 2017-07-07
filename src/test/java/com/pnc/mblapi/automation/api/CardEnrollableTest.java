package com.pnc.mblapi.automation.api;

import groovy.util.logging.Log;
import org.apache.log4j.Logger;
import org.json.JSONException;
import com.jayway.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by GWITHARANA on 5/1/2017.
 */
public class CardEnrollableTest {

    private CardEnrollableApi cardEnrollableApi;
    private Authentication authentication;
    private static Logger log = Logger.getLogger(Log.class);

    @BeforeTest
    public void beforeTest()throws JSONException, InterruptedException{
        log.info("Card Enrollable Service Test is started");
        authentication = new Authentication();
        authentication.loginService();
        cardEnrollableApi = new CardEnrollableApi();
    }

    @Test(priority = 1)
    public void verifyCardEnrollableResponseStatusCodeTest(){

        Response res = cardEnrollableApi.getResponse();
        cardEnrollableApi.ResponseLogTest();
        Assert.assertEquals(res.getStatusCode(), 200, "Card Enrollable Response Status code Check Failed!");
    }

    @Test(priority = 2,dependsOnMethods = {"verifyCardEnrollableResponseStatusCodeTest"})
    public void verifyStatusOfResponse()throws JSONException,InterruptedException{
        cardEnrollableApi = new CardEnrollableApi();
        String status = cardEnrollableApi.getOverallStatus();
        Assert.assertEquals(status, "SUCCESS", "Card Enrollable overall Response Status Check Failed!");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("Card Enrollable Service Test is over");
    }
}
