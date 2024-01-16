package Common;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class HikariConnection {
    public static Connection connection;
    public static HikariDataSource dataSource;
    public static HikariConfig config;

    public static Connection getPooledConnection() throws SQLException, ConnectionNotFoundException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://localhost:3306/Inventory");
        config.setUsername("root");
        config.setPassword("Helloworld");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);

        if (dataSource == null) {
            dataSource = new HikariDataSource(config);
        }
        connection = dataSource.getConnection();

        if (connection == null) {
            throw new ConnectionNotFoundException();
        }
        return connection;

    }
    public static void close() throws SQLException {
        if (connection != null && !connection.isClosed()){
            connection.close();
        }
        if (dataSource != null && !dataSource.isClosed()){
            dataSource.close();
        }
    }
}