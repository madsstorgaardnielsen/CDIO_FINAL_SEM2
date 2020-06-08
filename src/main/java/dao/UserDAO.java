package dao;

import db.DBConnection;
import dto.UserDTO;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    private static UserDAO instance;
    static {
        try {
            instance = new UserDAO();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    PreparedStatement statement;
    private DBConnection database;

    public UserDAO() throws SQLException {
        database = new DBConnection();
    }

    public static UserDAO getInstance() {
        return instance;
    }

    public void addUser(UserDTO user) throws IOException, SQLException {

        String addUser = "{call addUser(?,?,?,?,?,?)}";
        PreparedStatement statement = database.callableStatement(addUser);

        statement.setString(1,user.getFirstName());
        statement.setString(2,user.getLastName());
        statement.setString(3,user.getInitials());
        statement.setString(4,user.getRole());



        try {
            statement.executeUpdate();
            System.out.println("User successfully added to database");
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Something went wrong with createUser()");
        }
    }
}
