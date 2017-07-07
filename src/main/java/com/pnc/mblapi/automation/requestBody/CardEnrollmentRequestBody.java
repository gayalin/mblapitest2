package com.pnc.mblapi.automation.requestBody;

/**
 * Created by GWITHARANA on 4/21/2017.
 */
public class CardEnrollmentRequestBody {
    public String cardId;
    public String last4Digits;
    public String cardType;
    public String paymentNetwork;
    public String accountName;
    public String accountClassification;

    public void setCardId() {
        cardId = "365edaa63e34b182579d67c9cbe78974dd1ceb12cbc434fb4f9996b438412fe66d1ffd61004975d0437e1ce59a4a6e79,DCA,001";

    }
    public void setLast4Digits() {
        last4Digits = "6801";

    }
    public void setCardType() {

        cardType = "DEBIT_CARD";
    }
    public void setpaymentNetwork() {
        paymentNetwork = "VISA";

    }
    public void setAccountName() {
        accountName = "Visa™ Debit Card";

    }
    public void setAccountClassification() {
        accountClassification = "CONSUMER";
    }

}
