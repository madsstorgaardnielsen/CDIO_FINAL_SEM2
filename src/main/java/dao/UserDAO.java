package dao;

import dao.exceptions.DatabaseException;
import dao.idao.IUserDAO;
import db.DBConnection;
import dto.UserDTO;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO implements IUserDAO {
    private static final UserDAO instance;

    static {
        instance = new UserDAO();
    }

    private DBConnection database;

    public UserDAO() {
        database = new DBConnection();
    }

    public static IUserDAO getInstance() {
        return instance;
    }

    @Override
    public void addUser(UserDTO user) { //sends data for new user to the database to be saved
        String addUser = "{call addUser(?,?,?,?,?)}"; //the format string for the mysql command
        try {
            //use the format string to make a callable statement
            PreparedStatement statement = database.callableStatement(addUser);

            //insert the new data into the statement
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getInitials());
            statement.setString(4, user.getRole());
            statement.setInt(5, user.isActive() ? 1 : 0);

            statement.executeUpdate(); //call the command on the database
            System.out.println("User successfully added to database");
        } catch (SQLException e) { //thrown when database intercepts problems
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    @Override
    public ArrayList<UserDTO> getAllUsers() { //fetches data for all users in the database
        ArrayList<UserDTO> userList; //list for holding incoming userdata
        try {
            userList = new ArrayList<>(); //initialize list
            //make callable statement using format string
            CallableStatement stmt = database.callableStatement("{call GetAllUsers}");
            ResultSet rs = stmt.executeQuery(); //resultset for holding raw data from database
            UserDTO userDTO; //object for making user objects

            while (rs.next()) { //go though list of data until no more users in list
                userDTO = new UserDTO(); //make new user object
                getUserInfo(rs, userDTO); //get data from row in resultset
                userList.add(userDTO); //add user object to list of users
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException(); //throw exception if database intercepts problems
        }
        return userList; //return list of users
    }

    private void getUserInfo(ResultSet rs, UserDTO userDTO) {
        try {
            userDTO.setUserId(rs.getInt(1));
            userDTO.setFirstName(rs.getString(2));
            userDTO.setLastName(rs.getString(3));
            userDTO.setInitials(rs.getString(4));
            userDTO.setRole(rs.getString(5));
            userDTO.setActive(rs.getBoolean(6));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    @Override
    public UserDTO getUser(String ID) {
        UserDTO user;
        try {
            CallableStatement stmt = database.callableStatement("{call GetUser(?)}");
            stmt.setString(1, ID);
            user = new UserDTO();
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                getUserInfo(resultSet, user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
        return user;
    }

    public UserDTO getUserFromFirstNameLastName(String firstname, String lastname) {
        UserDTO user;
        try {
            CallableStatement stmt = database.callableStatement("{call getUserFromFirstNameLastName(?,?)}");
            stmt.setString(1, firstname);
            stmt.setString(2, lastname);
            user = new UserDTO();
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                getUserInfo(resultSet, user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
        return user;
    }

    @Override
    public UserDTO updateUser(UserDTO user) {
        try {
            CallableStatement stmt = database.callableStatement("{call UpdateUser(?,?,?,?,?,?)}");
            stmt.setInt(1, user.getUserId());
            stmt.setString(2, user.getFirstName());
            stmt.setString(3, user.getLastName());
            stmt.setString(4, user.getInitials());
            stmt.setString(5, user.getRole());
            stmt.setBoolean(6, user.getActive());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                getUserInfo(rs, user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
        return user;
    }

    @Override
    public UserDTO updateActivity(UserDTO user) {
        try {
            CallableStatement stmt = database.callableStatement("{call UpdateActivity(?,?)}");
            stmt.setString(1, String.valueOf(user.getUserId()));
            stmt.setString(2, String.valueOf(user.isActive() ? 1 : 0));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                getUserInfo(rs, user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
        return user;
    }

    //for testing purposes
    public void deleteUser(String name, String lastName) {
        try {
            String deleteUser = "DELETE FROM Users WHERE Firstname = ? AND Lastname = ?";
            PreparedStatement statement = database.callableStatement(deleteUser);
            statement.setString(1,name);
            statement.setString(2,lastName);
            statement.executeUpdate();
            System.out.println("User successfully deleted");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }
}
