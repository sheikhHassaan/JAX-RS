package org.services;

import java.util.UUID;
import java.sql.ResultSet;
import org.domain.Category;
import org.domain.Location;
import java.sql.Connection;
import java.util.ArrayList;
import org.domain.Inventory;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import org.common.HikariConnection;
import static org.services.Queries.*;
import org.common.ConnectionNotFoundException;
import javassist.tools.rmi.ObjectNotFoundException;

public class InventoryServiceImpl implements InventoryService{
    public static InventoryServiceImpl inventoryService;
    private InventoryServiceImpl(){
    }

    public static InventoryServiceImpl getInstance() {
        if (inventoryService == null){
            inventoryService = new InventoryServiceImpl();
        }
        return inventoryService;
    }

    public static boolean isExist(String filterValue, String query) throws SQLException, ConnectionNotFoundException, ClassNotFoundException {
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = HikariConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, filterValue);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            return count > 0;
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
    public boolean isCategoryExist(String catName) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        return isExist(catName, DOES_CATEGORY_EXIST);
    }

    @Override
    public boolean isLocationExist(String locName) throws SQLException, ConnectionNotFoundException, ClassNotFoundException {
        return isExist(locName, DOES_LOCATION_EXIST);
    }

    @Override
    public boolean isInventoryExist(String inventoryId) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        return isExist(inventoryId, DOES_INVENTORY_EXIST);
    }

    @Override
    public Category getCategory(String catName) throws SQLException, ConnectionNotFoundException, ClassNotFoundException {
        ResultSet resultSet = null;
        Connection connection = null;
        Category category = new Category();
        try {
            connection = HikariConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CATEGORY);
            preparedStatement.setString(1, catName);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            category.setCategoryId(resultSet.getString(1));
            category.setCategoryName(resultSet.getString(2));
            return category;
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
    public Location getLocation(String locName) throws SQLException, ConnectionNotFoundException, ClassNotFoundException {
        ResultSet resultSet = null;
        Connection connection = null;
        Location location = new Location();
        try {
            connection = HikariConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_LOCATION);
            preparedStatement.setString(1, locName);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            location.setLocationId(resultSet.getString(1));
            location.setLocationName(resultSet.getString(2));
            return location;
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
    public Category addCategory(String catName, Connection connection) throws SQLException {
        Category category = new Category();
        String id = String.valueOf(UUID.randomUUID());
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATEGORY);
        preparedStatement.setString(1, id);
        preparedStatement.setString(2, catName);
        int rowsEffected = preparedStatement.executeUpdate();
        if (rowsEffected > 0) {
            category.setCategoryId(id);
            category.setCategoryName(catName);
        }
        return category;
    }
    @Override
    public Location addLocation(String locName, Connection connection) throws SQLException {
        Location location = new Location();
        String id = String.valueOf(UUID.randomUUID());
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOCATION);
        preparedStatement.setString(1, id);
        preparedStatement.setString(2, locName);
        int rowsEffected = preparedStatement.executeUpdate();
        if (rowsEffected > 0){
            location.setLocationId(id);
            location.setLocationName(locName);
        }
        return location;
    }


    @Override
    public ArrayList<Inventory> fetchAll() throws SQLException, ClassNotFoundException, ConnectionNotFoundException {

        ArrayList<Inventory> inventories = new ArrayList<>();
        Connection connection = null;
        Inventory inventory;
        ResultSet resultSet = null;
        try {
            connection = HikariConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DATA);
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

    public Inventory fetchByID(String id) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = HikariConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setString(1, id);
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

    public ArrayList<Inventory> fetchAllByFilter(String id, String query) throws SQLException, ConnectionNotFoundException, ClassNotFoundException {
        ArrayList<Inventory> inventories = new ArrayList<>();
        Connection connection = null;
        Inventory inventory;
        ResultSet resultSet = null;
        try {
            connection = HikariConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
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
    public ArrayList<Inventory> fetchAllByCategory(String id) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        return fetchAllByFilter(id, SELECT_ALL_BY_CATEGORY);
    }

    @Override
    public ArrayList<Inventory> fetchAllByLocation(String id) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        return fetchAllByFilter(id, SELECT_ALL_BY_LOCATION);
    }

    @Override
    public ArrayList<Inventory> fetchAllByLocationAndCategory(String locId, String catId) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        ArrayList<Inventory> inventories = new ArrayList<>();
        Connection connection = null;
        Inventory inventory;
        ResultSet resultSet = null;
        try {
            connection = HikariConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_LOCATION_AND_CATEGORY);
            preparedStatement.setString(1, locId);
            preparedStatement.setString(2, catId);
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
        Connection connection = null;
        String id = String.valueOf(UUID.randomUUID());
        try {
            connection = HikariConnection.getConnection();
            if (!isCategoryExist(inv.getItemCategory().getCategoryName())) {
                Category category = addCategory(inv.getItemCategory().getCategoryName(), connection);
                inventory.setItemCategory(category);
            } else {
                inventory.setItemCategory(getCategory(inv.getItemCategory().getCategoryName()));
            }
            if (!isLocationExist(inv.getItemLocation().getLocationName())) {
                Location location = addLocation(inv.getItemLocation().getLocationName(), connection);
                inventory.setItemLocation(location);
            } else {
                inventory.setItemLocation(getLocation(inv.getItemLocation().getLocationName()));
            }
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INVENTORY);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, inv.getItemName());
            preparedStatement.setInt(3, inv.getItemQuantity());
            preparedStatement.setString(4, inventory.getItemCategory().getCategoryId());
            preparedStatement.setString(5, inventory.getItemLocation().getLocationId());

            if (preparedStatement.executeUpdate() > 0) {
                inventory.setId(id);
                inventory.setItemName(inv.getItemName());
                inventory.setItemQuantity(inv.getItemQuantity());
            }
            return inventory;
        } finally {
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }
    }


    @Override
    public Inventory updateInventory(Inventory inv) throws SQLException, ClassNotFoundException, ConnectionNotFoundException, ObjectNotFoundException {

        Inventory inventory = new Inventory();
        if(!isInventoryExist(inv.getId())){
            throw new ObjectNotFoundException("This inventory does not exist!");
        }
        Connection connection = null;
        try {
            connection = HikariConnection.getConnection();

            if (!isCategoryExist(inv.getItemCategory().getCategoryName())){
                inventory.setItemCategory(addCategory(inv.getItemCategory().getCategoryName(), connection));
            } else {
                inventory.setItemCategory(getCategory(inv.getItemCategory().getCategoryName()));
            }

            if (!isLocationExist(inv.getItemLocation().getLocationName())){
                inventory.setItemLocation(addLocation(inv.getItemLocation().getLocationName(), connection));
            } else {
                inventory.setItemLocation(getLocation(inv.getItemLocation().getLocationName()));
            }

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INVENTORY);

            preparedStatement.setString(1, inv.getItemName());
            preparedStatement.setInt(2, inv.getItemQuantity());
            preparedStatement.setString(3, inventory.getItemCategory().getCategoryId());
            preparedStatement.setString(4, inventory.getItemLocation().getLocationId());
            preparedStatement.setString(5, inv.getId());

            int rowsEffected = preparedStatement.executeUpdate();
            if (rowsEffected > 0){
                inventory.setId(inv.getId());
                inventory.setItemName(inv.getItemName());
                inventory.setItemQuantity(inv.getItemQuantity());
            }

        } finally {
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }
        return inventory;
    }
    @Override
    public boolean delete(String inventoryId) throws SQLException, ClassNotFoundException, ConnectionNotFoundException {
        Connection connection = null;
        try {
            connection = HikariConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_INVENTORY);
            preparedStatement.setString(1, inventoryId);
            int rowsEffected = preparedStatement.executeUpdate();
            return rowsEffected > 0;
        } finally {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }
}