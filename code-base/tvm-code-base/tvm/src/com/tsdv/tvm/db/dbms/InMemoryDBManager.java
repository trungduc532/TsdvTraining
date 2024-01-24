package com.tsdv.tvm.db.dbms;

import java.util.*;

//a unique DBManager for an in-memory DB
public class InMemoryDBManager implements DBManager {
    private final Map<String, List<Properties>> tables = new HashMap<>();
    private int idCount = 0;
    private static InMemoryDBManager inMemoryDBManager;
    
    /**
     * Create one singleton instance of InMemoryDBManager
     * <p>
     * Examples:
     * <pre>{@code
     *  InMemoryDBManager dbManager = InMemoryDBManager.instance();
     * }</pre>
     *
     * @return instance of InMemoryDBManager
     */
    public static DBManager instance(){
    	if (inMemoryDBManager == null)
    		inMemoryDBManager = new InMemoryDBManager();
    	
    	return inMemoryDBManager;
    }
    
    private InMemoryDBManager() {
    }

    @Override
    public List<Properties> get(String tableName) {
        initTableIfNotExists(tableName);

        List<Properties> result = new ArrayList<>();
        List<Properties> rows = tables.get(tableName);
        for (Properties row : rows) {
            result.add(cloneProperties(row));
        }

        return result;
    }

    @Override
    public List<Properties> get(String tableName, Properties condition) {
        List<Properties> matchedRows = findMatchedRows(tableName, condition);
        List<Properties> result = new ArrayList<>();
        for (Properties row : matchedRows) {
            result.add(cloneProperties(row));
        }
        return result;
    }

    private boolean isMatched(Properties properties, Properties condition) {
        boolean isMatched = true;
        Enumeration<?> conditionKeyValue = condition.propertyNames();
        while (conditionKeyValue.hasMoreElements()) {
            // Checking properties contain condition key
            String conditionKey = (String) conditionKeyValue.nextElement();
            if (!properties.containsKey(conditionKey)) {
                isMatched = false;
                break;
            }

            // Checking value equal
            String conditionValue = condition.getProperty(conditionKey);
            String matchingValue = properties.getProperty(conditionKey);
            if (!conditionValue.equals(matchingValue)) {
                isMatched = false;
                break;
            }
        }
        return isMatched;
    }

    private Properties cloneProperties(Properties properties) {
        Properties newProps = new Properties();
        properties.forEach((key, value) -> newProps.setProperty((String) key, (String) value));
        return newProps;
    }

    @Override
    public int insert(String tableName, Properties row) {
        Properties newProps = cloneProperties(row);
        initTableIfNotExists(tableName);
        tables.get(tableName).add(newProps);
        return 1;
    }

    private void initTableIfNotExists(String tableName) {
        if (!tables.containsKey(tableName)) {
            tables.put(tableName, new ArrayList<>());
        }
    }

    @Override
    public int delete(String tableName, Properties condition) {
        List<Properties> deletingRows = findMatchedRows(tableName, condition);
        for (Properties deletingRow : deletingRows) {
            tables.get(tableName).removeIf(row -> row == deletingRow);
        }
        return deletingRows.size();
    }

    private List<Properties> findMatchedRows(String tableName, Properties condition) {
        List<Properties> matchedRow = new ArrayList<>();
        if (condition.isEmpty()) {
            return matchedRow;
        }

        List<Properties> rows = tables.get(tableName);
        for (Properties row : rows) {
            if (isMatched(row, condition)) {
                matchedRow.add(row);
            }
        }
        return matchedRow;
    }

    @Override
    public int update(String tableName, Properties newProperties, Properties condition) {
        List<Properties> matchedRows = findMatchedRows(tableName, condition);
        for (Properties row : matchedRows) {
            applyNewProperties(newProperties, row);
        }
        return 0;
    }

    private void applyNewProperties(Properties newRow, Properties row) {
        Enumeration<?> newPropertiesKeyValue = newRow.propertyNames();
        while (newPropertiesKeyValue.hasMoreElements()) {
            String key = (String) newPropertiesKeyValue.nextElement();
            String value = newRow.getProperty(key);
            row.setProperty(key, value);
        }
    }

    @Override
    public String generateId() {
        String idString = String.valueOf(idCount);
        idCount++;
        return idString;
    }
}
