package com.pnc.mblapi.automation.requestBody;

/**
 * Created by GWITHARANA on 4/21/2017.
 */
public class UserIdRequestBody {
   public String userId;
   public String devicePrint;
   public String appVersion;


public void setUserId() {
    userId = "Paytest04";
}
public void setDevicePrint() {
    devicePrint = "Postman";

}
public void setAppVersion() {
    appVersion = "1";
}
public String getUserId() {
    return userId;
}
public String getDevicePrint() {
    return devicePrint;
}
public String getAppVersion() {
    return devicePrint;
}
}

