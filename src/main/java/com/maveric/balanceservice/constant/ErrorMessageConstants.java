package com.maveric.balanceservice.constant;

public class ErrorMessageConstants {
    private ErrorMessageConstants(){

    }
    public static final String MISSING_INPUT = "Input is missing or not valid";
    public static final String CURRENCY_ERROR = "Currency should one of the following value [DOLLAR, INR, EURO]";
    public static final String ACCOUNT_ID_MISMATCH = "account id in url and request body should be same";
}
