package org.common;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class HikariConnection {
    private static HikariDataSource dataSource;
    public static Connection connection;
    private static final Object LOCK = new Object();

    private HikariConnection(){}

    public static Connection getPooledConnection() throws SQLException, ConnectionNotFoundException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        synchronized (LOCK){
            if (HikariConnection.dataSource == null) {
                HikariConfig config = new HikariConfig();
                config.setJdbcUrl(System.getenv("DB_URL"));
                config.setUsername(System.getenv("DB_USER"));
                config.setPassword(System.getenv("DB_PASSWORD"));
                config.setMaximumPoolSize(15);

                HikariConnection.dataSource = new HikariDataSource(config);
            }
        }

        connection = dataSource.getConnection();

        if (connection == null) {
            throw new ConnectionNotFoundException();
        }
        return connection;
    }
}