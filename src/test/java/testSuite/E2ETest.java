package testSuite;

import com.pnc.mblapi.automation.api.*;
import com.pnc.mblapi.automation.api.Authentication;
import org.json.JSONException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by GWITHARANA on 5/1/2017.
 */
public class E2ETest {

    @BeforeTest
    public void beforeTest(){
        System.out.println("Test is started");
    }


    @Test(priority = 1)
    public void TC01_VerifyAuthentication()throws JSONException, InterruptedException{
        Authentication authenticationTest = new Authentication();
        authenticationTest.loginService();
    }

    @Test(priority = 2,dependsOnMethods = {"TC01_VerifyAuthentication"})
    public void TC02_VerifyDeviceEnrollment()throws JSONException, InterruptedException{
        DeviceEnrollmentServiceTest deviceEnrollmentServiceTest = new DeviceEnrollmentServiceTest();
        deviceEnrollmentServiceTest.verifyDeviceEnrollmentResponseStatus();
        deviceEnrollmentServiceTest.verifyDeviceIdfromResponse();
    }

    @Test(priority = 3,dependsOnMethods = {"TC02_VerifyDeviceEnrollment"})
    public void TC03_VerifyCardService()throws JSONException, InterruptedException{
        CardServiceTest cardServiceTest = new CardServiceTest();
        cardServiceTest.verifyResponseStatus();
    }

    @Test(priority = 4,dependsOnMethods = {"TC03_VerifyCardService"})
    public void TC04_VerifyCardEnrollableService()throws JSONException, InterruptedException{
        CardEnrollableTest cardEnrollableTest = new CardEnrollableTest();
        cardEnrollableTest.verifyCardEnrollableResponseStatusCodeTest();
        cardEnrollableTest.verifyStatusOfResponse();
    }

    @Test(priority = 5,dependsOnMethods = {"TC04_VerifyCardEnrollableService"})
    public void TC04_VerifyCardEnrollmentService()throws JSONException, InterruptedException{
        CardEnrollmentServiceTest cardEnrollmentServiceTest = new CardEnrollmentServiceTest();
        cardEnrollmentServiceTest.verifyCardEnrollmentResponseStatusTest();
        cardEnrollmentServiceTest.verifyCardEnrollmentStatus();
        }


    @AfterTest
    public void afterTest() {
        System.out.println("Test is over");
    }
}
