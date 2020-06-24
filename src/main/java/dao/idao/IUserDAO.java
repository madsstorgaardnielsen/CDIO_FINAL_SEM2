package dao.idao;

import dto.UserDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IUserDAO {
    void addUser(UserDTO user);

    ArrayList<UserDTO> getAllUsers();

    UserDTO getUser(String ID);

    UserDTO updateUser(UserDTO user);

    UserDTO updateActivity(UserDTO user);

    void deleteUser(int id);

    UserDTO getUserFromFirstNameLastName(String firstname, String lastname);
}
