package dao.idao;

import dto.UserDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IUserDAO {
    void addUser(UserDTO user) throws IOException, SQLException;

    ArrayList<UserDTO> getAllUsers() throws Exception;

    UserDTO getUser(String ID) throws Exception;

    UserDTO updateUser(UserDTO user) throws Exception;

    UserDTO updateActivity(UserDTO user) throws Exception;
}
