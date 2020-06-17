package controllers;

import controllers.icontrollers.IUserController;
import dao.idao.IUserDAO;
import dao.UserDAO;
import dto.UserDTO;
import validation.InputValidation;

import javax.ws.rs.core.Response;
import java.sql.SQLException;


public class UserController implements IUserController {
    private static IUserController instance;

    static {
        try {
            instance = new UserController();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private final InputValidation validation;
    private final IUserDAO userDAO;

    private UserController() throws SQLException {
        this.userDAO = new UserDAO();
        this.validation = new InputValidation();
    }

    public static IUserController getInstance() {
        return instance;
    }

    @Override
    public Response addUser(UserDTO userDTO) {
        if (validation.addUserInputValidation(userDTO)) {
            try {
                userDAO.addUser(userDTO);
                return Response.ok().build();
            } catch (Exception e) {
                return Response.serverError().build();
            }
        } else {
            return Response.status(418, "Bad input").build();
        }
    }

    @Override
    public Response getAllUsers() {
        try {
            return Response.ok(userDAO.getAllUsers()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @Override
    public Response updateUser(int userId, String firstName, String lastName, String initials, String role, String active) throws Exception {
        if (firstName.equals("null") && lastName.equals("null") && initials.equals("null") && role.equals("null") && active.equals("null")) {
            return Response.ok(userDAO.getUser(String.valueOf(userId))).build();
        }

        UserDTO user = new UserDTO(userId, firstName, lastName, initials, role, true);
        if (!active.equals("null")) {
            user.setActive(active);
            user = userDAO.updateActivity(user);
            return Response.ok(user).build();
        }

        if (validation.updateUserInputValidation(user)) {
            try {
                return Response.ok(userDAO.updateUser(user)).build();
            } catch (Exception e) {
                e.printStackTrace();
                return Response.serverError().build();
            }
        } else {
            return Response.status(418, "Bad input").build();
        }
    }

    //used for testing purposes
    public Response deleteUser(int id) {
        try {
            userDAO.deleteUser(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @Override
    public Response getUser(String userId, String role) {

        try {

            if (validation.userValidation(userDAO.getUser(userId))) {
                return Response.ok(userDAO.getUser(userId)).build();
            } else {
                //System.out.println(role + userId + user.getRole());
                return Response.status(400, "bruger har ikke adgang").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}