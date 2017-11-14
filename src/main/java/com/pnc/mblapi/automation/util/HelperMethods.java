package com.pnc.mblapi.automation.util;


import io.restassured.response.Response;
import org.testng.Assert;


public class HelperMethods {
	 /*
    Verify the http response status returned. Check Status Code is 200?
    We can use Rest Assured library's response's getStatusCode method
    */
    public static void checkStatusIs200 (Response res) {
    	Assert.assertEquals (res.getStatusCode(), 200, "Status Check Failed!");
    }
}
