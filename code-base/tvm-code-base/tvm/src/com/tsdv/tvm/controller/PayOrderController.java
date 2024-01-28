package com.tsdv.tvm.controller;

import com.card.association.CardAssociation;
import com.tsdv.tvm.db.orm.BankCardInfo;
import com.tsdv.tvm.db.orm.BankCardManager;

import java.util.Scanner;

public class PayOrderController {

    private Scanner scanner = new Scanner(System.in);

    private CardAssociation cardAssociation;

    /**
     * input cvv number
     * @return cvv number
     */
    private boolean inputCvvNumber(BankCardInfo bankCardInfo) {
        int numberOfTries = 0;

        System.out.print("Input number cvv on your credit card: ");
        while (bankCardInfo.getCvvNumber() == null) {
            try {
                bankCardInfo.setCvvNumber(Integer.parseInt(scanner.nextLine()));
            } catch (Exception e) {
                System.out.print("Your cvv number is incorrect. Please again: ");
                numberOfTries += 1;
                bankCardInfo.setCvvNumber(null);
            }
            if(numberOfTries > 3) {
                System.out.println("You have entered incorrectly more than 3 times");
                return false;
            }
        }
        return true;
    }

    public boolean confirmTransaction(BankCardInfo bankCardInfo) {
        inputCvvNumber(bankCardInfo);
        BankCardManager bankCardManager = BankCardManager.instance();
        BankCardInfo bankCardInfoDB =
        bankCardManager.getBankCardInfoByUserNameAndNumber(bankCardInfo.getUserName(), bankCardInfo.getCardNumber());

        if (bankCardInfoDB.getCvvNumber() == bankCardInfo.getCvvNumber()) {
            return true;
        }
        return false;
    }

}
