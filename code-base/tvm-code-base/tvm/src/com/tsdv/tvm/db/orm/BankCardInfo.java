package com.tsdv.tvm.db.orm;

public class BankCardInfo {

    private String cardNumber;

    private String userName;

    private Integer cvvNumber;

    public String getCardNumber() {
        return cardNumber;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getCvvNumber() {
        return cvvNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCvvNumber(Integer cvvNumber) {
        this.cvvNumber = cvvNumber;
    }

    public BankCardInfo(String userName, String cardNumber, Integer cvvNumber) {
        this.userName = userName;
        this.cardNumber = cardNumber;
        this.cvvNumber = cvvNumber;
    }

    public BankCardInfo() {
    }

    public static final String VARIABLE_USER_NAME = "user_name";
    public static final String VARIABLE_CARD_NUMBER = "card_number";
    public static final String VARIABLE_CVV = "cvv";


}
