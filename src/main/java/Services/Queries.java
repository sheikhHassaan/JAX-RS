package Services;

public class Queries {


    public static final String DOES_INVENTORY_ID_EXIST = "SELECT COUNT(*) AS COUNT FROM INVENTORY.INVENTORY WHERE ID = ?;";
    public static final String DOES_CATEGORY_EXIST = "SELECT COUNT(*) AS COUNT FROM INVENTORY.ITEM_CATEGORY WHERE CATEGORY_NAME = ?";
    public static final String DOES_LOCATION_EXIST = "SELECT COUNT(*) AS COUNT FROM INVENTORY.ITEM_LOCATION WHERE LOCATION_NAME = ?";

    public static final String DOES_CATEGORY_ID_EXIST = "SELECT COUNT(*) AS COUNT FROM INVENTORY.ITEM_CATEGORY WHERE ID = ?";
    public static final String DOES_LOCATION_ID_EXIST = "SELECT COUNT(*) AS COUNT FROM INVENTORY.ITEM_LOCATION WHERE ID = ?";


    public static final String GET_CATEGORY_BY_NAME = "SELECT * FROM INVENTORY.ITEM_CATEGORY WHERE CATEGORY_NAME = ?";
    public static final String GET_LOCATION_BY_NAME = "SELECT * FROM INVENTORY.ITEM_LOCATION WHERE LOCATION_NAME = ?";


    public static final String GET_CATEGORY_ID_BY_NAME = "SELECT ID FROM INVENTORY.ITEM_CATEGORY WHERE CATEGORY_NAME = ?";
    public static final String GET_LOCATION_ID_BY_NAME = "SELECT ID FROM INVENTORY.ITEM_LOCATION WHERE LOCATION_NAME = ?";

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
    public static final String SELECT_ALL_BY_LOCATION = "SELECT INVENTORY.ID, ITEM_NAME, ITEM_QUANTITY, ITEM_CATEGORY_ID, ITEM_CATEGORY.CATEGORY_NAME, ITEM_LOCATION_ID, ITEM_LOCATION.LOCATION_NAME FROM INVENTORY.INVENTORY INNER JOIN INVENTORY.ITEM_CATEGORY ON INVENTORY.INVENTORY.ITEM_CATEGORY_ID = INVENTORY.ITEM_CATEGORY.ID INNER JOIN INVENTORY.ITEM_LOCATION ON INVENTORY.INVENTORY.ITEM_LOCATION_ID = INVENTORY.ITEM_LOCATION.ID WHERE INVENTORY.ITEM_LOCATION_ID = ?;";

//            "SELECT INVENTORY.ID, ITEM_NAME, ITEM_QUANTITY, ITEM_CATEGORY_ID, ITEM_CATEGORY.CATEGORY_NAME, ITEM_LOCATION_ID, ITEM_LOCATION.LOCATION_NAME " +
//            "FROM INVENTORY.INVENTORY INNER JOIN INVENTORY.ITEM_CATEGORY " +
//            "ON INVENTORY.INVENTORY.ITEM_CATEGORY_ID = INVENTORY.ITEM_CATEGORY.ID " +
//            "INNER JOIN INVENTORY.ITEM_LOCATION " +
//            "ON INVENTORY.INVENTORY.ITEM_LOCATION_ID = INVENTORY.ITEM_LOCATION.ID " +
//            "WHERE INVENTORY.ITEM_LOCATION_ID = ?;";
    public static final String SELECT_ALL_BY_LOCATION_AND_CATEGORY =
            "SELECT INVENTORY.ID, ITEM_NAME, ITEM_QUANTITY, ITEM_CATEGORY_ID, ITEM_CATEGORY.CATEGORY_NAME, ITEM_LOCATION_ID, ITEM_LOCATION.LOCATION_NAME " +
            "FROM INVENTORY.INVENTORY INNER JOIN INVENTORY.ITEM_CATEGORY " +
            "ON INVENTORY.INVENTORY.ITEM_CATEGORY_ID = INVENTORY.ITEM_CATEGORY.ID " +
            "INNER JOIN INVENTORY.ITEM_LOCATION " +
            "ON INVENTORY.INVENTORY.ITEM_LOCATION_ID = INVENTORY.ITEM_LOCATION.ID " +
            "WHERE INVENTORY.ITEM_LOCATION_ID = ? AND INVENTORY.ITEM_CATEGORY_ID = ?;";

    public static final String INSERT_CATEGORY =
            "INSERT INTO INVENTORY.ITEM_CATEGORY (ID, CATEGORY_NAME) VALUES (?, ?);";

    public static final String INSERT_LOCATION =
            "INSERT INTO INVENTORY.ITEM_LOCATION (ID, LOCATION_NAME) VALUES (?, ?);";
    public static final String INSERT_INVENTORY =
            "INSERT INTO INVENTORY.INVENTORY (ID, ITEM_NAME, ITEM_QUANTITY, ITEM_CATEGORY_ID, ITEM_LOCATION_ID) VALUES (?, ?, ?, ?, ?);";

    public static final String UPDATE_LOCATION = "UPDATE INVENTORY.ITEM_LOCATION SET LOCATION_NAME = ? WHERE ID = ?;";
    public static final String UPDATE_CATEGORY = "UPDATE INVENTORY.ITEM_CATEGORY SET CATEGORY_NAME = ? WHERE ID = ?;";
    public static final String UPDATE_INVENTORY = "UPDATE INVENTORY.INVENTORY " +
            "SET ITEM_NAME = ?, ITEM_QUANTITY = ?, ITEM_CATEGORY_ID = ?, ITEM_LOCATION_ID = ? WHERE ID = ?;";
    public static final String DELETE_LOCATION = "DELETE FROM INVENTORY.ITEM_LOCATION WHERE ID = ?";
    public static final String DELETE_CATEGORY = "DELETE FROM INVENTORY.ITEM_CATEGORY WHERE ID = ?";
    public static final String DELETE_INVENTORY = "DELETE FROM INVENTORY.INVENTORY WHERE ID = ?";
}
