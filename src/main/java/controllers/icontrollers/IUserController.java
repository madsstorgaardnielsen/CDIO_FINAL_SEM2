package controllers.icontrollers;

import dto.UserDTO;

import javax.ws.rs.core.Response;

public interface IUserController {
    Response addUser(UserDTO userDTO) throws Exception;

    Response getAllUsers() throws Exception;

    Response updateUser(int userId, String firstName, String lastName, String initials, String role, String active) throws Exception;

    Response getUser(String userId, String role) throws Exception;

    public Response deleteUser(int id);
}
