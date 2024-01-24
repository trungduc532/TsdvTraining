package com.tsdv.tvm.test;

import java.util.List;
import java.util.Properties;

import org.junit.Assert;

import com.tsdv.tvm.db.dbms.DBManager;
import com.tsdv.tvm.db.dbms.InMemoryDBManager;

import junit.framework.TestCase;

public class InMemoryDBManagerTest extends TestCase {

    private DBManager dbManager;
    private String ticketTableName;

    public void setUp() throws Exception {
        super.setUp();
        dbManager = InMemoryDBManager.instance();
        ticketTableName = "Ticket";
    }

    public void testGet() {
        assertNotNull(dbManager.get(ticketTableName));
    }

    public void testGetWithEmpty() {
        assertEquals(0, dbManager.get(ticketTableName).size());
    }

    public void testGetWithNotEmptyTable() {
        Properties row = new Properties();
        row.setProperty("id", dbManager.generateId());
        row.setProperty("gtin", "12345");

        dbManager.insert(ticketTableName, row);

        List<Properties> rows = dbManager.get(ticketTableName);
        Assert.assertNotEquals(0, rows.size());
        Assert.assertNotSame(rows.get(0), row);

        Properties condition = new Properties();
        condition.setProperty("gtin", "12345");
        dbManager.delete(ticketTableName, condition);
    }

    public void testGetWithManyRowTable() {
        insertMultipleRow();

        List<Properties> rows = dbManager.get(ticketTableName);
        Assert.assertEquals(10, rows.size());

        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(String.valueOf(i), rows.get(i).getProperty("gtin"));
        }
        deleteMultipleRow();
    }

    private void insertMultipleRow() {
        for (int i = 0; i < 10; i++) {
            Properties row = new Properties();
            row.setProperty("id", dbManager.generateId());
            row.setProperty("gtin", String.valueOf(i));
            row.setProperty("price", String.valueOf(i * 10.2));
            dbManager.insert(ticketTableName, row);
        }
    }

    private void deleteMultipleRow() {
        for (int i = 0; i < 10; i++) {
            Properties condition = new Properties();
            condition.setProperty("gtin", String.valueOf(i));
            dbManager.delete(ticketTableName, condition);
        }
    }

    public void testGetWithEmptyCondition() {
        insertMultipleRow();
        List<Properties> properties = dbManager.get(ticketTableName, new Properties());
        Assert.assertEquals(0, properties.size());
        deleteMultipleRow();
    }

    public void testGetWithNotEmptyCondition() {
        insertMultipleRow();
        Properties condition = new Properties();
        condition.setProperty("gtin", "1");

        List<Properties> properties = dbManager.get(ticketTableName, condition);
        Assert.assertEquals(1, properties.size());
        deleteMultipleRow();
    }

    public void testGetWithNotEmptyConditionAndNotFoundKey() {
        insertMultipleRow();
        Properties condition = new Properties();
        condition.setProperty("gtin-not-found", "1");

        List<Properties> properties = dbManager.get(ticketTableName, condition);
        Assert.assertEquals(0, properties.size());
        deleteMultipleRow();
    }

    public void testInsert() {
    }

    public void testDeleteShouldNotDeleteWhenEmptyCondition() {
        insertMultipleRow();
        dbManager.delete(ticketTableName, new Properties());
        Assert.assertEquals(10, dbManager.get(ticketTableName).size());
        deleteMultipleRow();
    }

    public void testDeleteShouldDeleteConditionMatched() {
        insertMultipleRow();
        Properties condition = new Properties();
        condition.setProperty("gtin", "5");

        dbManager.delete(ticketTableName, condition);
        List<Properties> rows = dbManager.get(ticketTableName);
        Assert.assertEquals(9, rows.size());
        Assert.assertNotEquals(rows.get(5).getProperty("gtin"), "5");
        deleteMultipleRow();
    }

    public void testUpdate() {
        insertMultipleRow();
        Properties condition = new Properties();
        condition.setProperty("gtin", "5");

        // Update price of ticket have gtin is 5 to be 20.1
        Properties updatedProperties = new Properties();
        updatedProperties.setProperty("price", "20.1");

        dbManager.update(ticketTableName, updatedProperties, condition);

        List<Properties> rows = dbManager.get(ticketTableName, condition);
        Assert.assertEquals(1, rows.size());
        Assert.assertEquals(rows.get(0).getProperty("price"), "20.1");
        deleteMultipleRow();
    }

    public void testUpdateWithEmptyProperties() {
        insertMultipleRow();
        Properties condition = new Properties();
        condition.setProperty("gtin", "5");
        Properties updatedProperties = new Properties();

        List<Properties> rowsBeforeUpdated = dbManager.get(ticketTableName, condition);
        Assert.assertEquals(1, rowsBeforeUpdated.size());
        String previousPrice = rowsBeforeUpdated.get(0).getProperty("price");

        dbManager.update(ticketTableName, updatedProperties, condition);

        List<Properties> rows = dbManager.get(ticketTableName, condition);
        Assert.assertEquals(1, rows.size());
        Assert.assertEquals(rows.get(0).getProperty("price"), previousPrice);
        deleteMultipleRow();
    }
}