package Services;

import org.services.InventoryServiceImpl;
import org.common.ConnectionNotFoundException;
import org.common.HikariConnection;
import org.domain.Category;
import org.domain.Inventory;
import org.domain.Location;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceImplTest {

    InventoryServiceImpl service;
    Connection connection;

    InventoryServiceImplTest() throws ConnectionNotFoundException, SQLException, ClassNotFoundException {
         service = InventoryServiceImpl.getInstance();
         connection = HikariConnection.getPooledConnection();
    }

    @Test
    void getInstance() {
        assertNotNull(service.getInstance(), "Service object not received. Got null.");
    }

    @Test
    void doesCategoryExist() throws ConnectionNotFoundException, SQLException, ClassNotFoundException {
        assertFalse(service.doesCategoryExist("Test", connection) != null);
    }

    @Test
    void doesCategoryExistByID() throws ConnectionNotFoundException, SQLException, ClassNotFoundException {
        assertTrue(service.doesCategoryExist(UUID.fromString("d21c6419-d007-4a9a-a169-5b8e97fdac8b"), connection));
    }

    @Test
    void doesLocationExist() throws ConnectionNotFoundException, SQLException, ClassNotFoundException {
        assertTrue(service.doesLocationExist("Peshawar", connection) == null);
    }

    @Test
    void doesLocationExistByID() throws ConnectionNotFoundException, SQLException, ClassNotFoundException {
        assertTrue(service.doesLocationExist(UUID.fromString("5aeadcb0-97cb-4db4-8173-bf7fe2bf89cc"), connection));
    }

    @Test
    void getCategoryIdByName() throws ConnectionNotFoundException, SQLException, ClassNotFoundException {
        assertNotNull(service.getCategoryIdByName("Laptop", connection), "function getCategoryByName() returned null.");
    }

    @Test
    void getLocationIdByName() throws SQLException {
        assertNotNull(service.getLocationIdByName("Karachi", connection), "function getLocationIdByName() returned null.");
    }

    @Test
    void addCategory() throws ConnectionNotFoundException, SQLException, ClassNotFoundException {
        assertNotNull(service.addCategory("TestCat", connection), "function getLocationIdByName() returned null.");
    }

    @Test
    void addLocation() throws ConnectionNotFoundException, SQLException, ClassNotFoundException {
        assertNotNull(service.addLocation("Quetta", connection), "function getLocationIdByName() returned null.");
    }

    @Test
    void updateCategory() throws SQLException, ConnectionNotFoundException, ClassNotFoundException {
        assertNotNull(service.updateCategory(new Category("Tracker"), connection), "function getLocationIdByName() returned null.");
    }

    @Test
    void updateLocation() throws ConnectionNotFoundException, SQLException, ClassNotFoundException {
        assertNotNull(service.updateLocation(new Location("Islamabad"), connection), "function getLocationIdByName() returned null.");
    }

    @Test
    void fetchAll() throws ConnectionNotFoundException, SQLException, ClassNotFoundException {
        assertNotNull(service.fetchAll(), "The function fetchAll() returned null.");
    }

    @Test
    void fetchByID() throws ConnectionNotFoundException, SQLException, ClassNotFoundException {
        assertNotNull(service.fetchByID(UUID.fromString("7e72f51d-11c6-4499-a7f4-f980d03e837d")), "The function fetchByID() returned null.");
    }

    @Test
    void fetchAllByCategory() throws ConnectionNotFoundException, SQLException, ClassNotFoundException {
        assertNotNull(service.fetchAllByCategory(UUID.fromString("d21c6419-d007-4a9a-a169-5b8e97fdac8b")), "The function fetchAllByCategory() returned null.");
    }

    @Test
    void fetchAllByLocation() throws ConnectionNotFoundException, SQLException, ClassNotFoundException {
        assertNotNull(service.fetchAllByLocation(UUID.fromString("5aeadcb0-97cb-4db4-8173-bf7fe2bf89cc")), "The function fetchAllByLocation() returned null.");
    }

    @Test
    void fetchAllByLocationAndCategory() throws ConnectionNotFoundException, SQLException, ClassNotFoundException {
        assertNotNull(service.fetchAllByLocationAndCategory(UUID.fromString("5aeadcb0-97cb-4db4-8173-bf7fe2bf89cc"), UUID.fromString("d21c6419-d007-4a9a-a169-5b8e97fdac8b")), "The function fetchAllByLocation() returned null.");

    }

    @Test
    void addInventory() throws ConnectionNotFoundException, SQLException, ClassNotFoundException {
        assertNotNull(service.addInventory(new Inventory(UUID.randomUUID(), "TestProduct", 1, new Category(UUID.fromString("3dac5d7b-2247-49b8-a80e-15c8a80830e1"), "Phone"), new Location(UUID.fromString("5aeadcb0-97cb-4db4-8173-bf7fe2bf89cc"), "Lahore")))
                , "The function addInventory() returned null.");
    }

    @Test
    void updateInventory() throws ConnectionNotFoundException, SQLException, ClassNotFoundException {
        assertNotNull(service.updateInventory(new Inventory(UUID.fromString("b43ef010-8331-4f0e-a59b-4f72946432b9"), "TestProduct", 11, new Category(UUID.fromString("3dac5d7b-2247-49b8-a80e-15c8a80830e1"), "Phone"), new Location(UUID.fromString("5aeadcb0-97cb-4db4-8173-bf7fe2bf89cc"), "Lahore")))
                , "The function updateInventory() returned null.");
    }

    @Test
    void delete() throws ConnectionNotFoundException, SQLException, ClassNotFoundException {
        assertFalse(service.delete(UUID.fromString("d954b875-cd56-4664-b28b-d20fb11eb40b")));
    }
}