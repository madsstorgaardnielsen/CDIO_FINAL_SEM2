package db;

import controllers.IngredientBatchController;
import dao.exceptions.DatabaseConnectionException;
import dao.exceptions.DatabaseException;

import java.io.IOException;
import java.sql.*;

public class DBConnection {

    private Connection connection;

    public DBConnection()  {
            String host = "cdiofinalsem2.cibykspwyepg.us-east-2.rds.amazonaws.com";
            String port = "3306";
            String database = "cdio_final";
            String username = "admin";
            String password = "gruppe22";
            String jdbcUrl = "jdbc:mysql://" + host + ":" + port + "/" + database + "?user=" + username + "&password=" + password;

        try {
            connection = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseConnectionException();
        }
    }

    public void closeDBConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    public PreparedStatement prepareStatement(String sql) {
        try {
            return connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    public CallableStatement callableStatement(String sql) {
        try {
            return connection.prepareCall(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    public void finalize()  {
        closeDBConnection();
        try {
            super.finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
