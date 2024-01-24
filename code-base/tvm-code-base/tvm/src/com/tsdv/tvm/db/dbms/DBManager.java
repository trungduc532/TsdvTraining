package com.tsdv.tvm.db.dbms;

import java.util.List;
import java.util.Properties;

/**
 * Interface to work with key value database
 */
public interface DBManager {
    /**
     * Get all rows in table
     * <p>
     * Example:
     * <pre>{@code
     *     DBManager dbManager = InMemoryDBManager.instance();
     *     //insert multiple rows
     *     for (int i = 0; i < 10; i++) {
     *         Properties row = new Properties();
     *         row.setProperty("id", dbManager.generateId());
     *         row.setProperty("gtin", String.valueOf(i));
     *         row.setProperty("price", String.valueOf(i * 10.2));
     *         dbManager.insert(ticketTableName, row);
     *     }
     *
     *     assertNotNull(dbManager.get(ticketTableName));
     *     List<Properties> rows = dbManager.get(ticketTableName);
     *     Assert.assertEquals(10, rows.size());
     * }</pre><p>
     *
     * @param tableName predefined table name
     * @return
     *      rows list which is sorted by adding order
     *      empty list if don't found table name
     */
    List<Properties> get(String tableName);

    /**
     * Get rows by condition
     * <p>
     * Example with empty condition:
     * <pre>{@code
     *  List<Properties> properties = dbManager.get(
     *      ticketTableName,
     *      new Properties());
     *  Assert.assertEquals(0, properties.size());
     * }</pre><p>
     *
     * Example with not empty condition:
     * <pre>{@code
     *  Properties condition = new Properties();
     *  condition.setProperty("gtin", "1");
     *
     *  List<Properties> properties = dbManager.get(
     *      ticketTableName,
     *      condition);
     *  Assert.assertEquals(1, properties.size());
     * }</pre><p>
     *
     * @param tableName predefined name of table
     * @param condition properties for maching
     * @return
     *      empty list if condition is empty
     *      matched {@code & } cloned row if condition match some rows
     */
    List<Properties> get(String tableName, Properties condition);

    /**
     * Insert row to table
     * <p>
     *
     * Example with not empty condition:
     * <pre>{@code
     * insertMultipleRow();
     * Properties condition = new Properties();
     * condition.setProperty("gtin", "5");
     *
     * // Update price of ticket have gtin is 5 to be 20.1
     * Properties updatedProperties = new Properties();
     * updatedProperties.setProperty("price", "20.1");
     *
     * dbManager.update(
     *     ticketTableName,
     *     updatedProperties,
     *     condition);
     *
     * List<Properties> rows = dbManager.get(
     *     ticketTableName,
     *     condition);
     * Assert.assertEquals(1, rows.size());
     * Assert.assertEquals(
     *     rows.get(0).getProperty("price"),
     *     "20.1"
     * );
     * }</pre><p>
     *
     * @param tableName predefined name of table
     * @param newRow row for inserting
     * @return number of row affected
     *      0: error happened
     *      1: insert success
     */
    int insert(String tableName, Properties newRow);

    /**
     * Delete row matched with condition
     * <p>
     *
     * Example with not empty condition:
     * <pre>{@code
     *  insertMultipleRow();
     *  Properties condition = new Properties();
     *  condition.setProperty("gtin", "5");
     *
     *  dbManager.delete(ticketTableName, condition);
     *  List<Properties> rows = dbManager.get(ticketTableName);
     *  Assert.assertEquals(9, rows.size());
     *  Assert.assertNotEquals(
     *      rows.get(5).getProperty("gtin"),
     *      "5");
     * }</pre><p>
     *
     * @param tableName predefined name of table
     * @param conditionRow row for inserting
     * @return number of row affected
     */
    int delete(String tableName, Properties conditionRow);

    /**
     * Update matched rows by using new properties
     * <p>
     *
     * Example:
     * <pre>{@code
     *  insertMultipleRow();
     *  Properties condition = new Properties();
     *  condition.setProperty("gtin", "5");
     *
     *  // Update price of ticket have gtin is 5 to be 20.1
     *  Properties updatedProperties = new Properties();
     *  updatedProperties.setProperty("price", "20.1");
     *
     *  dbManager.update(ticketTableName, updatedProperties, condition);
     *
     *  List<Properties> rows = dbManager.get(ticketTableName, condition);
     *  Assert.assertEquals(1, rows.size());
     *  Assert.assertEquals(rows.get(0).getProperty("price"), "20.1");
     * }</pre>
     *
     * @param tableName predefined name of table
     * @param newRow new row properties for update
     * @param conditionRow condition for matching row
     * @return number of row affected
     */
    int update(String tableName, Properties newRow, Properties conditionRow);

    /**
     * Generate global ID for this db
     * @return global id for instance
     */
    String generateId();
}
