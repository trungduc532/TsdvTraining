package com.tsdv.tvm.db.orm;

import com.tsdv.tvm.db.dbms.DBManager;
import com.tsdv.tvm.db.dbms.InMemoryDBManager;

public class TicketDBManager {

    private final DBManager dbManager;

    public static final String TABLE_NAME = "Ticket";

    public TicketDBManager() {
        this.dbManager = InMemoryDBManager.instance();
    }
}
