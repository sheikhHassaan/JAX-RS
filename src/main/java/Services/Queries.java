package Services;

import java.sql.*;

public class Queries {
    public final String DOES_ID_EXIST = "SELECT COUNT(*) AS COUNT FROM INVENTORY.INVENTORY WHERE ID = ?;";
    public final String SELECT_BY_ID = "SELECT * FROM INVENTORY.INVENTORY WHERE ID = ?;";
    public final String SELECT_ALL = "SELECT * FROM INVENTORY.INVENTORY;";
    public final String SELECT_ALL_BY_CATEGORY = "SELECT * FROM INVENTORY.INVENTORY WHERE ITEM_CATEGORY_ID = ?;";
    public final String SELECT_ALL_BY_LOCATION = "SELECT * FROM INVENTORY.INVENTORY WHERE ITEM_LOCATION_ID = ?;";
    public final String SELECT_ALL_BY_LOCATION_AND_CATEGORY = "SELECT * FROM INVENTORY.INVENTORY WHERE ITEM_CATEGORY_ID = ? AND ITEM_LOCATION_ID = ?;";
    public final String INSERT = "INSERT INTO INVENTORY.INVENTORY (ID, ITEM_NAME, ITEM_QUANTITY, ITEM_CATEGORY_ID, ITEM_LOCATION_ID) VALUES (?, ?, ?, ?, ?);";
    public final String UPDATE_BY_ID = "UPDATE INVENTORY.INVENTORY SET ID = ? ITEM_NAME = ? ITEM_QUANTITY = ? ITEM_CATEGORY_ID = ? ITEM_LOCATION_ID = ?;";
    public final String DELETE_BY_ID = "DELETE FROM INVENTORY.INVENTORY WHERE ID = ?;";

}
