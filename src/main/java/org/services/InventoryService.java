package org.services;

import org.domain.Category;
import org.domain.Location;
import java.sql.Connection;
import java.util.ArrayList;
import org.domain.Inventory;
import java.sql.SQLException;
import org.common.ConnectionNotFoundException;
import javassist.tools.rmi.ObjectNotFoundException;

public interface InventoryService {

//  note: Dependency functionalities ↓
    boolean isCategoryExist(String catName) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
    boolean isLocationExist(String locName) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
    boolean isInventoryExist(String inventoryId) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
    Category getCategory(String catName) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
    Location getLocation(String locName) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
    Category addCategory(String catName, Connection connection) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
    Location addLocation(String locName, Connection connection) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;


//  note: Core functionalities ↓
    ArrayList<Inventory> fetchAll() throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
    Inventory fetchByID(String id) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
    ArrayList<Inventory> fetchAllByCategory(String id) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
    ArrayList<Inventory> fetchAllByLocation(String id) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
    ArrayList<Inventory> fetchAllByLocationAndCategory(String locId, String catId) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
    Inventory addInventory(Inventory inventory) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
    Inventory updateInventory(Inventory inventory) throws SQLException, ClassNotFoundException, ConnectionNotFoundException, ObjectNotFoundException;
    boolean delete(String inventoryId) throws SQLException, ClassNotFoundException, ConnectionNotFoundException;
}