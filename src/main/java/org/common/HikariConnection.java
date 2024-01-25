package org.common;

import java.sql.Connection;
import java.sql.SQLException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import static org.common.EnvironmentVariables.*;

public class HikariConnection {
    private static HikariDataSource dataSource;
    public static Connection connection;

    private HikariConnection(){}

    public static void initializeDataSource(){

        synchronized (HikariConnection.class){

            if (HikariConnection.dataSource == null) {
                HikariConfig config = new HikariConfig();
                config.setJdbcUrl(DB_URL);
                config.setUsername(DB_USER);
                config.setPassword(DB_PASSWORD);
                config.setMaximumPoolSize(25);

                HikariConnection.dataSource = new HikariDataSource(config);
            }
        }
    }
    public static Connection getConnection() throws SQLException, ConnectionNotFoundException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        HikariConnection.initializeDataSource();

        connection = dataSource.getConnection();

        if (connection == null) { throw new ConnectionNotFoundException(); }

        return connection;
    }
}