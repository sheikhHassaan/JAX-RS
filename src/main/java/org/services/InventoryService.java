package org.services;

import org.common.ConnectionNotFoundException;
import org.domain.Category;
import org.domain.Inventory;
import org.domain.Location;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public interface InventoryService {

//  note: Dependency functionalities ↓

//    public boolean doesInventoryExist(UUID uuid) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
    public boolean doesCategoryExist(UUID cat_id, Connection connection) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
    public UUID doesCategoryExist(String cat_name, Connection connection) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
    public boolean doesLocationExist(UUID loc_id, Connection connection) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
    public UUID doesLocationExist(String loc_name, Connection connection) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
    public UUID getCategoryIdByName(String cat_name, Connection connection) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
    public UUID getLocationIdByName(String loc_name, Connection connection) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
    public Category addCategory(String cat_name, Connection connection) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
    public Location addLocation(String loc_name, Connection connection) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
    public boolean updateCategory(Category category, Connection connection) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
    public boolean updateLocation(Location location, Connection connection) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
    public Location addLocation(String loc_name) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
    public Category addCategory(String cat_name) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;


//  note: Core functionalities ↓
    public ArrayList<Inventory> fetchAll() throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
    public Inventory fetchByID(UUID uuid) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
    public ArrayList<Inventory> fetchAllByCategory(UUID uuid) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
    public ArrayList<Inventory> fetchAllByLocation(UUID uuid) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
    public ArrayList<Inventory> fetchAllByLocationAndCategory(UUID loc_id, UUID cat_id) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
    public Inventory addInventory(Inventory inventory) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
    public Inventory updateInventory(Inventory inventory) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
    public boolean delete(UUID inventory_id) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
}
