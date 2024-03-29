package org.services;

public class Queries {

    public static final String DOES_CATEGORY_EXIST = "SELECT COUNT(*) AS COUNT FROM INVENTORY.ITEM_CATEGORY WHERE CATEGORY_NAME = ?";
    public static final String DOES_LOCATION_EXIST = "SELECT COUNT(*) AS COUNT FROM INVENTORY.ITEM_LOCATION WHERE LOCATION_NAME = ?";
    public static final String DOES_INVENTORY_EXIST = "SELECT COUNT(*) AS COUNT FROM INVENTORY.INVENTORY WHERE ID = ?";

    public static final String GET_CATEGORY = "SELECT * FROM INVENTORY.ITEM_CATEGORY WHERE CATEGORY_NAME = ?";
    public static final String GET_LOCATION = "SELECT * FROM INVENTORY.ITEM_LOCATION WHERE LOCATION_NAME = ?";

    public static final String SELECT_ALL_DATA =
            "SELECT INVENTORY.ID, ITEM_NAME, ITEM_QUANTITY, ITEM_CATEGORY_ID, ITEM_CATEGORY.CATEGORY_NAME, ITEM_LOCATION_ID, ITEM_LOCATION.LOCATION_NAME " +
            "FROM INVENTORY.INVENTORY INNER JOIN INVENTORY.ITEM_CATEGORY " +
            "ON INVENTORY.INVENTORY.ITEM_CATEGORY_ID = INVENTORY.ITEM_CATEGORY.ID " +
            "INNER JOIN INVENTORY.ITEM_LOCATION " +
            "ON INVENTORY.INVENTORY.ITEM_LOCATION_ID = INVENTORY.ITEM_LOCATION.ID;";
    public static final String SELECT_BY_ID =
            "SELECT INVENTORY.ID, ITEM_NAME, ITEM_QUANTITY, ITEM_CATEGORY_ID, ITEM_CATEGORY.CATEGORY_NAME, ITEM_LOCATION_ID, ITEM_LOCATION.LOCATION_NAME " +
            "FROM INVENTORY.INVENTORY INNER JOIN INVENTORY.ITEM_CATEGORY " +
            "ON INVENTORY.INVENTORY.ITEM_CATEGORY_ID = INVENTORY.ITEM_CATEGORY.ID " +
            "INNER JOIN INVENTORY.ITEM_LOCATION " +
            "ON INVENTORY.INVENTORY.ITEM_LOCATION_ID = INVENTORY.ITEM_LOCATION.ID " +
            "WHERE INVENTORY.INVENTORY.ID = ?";
    public static final String SELECT_ALL_BY_CATEGORY =
            "SELECT INVENTORY.ID, ITEM_NAME, ITEM_QUANTITY, ITEM_CATEGORY_ID, ITEM_CATEGORY.CATEGORY_NAME, ITEM_LOCATION_ID, ITEM_LOCATION.LOCATION_NAME " +
            "FROM INVENTORY.INVENTORY INNER JOIN INVENTORY.ITEM_CATEGORY " +
            "ON INVENTORY.INVENTORY.ITEM_CATEGORY_ID = INVENTORY.ITEM_CATEGORY.ID " +
            "INNER JOIN INVENTORY.ITEM_LOCATION " +
            "ON INVENTORY.INVENTORY.ITEM_LOCATION_ID = INVENTORY.ITEM_LOCATION.ID " +
            "WHERE INVENTORY.ITEM_CATEGORY_ID = ?;";
    public static final String SELECT_ALL_BY_LOCATION =
            "SELECT INVENTORY.ID, ITEM_NAME, ITEM_QUANTITY, ITEM_CATEGORY_ID, ITEM_CATEGORY.CATEGORY_NAME, ITEM_LOCATION_ID, ITEM_LOCATION.LOCATION_NAME " +
            "FROM INVENTORY.INVENTORY INNER JOIN INVENTORY.ITEM_CATEGORY " +
            "ON INVENTORY.INVENTORY.ITEM_CATEGORY_ID = INVENTORY.ITEM_CATEGORY.ID " +
            "INNER JOIN INVENTORY.ITEM_LOCATION " +
            "ON INVENTORY.INVENTORY.ITEM_LOCATION_ID = INVENTORY.ITEM_LOCATION.ID " +
            "WHERE INVENTORY.ITEM_LOCATION_ID = ?;";
    public static final String SELECT_ALL_BY_LOCATION_AND_CATEGORY =
            "SELECT INVENTORY.ID, ITEM_NAME, ITEM_QUANTITY, ITEM_CATEGORY_ID, ITEM_CATEGORY.CATEGORY_NAME, ITEM_LOCATION_ID, ITEM_LOCATION.LOCATION_NAME " +
            "FROM INVENTORY.INVENTORY INNER JOIN INVENTORY.ITEM_CATEGORY " +
            "ON INVENTORY.INVENTORY.ITEM_CATEGORY_ID = INVENTORY.ITEM_CATEGORY.ID " +
            "INNER JOIN INVENTORY.ITEM_LOCATION " +
            "ON INVENTORY.INVENTORY.ITEM_LOCATION_ID = INVENTORY.ITEM_LOCATION.ID " +
            "WHERE INVENTORY.ITEM_LOCATION_ID = ? AND INVENTORY.ITEM_CATEGORY_ID = ?;";

    public static final String INSERT_INTO_CATEGORY_LOCATIONS =
            "INSERT IGNORE INTO inventory.category_locations (category_id, location_id) " +
            "SELECT ? AS category_id, ? AS location_id FROM DUAL " +
            "WHERE NOT EXISTS ( " +
            "    SELECT 1 FROM inventory.category_locations " +
            "    WHERE category_id = ? AND location_id = ? );";

    public static final String INSERT_CATEGORY =
            "INSERT INTO INVENTORY.ITEM_CATEGORY (ID, CATEGORY_NAME) VALUES (?, ?);";

    public static final String INSERT_LOCATION =
            "INSERT INTO INVENTORY.ITEM_LOCATION (ID, LOCATION_NAME) VALUES (?, ?);";
    public static final String INSERT_INVENTORY =
            "INSERT INTO INVENTORY.INVENTORY (ID, ITEM_NAME, ITEM_QUANTITY, ITEM_CATEGORY_ID, ITEM_LOCATION_ID) VALUES (?, ?, ?, ?, ?);";
    public static final String UPDATE_INVENTORY = "UPDATE INVENTORY.INVENTORY " +
            "SET ITEM_NAME = ?, ITEM_QUANTITY = ?, ITEM_CATEGORY_ID = ?, ITEM_LOCATION_ID = ? WHERE ID = ?;";
    public static final String DELETE_INVENTORY = "DELETE FROM INVENTORY.INVENTORY WHERE ID = ?";
}