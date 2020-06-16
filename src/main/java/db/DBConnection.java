package db;

import java.io.IOException;
import java.sql.*;

public class DBConnection {
    private Connection connection;

    public DBConnection() throws SQLException {
            String host = "cdiofinalsem2.cibykspwyepg.us-east-2.rds.amazonaws.com";
            String port = "3306";
            String database = "cdio_final";
            String username = "admin";
            String password = "gruppe22";
            String jdbcUrl = "jdbc:mysql://" + host + ":" + port + "/" + database + "?user=" + username + "&password=" + password;

            connection = DriverManager.getConnection(jdbcUrl);
    }

    public void closeDBConnection() throws IOException {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException("Something went wrong with closeDBConnection()");
        }
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    public CallableStatement callableStatement(String sql) throws SQLException {
        return connection.prepareCall(sql);
    }

    public void finalize() throws Throwable {
        closeDBConnection();
        super.finalize();
    }
}
