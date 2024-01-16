package Services;

import Common.HikariConnection;
import Common.ConnectionNotFoundException;
import Domain.Inventory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

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
    public boolean doesExist(UUID uuid) throws ConnectionNotFoundException, SQLException, ClassNotFoundException {

        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM INVENTORY.INVENTORY WHERE ID = ?";
            connection = HikariConnection.getPooledConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
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




//    public boolean isExist(UUID item_id) throws SQLException, ConnectionNotFoundException, ClassNotFoundException {
//
//    }
//
//    public boolean isExist(String item_name) throws SQLException, ConnectionNotFoundException, ClassNotFoundException {
//
//    }
//
//    public boolean addInventory(Inventory inventory) throws SQLException, ConnectionNotFoundException, ClassNotFoundException {
//
//    }
//
//    public int updateInventory(Employee employee) throws SQLException, ConnectionNotFoundException, ClassNotFoundException {
//
//    }

}
