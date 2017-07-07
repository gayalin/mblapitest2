package com.pnc.mblapi.automation.api;
import com.pnc.mblapi.automation.api.*;
import com.jayway.restassured.response.Response;
import groovy.util.logging.Log;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by GWITHARANA on 4/20/2017.
 */
public class Authentication {

   private static Logger log = Logger.getLogger(Log.class);

    @BeforeTest
    public void beforeTest(){
        log.info("authentication check is started");
    }

    @Test(groups = {"SmokeTest"})
    public void loginService()throws InterruptedException {
        /*********validate user id API ********************************************************/
        log.info("Verify Response Status");
        UserIdApi userIdApi = new UserIdApi();
        Response res1 = userIdApi.getResponse();
        log.info("Asserting the response code");
        Assert.assertEquals(res1.getStatusCode(), 200, "UserID Response Status Check Failed!");
        log.info("Verify authentication status");
        String authStatus1 = userIdApi.getAuthenticationStatus();

        if (authStatus1.matches("CHALLENGE")) {
        /********validate user challenge******************************************************/
            log.info("Challenge Api -Started");
            ChallengeApi challengeApi = new ChallengeApi();
            log.info("Challenge Api -send the request");
            Response res3 = challengeApi.getResponse();
            log.info("Challenge Api -Asserting the response status code");
            Assert.assertEquals(res3.getStatusCode(), 200, "Challenge Response Status Check Failed!");
            //Verify authentication Status
            log.info("Challenge Api -Verify Authentication Status");
            String authStatus2 = challengeApi.getAuthenticationStatus();
            log.info("Challenge Api -Asserting the Authentication Status");
            Assert.assertEquals(authStatus2, "PASSWORD", "Challenge authentication Check Failed!");

        } else {
            log.info("Password Api -Started");
            Assert.assertEquals(authStatus1, "PASSWORD", "UserID authentication Check Failed!");
        }
        /********validate password API********************************************************/
            log.info("Password Api -Started");
            PasswordApi passwordApi = new PasswordApi();
            Response res2 = passwordApi.getResponse();
            log.info("Password Api -Asserting the response status code");
            Assert.assertEquals(res2.getStatusCode(), 200, "Password Response Status Check Failed!");
            //Verify authentication Status
            String authStatus3 = passwordApi.getAuthenticationStatus();
            log.info("Password Api -Asserting the Authentication Status");
            Assert.assertEquals(authStatus3, "AUTHENTICATED", "Password authentication Check Failed!");


    }

    @AfterTest
    public void afterTest(){
        log.info("authentication check is Over");
    }

}
