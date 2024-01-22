package org.services;

import org.common.HikariConnection;
import org.common.ConnectionNotFoundException;
import org.domain.Category;
import org.domain.Inventory;
import org.domain.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class InventoryServiceImpl implements InventoryService{
    public static InventoryServiceImpl inventoryService;
    Connection connection;

    private InventoryServiceImpl(){
        connection = null;
    }

    public static InventoryServiceImpl getInstance() {
        if (inventoryService == null){
            inventoryService = new InventoryServiceImpl();
        }
        return inventoryService;
    }

    @Override
    public boolean doesCategoryExist(UUID cat_id, Connection connection) throws SQLException {
        ResultSet resultSet = null;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.DOES_CATEGORY_ID_EXIST);
            preparedStatement.setString(1, String.valueOf(cat_id));
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            return count > 0;
        } finally {
            if (resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
        }
    }




    @Override
    public UUID doesCategoryExist(String cat_name, Connection connection) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.DOES_CATEGORY_EXIST);
            preparedStatement.setString(1, cat_name);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            if (count > 0){
                return getCategoryIdByName(cat_name, connection);
            }
            return null;
        } finally {
            if (resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
        }
    }
    @Override
    public boolean doesLocationExist(UUID loc_id, Connection connection) throws SQLException {
        ResultSet resultSet = null;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.DOES_LOCATION_ID_EXIST);
            preparedStatement.setString(1, String.valueOf(loc_id));
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            return count > 0;
        } finally {
            if (resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
        }
    }
    @Override
    public UUID doesLocationExist(String loc_name, Connection connection) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.DOES_LOCATION_EXIST);
            preparedStatement.setString(1, loc_name);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            if (count > 0){
                return getLocationIdByName(loc_name, connection);
            }
            return null;
        } finally {
            if (resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
        }
    }
    @Override
    public UUID getCategoryIdByName(String cat_name, Connection connection) throws SQLException {
        ResultSet resultSet = null;
        String id;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.GET_CATEGORY_ID_BY_NAME);
            preparedStatement.setString(1, cat_name);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getString(1);
            if (id != null)
                return UUID.fromString(id);
            else
                return null;
        } finally {
            if (resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
        }
    }
    @Override
    public UUID getLocationIdByName(String loc_name, Connection connection) throws SQLException {
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.GET_LOCATION_ID_BY_NAME);
            preparedStatement.setString(1, loc_name);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return UUID.fromString(resultSet.getString(1));
        } finally {
            if (resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
        }
    }
    @Override
    public Category addCategory(String cat_name, Connection connection) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        Category category = new Category();
        UUID cat_id = doesCategoryExist(cat_name, connection);
        if(cat_id == null){   //!doesCategoryExist(cat_name)
            UUID id = UUID.randomUUID();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(Queries.INSERT_CATEGORY);
                preparedStatement.setString(1, String.valueOf(id));
                preparedStatement.setString(2, cat_name);
                int rowsEffected = preparedStatement.executeUpdate();
                if (rowsEffected > 0) {
                    category.setCategory_id(id);
                    category.setCategory_name(cat_name);
                }
                return category;
            } finally {}
        } else {
            category.setCategory_id(cat_id);
            category.setCategory_name(cat_name);
            return category;
        }
    }
    @Override
    public Location addLocation(String loc_name, Connection connection) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        Location location = new Location();
        UUID loc_id = doesLocationExist(loc_name, connection);
        if(loc_id == null){   // !doesLocationExist(loc_name)
            UUID id = UUID.randomUUID();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(Queries.INSERT_LOCATION);
                preparedStatement.setString(1, String.valueOf(id));
                preparedStatement.setString(2, loc_name);
                int rowsEffected = preparedStatement.executeUpdate();
                if (rowsEffected > 0){
                    location.setLocation_id(id);
                    location.setLocation_name(loc_name);
                }
                return location;
            } finally {}
        } else {
            location.setLocation_id(loc_id);
            location.setLocation_name(loc_name);
            return location;
        }
    }
    @Override
    public boolean updateCategory(Category category, Connection connection) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        UUID cat_id = doesCategoryExist(category.getCategory_name(), connection);
        if (cat_id != null) {
            category.setCategory_id(cat_id);
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(Queries.UPDATE_CATEGORY);
                preparedStatement.setString(1, category.getCategory_name());
                preparedStatement.setString(2, String.valueOf(category.getCategory_id()));
                int rowsEffected = preparedStatement.executeUpdate();
                return rowsEffected > 0;
            } finally {}
        } else {
            return false;   // NOTE: The record doesn't exist.
        }
    }
    @Override
    public boolean updateLocation(Location location, Connection connection) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        UUID loc_id = doesLocationExist(location.getLocation_name(), connection);
        if (loc_id != null) {
            location.setLocation_id(loc_id);
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(Queries.UPDATE_LOCATION);
                preparedStatement.setString(1, location.getLocation_name());
                preparedStatement.setString(2, String.valueOf(location.getLocation_id()));
                int rowsEffected = preparedStatement.executeUpdate();
                return rowsEffected > 0;
            } finally {}
        } else {
            return false;   // NOTE: The record doesn't exist.
        }
    }

    @Override
    public Location addLocation(String loc_name) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        connection = HikariConnection.getPooledConnection();
        return addLocation(loc_name, connection);
    }

    @Override
    public Category addCategory(String cat_name) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        connection = HikariConnection.getPooledConnection();
        return addCategory(cat_name, connection);
    }


    @Override
    public ArrayList<Inventory> fetchAll() throws SQLException, ClassNotFoundException, ConnectionNotFoundException {

        ArrayList<Inventory> inventories = new ArrayList<>();
        Inventory inventory;
        ResultSet resultSet = null;
        try {
            connection = HikariConnection.getPooledConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.SELECT_ALL_DATA);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){

                inventory = new Inventory(resultSet);
                inventories.add(inventory);
            }
            return inventories;

        } finally {
            if (resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }
    }

    public Inventory fetchByID(UUID uuid) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {

        ResultSet resultSet = null;
        try {
            connection = HikariConnection.getPooledConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.SELECT_BY_ID);
            preparedStatement.setString(1, String.valueOf(uuid));
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Inventory(resultSet);
        } finally {
            if (resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
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
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.SELECT_ALL_BY_CATEGORY);
            preparedStatement.setString(1, String.valueOf(uuid));
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                inventory = new Inventory(resultSet);
                inventories.add(inventory);
            }
            return inventories;
        } finally {
            if (resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
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
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.SELECT_ALL_BY_LOCATION);
            preparedStatement.setString(1, String.valueOf(uuid));
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                inventory = new Inventory(resultSet);
                inventories.add(inventory);
            }
            return inventories;
        } finally {
            if (resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
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
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.SELECT_ALL_BY_LOCATION_AND_CATEGORY);
            preparedStatement.setString(1, String.valueOf(loc_id));
            preparedStatement.setString(2, String.valueOf(cat_id));
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                inventory = new Inventory(resultSet);
                inventories.add(inventory);
            }
            return inventories;
        } finally {
            if (resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }
    }
    @Override
    public Inventory addInventory(Inventory inv) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {

        Inventory inventory = new Inventory();
        try {

            connection = HikariConnection.getPooledConnection();

            inventory.setItem_category(addCategory(inv.getItem_category().getCategory_name(), connection));
            inventory.setItem_location(addLocation(inv.getItem_location().getLocation_name(), connection));

            PreparedStatement preparedStatement = connection.prepareStatement(Queries.INSERT_INVENTORY);
            preparedStatement.setString(1, String.valueOf(inv.getId()));
            preparedStatement.setString(2, inv.getItem_name());
            preparedStatement.setInt(3, inv.getItem_quantity());
            preparedStatement.setString(4, String.valueOf(inventory.getItem_category().getCategory_id()));
            preparedStatement.setString(5, String.valueOf(inventory.getItem_location().getLocation_id()));

            if (preparedStatement.executeUpdate() > 0){
                inventory.setId(inv.getId());
                inventory.setItem_name(inv.getItem_name());
                inventory.setItem_quantity(inv.getItem_quantity());
            }
            return inventory;
            } finally {
                if (connection != null && !connection.isClosed()){
                    connection.close();
                }
            }
    }
    @Override
    public Inventory updateInventory(Inventory inventory) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        Category category = new Category(inventory.getItem_category().getCategory_name());
        Location location = new Location(inventory.getItem_location().getLocation_name());
        connection = HikariConnection.getPooledConnection();
            if (updateCategory(inventory.getItem_category(), connection)) {
                if (updateLocation(inventory.getItem_location(), connection)) {
                    try {
                        PreparedStatement preparedStatement = connection.prepareStatement(Queries.UPDATE_INVENTORY);
                        preparedStatement.setString(1, inventory.getItem_name());
                        preparedStatement.setInt(2, inventory.getItem_quantity());
                        preparedStatement.setString(3, String.valueOf(inventory.getItem_category().getCategory_id()));
                        preparedStatement.setString(4, String.valueOf(inventory.getItem_location().getLocation_id()));
                        preparedStatement.setString(5, String.valueOf(inventory.getId()));
                        int rowsEffected = preparedStatement.executeUpdate();
                        if (rowsEffected > 0){
                            category.setCategory_id(getCategoryIdByName(category.getCategory_name(), connection));
                            location.setLocation_id(getLocationIdByName(location.getLocation_name(), connection));

                            inventory.setItem_category(category);
                            inventory.setItem_location(location);
                            return inventory;
                        }
                    } finally {
                        if (connection != null && !connection.isClosed()) {
                            connection.close();
                        }
                    }
                } else { System.out.println("Location couldn't be updated."); }
            } else { System.out.println("Category couldn't be updated."); }
            return null;
    }
    @Override
    public boolean delete(UUID inventory_id) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        // note: no cascade delete.
        try {
            connection = HikariConnection.getPooledConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.DELETE_INVENTORY);
            preparedStatement.setString(1, String.valueOf(inventory_id));
            int rowsEffected = preparedStatement.executeUpdate();
            return rowsEffected > 0;
        } finally {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }
}