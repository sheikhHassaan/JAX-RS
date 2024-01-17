package Services;

import Common.HikariConnection;
import Common.ConnectionNotFoundException;
import Domain.Category;
import Domain.Inventory;
import Domain.Location;
import org.w3c.dom.html.HTMLIsIndexElement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import static Services.Queries.*;

public class InventoryServiceImpl implements InventoryService{
    public static InventoryServiceImpl inventoryService;
    HikariConnection hikariConnection = new HikariConnection();
    Connection connection;

    private InventoryServiceImpl(){
        connection = null;
        if (hikariConnection == null){
            hikariConnection = new HikariConnection();
        }
    }

    public static InventoryServiceImpl getInstance() {
        if (inventoryService == null){
            inventoryService = new InventoryServiceImpl();
        }
        return inventoryService;
    }

    @Override
    public boolean doesInventoryExist(UUID uuid) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {

        ResultSet resultSet = null;
        try {
            connection = HikariConnection.getPooledConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DOES_INVENTORY_ID_EXIST);
            preparedStatement.setString(1, String.valueOf(uuid));
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            return count > 0;
        } finally {
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            if (resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
        }
    }

    @Override
    public boolean doesCategoryExist(UUID cat_id) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        ResultSet resultSet = null;
        try{
            connection = HikariConnection.getPooledConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DOES_CATEGORY_ID_EXIST);
            preparedStatement.setString(1, String.valueOf(cat_id));
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            return count > 0;
        } finally {
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            if (resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
        }
    }

    @Override
    public UUID doesCategoryExist(String cat_name) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        ResultSet resultSet = null;
        try {
            connection = HikariConnection.getPooledConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DOES_CATEGORY_EXIST);
            preparedStatement.setString(1, cat_name);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            if (count > 0){
                UUID id = getCategoryIdByName(cat_name);
                return id;
            }
            return null;
        } finally {
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            if (resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
        }
    }

    @Override
    public boolean doesLocationExist(UUID loc_id) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        ResultSet resultSet = null;
        try{
            connection = HikariConnection.getPooledConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DOES_LOCATION_ID_EXIST);
            preparedStatement.setString(1, String.valueOf(loc_id));
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            return count > 0;
        } finally {
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            if (resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
        }
    }

    @Override
    public UUID doesLocationExist(String loc_name) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        ResultSet resultSet = null;
        try {
            connection = HikariConnection.getPooledConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DOES_LOCATION_EXIST);
            preparedStatement.setString(1, loc_name);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            if (count > 0){
                UUID id = getLocationIdByName(loc_name);
                return id;
            }
            return null;
        } finally {
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            if (resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
        }
    }

    @Override
    public UUID getCategoryIdByName(String cat_name) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        ResultSet resultSet = null;
        String id = null;
        try {
            connection = HikariConnection.getPooledConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CATEGORY_ID_BY_NAME);
            preparedStatement.setString(1, cat_name);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getString(1);
            if (id != null)
                return UUID.fromString(id);
            else
                return null;
        } finally {
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            if (resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
        }
    }

    @Override
    public UUID getLocationIdByName(String loc_name) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        ResultSet resultSet = null;
        try {
            connection = HikariConnection.getPooledConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_LOCATION_ID_BY_NAME);
            preparedStatement.setString(1, loc_name);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            UUID id = UUID.fromString(resultSet.getString(1));
            return id;
        } finally {
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            if (resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
        }
    }

    /*
    public Inventory extractValues(ResultSet resultSet) throws SQLException {
        Inventory inventory = new Inventory();
        Category category = new Category();
        Location location = new Location();

        inventory.setId(UUID.fromString(resultSet.getString(1)));
        inventory.setItem_name(resultSet.getString(2));
        inventory.setItem_quantity(resultSet.getInt(3));
        category.setCategory_id(UUID.fromString(resultSet.getString(4)));
        category.setCategory_name(resultSet.getString(5));
        location.setLocation_id(UUID.fromString(resultSet.getString(6)));
        location.setLocation_name(resultSet.getString(7));
        inventory.setItem_category(category);
        inventory.setItem_location(location);

        return inventory;
    }
     */  // extra: Extract values function

    @Override
    public ArrayList<Inventory> fetchAll() throws SQLException, ClassNotFoundException, ConnectionNotFoundException {

        ArrayList<Inventory> inventories = new ArrayList<>();
        Inventory inventory;
        ResultSet resultSet = null;
        try {
            connection = HikariConnection.getPooledConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DATA);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){

                inventory = new Inventory(resultSet);
                inventories.add(inventory);
            }
            return inventories;

        } finally {
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            if (resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
        }
    }

    public Inventory fetchByID(UUID uuid) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {

        ResultSet resultSet = null;

        try {
            connection = HikariConnection.getPooledConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setString(1, String.valueOf(uuid));
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Inventory(resultSet);
        } finally {
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            if (resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
        }
    }

    @Override
    public ArrayList<Inventory> fetchAllByCategory(UUID uuid) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        ArrayList<Inventory> inventories = new ArrayList<>();
        Inventory inventory;
        ResultSet resultSet = null;
        try {
            connection = HikariConnection.getPooledConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_CATEGORY);
            preparedStatement.setString(1, String.valueOf(uuid));
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                inventory = new Inventory(resultSet);
                inventories.add(inventory);
            }
            return inventories;
        } finally {
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            if (resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
        }
    }

    @Override
    public ArrayList<Inventory> fetchAllByLocation(UUID uuid) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        ArrayList<Inventory> inventories = new ArrayList<>();
        Inventory inventory;
        ResultSet resultSet = null;
        try {
            connection = HikariConnection.getPooledConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_LOCATION);
            preparedStatement.setString(1, String.valueOf(uuid));
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                inventory = new Inventory(resultSet);
                inventories.add(inventory);
            }
            return inventories;
        } finally {
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            if (resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
        }
    }

    @Override
    public ArrayList<Inventory> fetchAllByLocationAndCategory(UUID loc_id, UUID cat_id) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        ArrayList<Inventory> inventories = new ArrayList<>();
        Inventory inventory;
        ResultSet resultSet = null;
        try {
            connection = HikariConnection.getPooledConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_LOCATION_AND_CATEGORY);
            preparedStatement.setString(1, String.valueOf(loc_id));
            preparedStatement.setString(2, String.valueOf(cat_id));
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                inventory = new Inventory(resultSet);
                inventories.add(inventory);
            }
            return inventories;
        } finally {
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            if (resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
        }
    }

    @Override
    public Inventory addInventory(Inventory inv) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {

        Inventory inventory = new Inventory();
        if (!doesInventoryExist(inv.getId())){
            try {
                addCategory(inv.getItem_category().getCategory_name());
                addLocation(inv.getItem_location().getLocation_name());

                connection = HikariConnection.getPooledConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INVENTORY);

                preparedStatement.setString(1, String.valueOf(inv.getId()));
                preparedStatement.setString(2, inv.getItem_name());
                preparedStatement.setInt(3, inv.getItem_quantity());
                preparedStatement.setString(4, String.valueOf(inv.getItem_category().getCategory_id()));
                preparedStatement.setString(5, String.valueOf(inv.getItem_location().getLocation_id()));

                preparedStatement.executeUpdate();
                return inventory;
//            location.setLocation_id(inv.getItem_location().getLocation_id());
//            location.setLocation_name(inv.getItem_location().getLocation_name());
//
//            category.setCategory_id(inv.getItem_category().getCategory_id());
//            category.setCategory_name(inv.getItem_category().getCategory_name());
            } finally {
                if (connection != null && !connection.isClosed()){
                    connection.close();
                }
            }
        } else {
            inventory = inv;
        }
        return inventory;
    }

    @Override
    public Inventory updateInventory(Inventory inv) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        Inventory inventory = null;
        if (doesInventoryExist(inv.getId())) {
            if (updateCategory(inv.getItem_category())) {
                if (updateLocation(inv.getItem_location())) {
                    try {
                        connection = HikariConnection.getPooledConnection();
                        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INVENTORY);
                        preparedStatement.setString(1, inv.getItem_name());
                        preparedStatement.setInt(2, inv.getItem_quantity());
                        preparedStatement.setString(3, String.valueOf(inv.getItem_category().getCategory_id()));
                        preparedStatement.setString(4, String.valueOf(inv.getItem_location().getLocation_id()));
                        preparedStatement.setString(5, String.valueOf(inv.getId()));
                        int rowsEffected = preparedStatement.executeUpdate();
                        if (rowsEffected > 0){
                            inventory = inv;
                        }
                    } finally {
                        if (connection != null && !connection.isClosed()) {
                            connection.close();
                        }
                    }
                } else { System.out.println("Location couldn't be updated."); }
            } else { System.out.println("Category couldn't be updated."); }
        }
        else {    // NOTE: This inventory doesn't exist.
            System.out.println("Inventory with ID: "+inv.getId()+" doesn't exist.");
        }
        return inventory;
    }

    @Override
    public boolean delete(UUID inventory_id) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        // note: no cascade delete.
        if (doesInventoryExist(inventory_id)){
            try {
                connection = HikariConnection.getPooledConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_INVENTORY);
                preparedStatement.setString(1, String.valueOf(inventory_id));
                int rowsEffected = preparedStatement.executeUpdate();
                return rowsEffected > 0;
            } finally {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            }
        }
        else { return false; }   // NOTE: This inventory doesn't exist.
    }

    @Override
    public Category addCategory(String cat_name) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        Category category = new Category();
        UUID cat_id = doesCategoryExist(cat_name);
        if(cat_id == null){   //!doesCategoryExist(cat_name)
            UUID id = UUID.randomUUID();
            try {
                connection = HikariConnection.getPooledConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATEGORY);
                preparedStatement.setString(1, String.valueOf(id));
                preparedStatement.setString(2, cat_name);
                int rowsEffected = preparedStatement.executeUpdate();
                if (rowsEffected > 0){
                    category.setCategory_id(id);
                    category.setCategory_name(cat_name);
                }
                return category;
            } finally {
                if (connection != null && !connection.isClosed()){
                    connection.close();
                }
            }
        } else {
            category.setCategory_id(cat_id);
            category.setCategory_name(cat_name);
            return category;


            /*
            ResultSet resultSet = null;
            try {
                connection = HikariConnection.getPooledConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_CATEGORY_BY_NAME);
                preparedStatement.setString(1, cat_name);
                resultSet = preparedStatement.executeQuery();

                resultSet.next();
                category.setCategory_id(UUID.fromString(resultSet.getString(1)));
                category.setCategory_name(resultSet.getString(2));

                return category;
            } finally {
                if (connection != null && !connection.isClosed()){
                    connection.close();
                }
                if (resultSet != null && !resultSet.isClosed()){
                    resultSet.close();
                }
            }
             */
        }
    }

    @Override
    public Location addLocation(String loc_name) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        Location location = new Location();
        UUID loc_id = doesLocationExist(loc_name);
        if(loc_id == null){   // !doesLocationExist(loc_name)
            UUID id = UUID.randomUUID();
            try {
                connection = HikariConnection.getPooledConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOCATION);
                preparedStatement.setString(1, String.valueOf(id));
                preparedStatement.setString(2, loc_name);
                int rowsEffected = preparedStatement.executeUpdate();
                if (rowsEffected > 0){
                    location.setLocation_id(id);
                    location.setLocation_name(loc_name);
                }
                return location;
            } finally {
                if (connection != null && !connection.isClosed()){
                    connection.close();
                }
            }
        } else {

            location.setLocation_id(loc_id);
            location.setLocation_name(loc_name);
            return location;


            /*
            ResultSet resultSet = null;
            try {
                connection = HikariConnection.getPooledConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_LOCATION_BY_NAME);
                preparedStatement.setString(1, loc_name);
                resultSet = preparedStatement.executeQuery();

                resultSet.next();
                location.setLocation_id(UUID.fromString(resultSet.getString(1)));
                location.setLocation_name(resultSet.getString(2));

                return location;
            } finally {
                if (connection != null && !connection.isClosed()){
                    connection.close();
                }
                if (resultSet != null && !resultSet.isClosed()){
                    resultSet.close();
                }
            }

             */
        }
    }

    @Override
    public boolean updateCategory(Category category) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        UUID cat_id = doesCategoryExist(category.getCategory_name());
        if (cat_id != null) {
            try {
                connection = HikariConnection.getPooledConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORY);
                preparedStatement.setString(1, category.getCategory_name());
                preparedStatement.setString(2, String.valueOf(category.getCategory_id()));
                int rowsEffected = preparedStatement.executeUpdate();
                return rowsEffected > 0;
            } finally {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            }
        } else {
            return false;   // NOTE: The record doesn't exist.
        }
    }

    @Override
    public boolean updateLocation(Location location) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        UUID loc_id = doesLocationExist(location.getLocation_name());
        if (loc_id != null) {
            try {
                connection = HikariConnection.getPooledConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LOCATION);
                preparedStatement.setString(1, location.getLocation_name());
                preparedStatement.setString(2, String.valueOf(location.getLocation_id()));
                int rowsEffected = preparedStatement.executeUpdate();
                return rowsEffected > 0;
            } finally {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            }
        } else {
            return false;   // NOTE: The record doesn't exist.
        }
    }

    @Override
    public boolean deleteCategory(UUID cat_id) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        if(doesCategoryExist(cat_id)){
            try {
                connection = HikariConnection.getPooledConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CATEGORY);
                preparedStatement.setString(1, String.valueOf(cat_id));
                int rowsEffected = preparedStatement.executeUpdate();
                return rowsEffected > 0;
            } finally {
                if (connection != null && !connection.isClosed()){
                    connection.close();
                }
            }
        } else { return false; } // NOTE: This category doesn't exist.
    }

    @Override
    public boolean deleteLocation(UUID loc_id) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        if(doesCategoryExist(loc_id)){
            try {
                connection = HikariConnection.getPooledConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_LOCATION);
                preparedStatement.setString(1, String.valueOf(loc_id));
                int rowsEffected = preparedStatement.executeUpdate();
                return rowsEffected > 0;
            } finally {
                if (connection != null && !connection.isClosed()){
                    connection.close();
                }
            }
        } else { return false; } // NOTE: This location doesn't exist.
    }
}