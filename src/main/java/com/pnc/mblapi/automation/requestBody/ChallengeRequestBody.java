package com.pnc.mblapi.automation.requestBody;

/**
 * Created by GWITHARANA on 4/21/2017.
 */
public class ChallengeRequestBody {
    public String challengeAnswer;
    public String devicePrint;

    public void setChallengeAnswer() {
        challengeAnswer = "wall";

    }
    public void setDevicePrint() {
        devicePrint = "Postman";

    }
    public String getChallengeAnswer() {
        return challengeAnswer;

    }
}
