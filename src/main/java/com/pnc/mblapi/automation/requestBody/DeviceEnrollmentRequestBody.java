package com.pnc.mblapi.automation.requestBody;

/**
 * Created by GWITHARANA on 4/24/2017.
 */
public class DeviceEnrollmentRequestBody {
    public String deviceBrand;
    public String deviceIDType;
    public String deviceManufacturer;
    public String deviceModel;
    public String deviceName;
    public String deviceType;
    public String hostDeviceID;
    public String osBuildID;
    public String osType;
    public String osVersion;
    public String phoneNumber;

    public void setDeviceBrand() {
        deviceBrand = "oneplus";

    }
    public void setDeviceIDType() {
        deviceIDType = "Derived";

    }
    public void setDeviceManufacturer() {

        deviceManufacturer = "OnePlus";
    }
    public void setDeviceModel() {
        deviceModel = "A0001";

    }
    public void setDeviceName() {
        deviceName = "T25lUGx1cyBPbmU.";

    }
    public void setDeviceType() {
        deviceType = "PHONE_TABLET";
    }
    public void setHostDeviceID() {
     hostDeviceID = "cc81c5f714e0e27f676c0fy1";


    }
    public void setOsBuildID() {
        osBuildID = "MHC19Q";
    }
    public void setOsType() {
        osType = "ANDROID";
    }
    public void setOsVersion() {
        osVersion = "6.0.1";

    }
    public void setPhoneNumber() {
        phoneNumber = "14125197765";
    }

    public String getHostDeviceID() {
       setHostDeviceID();
       return hostDeviceID;
    }
}
