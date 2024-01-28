package com.tsdv.tvm.db.orm;

import com.tsdv.tvm.db.dbms.DBManager;
import com.tsdv.tvm.db.dbms.InMemoryDBManager;

import java.util.List;
import java.util.Properties;

public class BankCardManager {

    private final DBManager dbManager;
    public static final String TABLE_NAME = "bank_card_info";

    private static BankCardManager bankCardManager;

    private BankCardManager() {
        dbManager = InMemoryDBManager.instance();
    }

    public static BankCardManager instance() {
        if (bankCardManager == null) {
            bankCardManager = new BankCardManager();

            bankCardManager.clean();
            bankCardManager.init();
        }
        return bankCardManager;
    }

    public void init() {
        addBankCardInfo(new BankCardInfo("ducvv1", "0971000028111", 159));
        addBankCardInfo(new BankCardInfo("ducvv2", "0971000028112", 160));
        addBankCardInfo(new BankCardInfo("ducvv3", "0971000028113", 162));
        addBankCardInfo(new BankCardInfo("ducvv4", "0971000028114", 163));
    }

    private void addBankCardInfo(BankCardInfo bankCardInfo) {
        Properties bankCardInfoPro = new Properties();

        bankCardInfoPro.put(
                BankCardInfo.VARIABLE_USER_NAME,
                bankCardInfo.getUserName());

        bankCardInfoPro.put(
                BankCardInfo.VARIABLE_CARD_NUMBER,
                bankCardInfo.getCardNumber());

        bankCardInfoPro.put(
                BankCardInfo.VARIABLE_CVV,
                bankCardInfo.getCvvNumber());

        dbManager.insert(TABLE_NAME, bankCardInfoPro);
    }

    public void clean() {
        List<Properties> rows = dbManager.get(TABLE_NAME);
        for (Properties row : rows) {
            dbManager.delete(TABLE_NAME, row);
        }
    }

    public BankCardInfo getBankCardInfoByUserNameAndNumber(String user, String number) {
        BankCardInfo bankCardInfo = null;

        List<Properties> rows = dbManager.get(TABLE_NAME);
        for (Properties row : rows) {
            if(user == row.getProperty(BankCardInfo.VARIABLE_USER_NAME)
                    && number == row.getProperty(BankCardInfo.VARIABLE_CARD_NUMBER)){
                bankCardInfo = new BankCardInfo(user, number,
                        Integer.parseInt(row.getProperty(BankCardInfo.VARIABLE_CVV)));
            }
        }
        return bankCardInfo;
    }
}
