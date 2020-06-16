package dao;

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
    private static IUserDAO instance;

    static {
        try {
            instance = new UserDAO();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private DBConnection database;

    public UserDAO() throws SQLException {
        database = new DBConnection();
    }

    public static IUserDAO getInstance() {
        return instance;
    }

    @Override
    public void addUser(UserDTO user) throws IOException, SQLException {

        String addUser = "{call addUser(?,?,?,?,?)}";
        PreparedStatement statement = database.callableStatement(addUser);

        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getInitials());
        statement.setString(4, user.getRole());
        statement.setInt(5, user.isActive() ? 1 : 0);

        try {
            statement.executeUpdate();
            System.out.println("User successfully added to database");
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Something went wrong with createUser()");
        }
    }

    @Override
    public ArrayList<UserDTO> getAllUsers() throws Exception {
        ArrayList<UserDTO> userList = new ArrayList<>();
        CallableStatement stmt = database.callableStatement("{call GetAllUsers}");
        ResultSet rs = stmt.executeQuery();
        UserDTO userDTO;
        try {
            while (rs.next()) {
                userDTO = new UserDTO();
                getUserInfo(rs, userDTO);
                userList.add(userDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    private void getUserInfo(ResultSet rs, UserDTO userDTO) throws SQLException {
        userDTO.setUserId(rs.getInt(1));
        userDTO.setFirstName(rs.getString(2));
        userDTO.setLastName(rs.getString(3));
        userDTO.setInitials(rs.getString(4));
        userDTO.setRole(rs.getString(5));
        userDTO.setActive(rs.getBoolean(6));
    }

    @Override
    public UserDTO getUser(String ID) throws Exception {
        CallableStatement stmt = database.callableStatement("{call GetUser(?)}");
        stmt.setString(1, ID);
        UserDTO user = new UserDTO();
        ResultSet resultSet = stmt.executeQuery();
        try {
            while (resultSet.next()) {
                getUserInfo(resultSet, user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public UserDTO getUserFromFirstNameLastName(String firstname, String lastname) throws Exception {
        CallableStatement stmt = database.callableStatement("{call getUserFromFirstNameLastName(?,?)}");
        stmt.setString(1, firstname);
        stmt.setString(2, lastname);

        UserDTO user = new UserDTO();
        ResultSet resultSet = stmt.executeQuery();
        try {
            while (resultSet.next()) {
                getUserInfo(resultSet, user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


    @Override
    public UserDTO updateUser(UserDTO user) throws Exception {
        CallableStatement stmt = database.callableStatement("{call UpdateUser(?,?,?,?,?,?)}");

        stmt.setInt(1, user.getUserId());
        stmt.setString(2, user.getFirstName());
        stmt.setString(3, user.getLastName());
        stmt.setString(4, user.getInitials());
        stmt.setString(5, user.getRole());
        stmt.setBoolean(6, user.getActive());

        ResultSet rs = stmt.executeQuery();

        try {
            while (rs.next()) {
                getUserInfo(rs, user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public UserDTO updateActivity(UserDTO user) throws Exception {
        CallableStatement stmt = database.callableStatement("{call UpdateActivity(?,?)}");

        stmt.setString(1, String.valueOf(user.getUserId()));
        stmt.setString(2, String.valueOf(user.isActive() ? 1 : 0));

        ResultSet rs = stmt.executeQuery();

        try {
            while (rs.next()) {
                getUserInfo(rs, user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    //for testing purposes
    public void deleteUser(int id) throws IOException, SQLException {

        String deleteUser = "{call DeleteUser(?)}";
        PreparedStatement statement = database.callableStatement(deleteUser);
        statement.setInt(1, id);

        try {
            statement.executeUpdate();
            System.out.println("User successfully deleted");
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("User could no be deleted");
        }
    }
}
