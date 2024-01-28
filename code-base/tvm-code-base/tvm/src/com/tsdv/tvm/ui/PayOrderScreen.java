package com.tsdv.tvm.ui;

import com.card.association.CardAssociation;
import com.tsdv.tvm.db.orm.BankCardInfo;

public class PayOrderScreen extends BuyFareCertificateScreen {

    private BankCardInfo bankCardInfo = new BankCardInfo();

    PayOrderScreen(MainScreen mainScreen) {
        super(mainScreen);
    }

    @Override
    protected boolean acceptInputs() {
        return false;
    }

    public void display() {

        System.out.println("---------------------");
        System.out.println("PAYMENT SCREEN");

        System.out.println("Input your bank card number: ");
        bankCardInfo.setCardNumber(console.nextLine());
        System.out.print("Input user name of bank card: ");
        bankCardInfo.setUserName(console.nextLine());
    }

    public void displayResult(boolean confirmCardInfo) {
        System.out.println("Your payment was successful");
    }
}
