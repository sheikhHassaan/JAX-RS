package Services;

import Common.ConnectionNotFoundException;

import java.sql.SQLException;
import java.util.UUID;

public interface InventoryService {

    public boolean doesExist(UUID uuid) throws ConnectionNotFoundException, SQLException, ClassNotFoundException;

}
