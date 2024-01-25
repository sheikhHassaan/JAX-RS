package Services;

import java.util.UUID;
import java.sql.Connection;
import org.domain.Location;
import org.domain.Category;
import org.domain.Inventory;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import org.common.HikariConnection;
import org.services.InventoryServiceImpl;
import org.common.ConnectionNotFoundException;
import static org.junit.jupiter.api.Assertions.*;
import javassist.tools.rmi.ObjectNotFoundException;

class InventoryServiceImplTest {

    InventoryServiceImpl service;
    Connection connection;

    InventoryServiceImplTest() throws ConnectionNotFoundException, SQLException, ClassNotFoundException {
         service = InventoryServiceImpl.getInstance();
         connection = HikariConnection.getConnection();
    }

    @Test
    void getInstance() {
        assertNotNull(InventoryServiceImpl.getInstance(), "Service object not received. Got null.");
    }

    @Test
    void isCategoryExist() throws ConnectionNotFoundException, SQLException, ClassNotFoundException {
        assertFalse(service.isCategoryExist("Test"));
    }

    @Test
    void isLocationExist() throws ConnectionNotFoundException, SQLException, ClassNotFoundException {
        assertFalse(service.isLocationExist("Peshawar"));
    }

    @Test
    void getCategory() throws ConnectionNotFoundException, SQLException, ClassNotFoundException {
        assertNotNull(service.getCategory("Laptop"), "function getCategoryByName() returned null.");
    }

    @Test
    void getLocation() throws SQLException, ConnectionNotFoundException, ClassNotFoundException {
        assertNotNull(service.getLocation("Karachi"), "function getLocationIdByName() returned null.");
    }

    @Test
    void addCategory() throws SQLException {
        assertNotNull(service.addCategory("TestCat", connection), "function getLocationIdByName() returned null.");
    }

    @Test
    void addLocation() throws SQLException {
        assertNotNull(service.addLocation("Quetta", connection), "function getLocationIdByName() returned null.");
    }

    @Test
    void fetchAll() throws ConnectionNotFoundException, SQLException, ClassNotFoundException {
        assertNotNull(service.fetchAll(), "The function fetchAll() returned null.");
    }

    @Test
    void fetchByID() throws ConnectionNotFoundException, SQLException, ClassNotFoundException {
        assertNotNull(service.fetchByID("7e72f51d-11c6-4499-a7f4-f980d03e837d"), "The function fetchByID() returned null.");
    }

    @Test
    void fetchAllByCategory() throws ConnectionNotFoundException, SQLException, ClassNotFoundException {
        assertNotNull(service.fetchAllByCategory("d21c6419-d007-4a9a-a169-5b8e97fdac8b"), "The function fetchAllByCategory() returned null.");
    }

    @Test
    void fetchAllByLocation() throws ConnectionNotFoundException, SQLException, ClassNotFoundException {
        assertNotNull(service.fetchAllByLocation("5aeadcb0-97cb-4db4-8173-bf7fe2bf89cc"), "The function fetchAllByLocation() returned null.");
    }

    @Test
    void fetchAllByLocationAndCategory() throws ConnectionNotFoundException, SQLException, ClassNotFoundException {
        assertNotNull(service.fetchAllByLocationAndCategory("5aeadcb0-97cb-4db4-8173-bf7fe2bf89cc", "d21c6419-d007-4a9a-a169-5b8e97fdac8b"), "The function fetchAllByLocation() returned null.");

    }

    @Test
    void addInventory() throws ConnectionNotFoundException, SQLException, ClassNotFoundException {
        assertNotNull(service.addInventory(new Inventory(String.valueOf(UUID.randomUUID()), "TestProduct", 1, new Category("3dac5d7b-2247-49b8-a80e-15c8a80830e1", "Phone"), new Location("5aeadcb0-97cb-4db4-8173-bf7fe2bf89cc", "Lahore")))
                , "The function addInventory() returned null.");
    }

    @Test
    void updateInventory() throws ConnectionNotFoundException, SQLException, ClassNotFoundException, ObjectNotFoundException {
        assertNotNull(service.updateInventory(new Inventory("b43ef010-8331-4f0e-a59b-4f72946432b9", "TestProduct", 11, new Category("3dac5d7b-2247-49b8-a80e-15c8a80830e1", "Phone"), new Location("5aeadcb0-97cb-4db4-8173-bf7fe2bf89cc", "Lahore")))
                , "The function updateInventory() returned null.");
    }

    @Test
    void delete() throws ConnectionNotFoundException, SQLException, ClassNotFoundException {
        assertFalse(service.delete("d954b875-cd56-4664-b28b-d20fb11eb40b"));
    }
}