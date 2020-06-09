package controllers;

import dao.UserDAO;
import dto.UserDTO;
import validation.InputValidation;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.sql.SQLException;


public class UserController {
    private final InputValidation validation;
    private final UserDAO userDAO;
    private static UserController instance;

    static {
        try {
            instance = new UserController();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private UserController() throws SQLException {
        this.userDAO = new UserDAO();
        this.validation = new InputValidation();
    }

    public static UserController getInstance()
    {
        return instance;
    }

    public Response addUser(UserDTO userDTO) throws Exception {
        try {
            userDAO.addUser(userDTO);
            return Response.ok().build();
        } catch (Exception e){
            return Response.serverError().build();
        }
    }

    public Response getAllUsers(UriInfo uriInfo) throws Exception {
        if (uriInfo.getQueryParameters().isEmpty()) {
            try {
                return Response.ok(userDAO.getAllUsers()).build();
            } catch (Exception e) {
                e.printStackTrace();
                return Response.serverError().build();
            }
        } else {
            try {
                MultivaluedMap<String, String> params = uriInfo.getQueryParameters();
                UserDTO user = userDAO.getUser(params.getFirst("userId"));
                if (validation.userValidation(user, params.getFirst("role")))
                    return Response.ok(user).build();
                else
                    return Response.status(400, "bruger har ikke adgang").build();
            } catch (Exception e) {
                e.printStackTrace();
                return Response.serverError().build();
            }
        }
    }

    public Response updateUser(UriInfo uriInfo) throws Exception {
        if (uriInfo.getQueryParameters().keySet().size() == 1) {
            return Response.status(200, "No data changed").build();
        } else {
            MultivaluedMap<String,String> params = uriInfo.getQueryParameters();
            UserDTO user = new UserDTO();

            user.setUserId(Integer.parseInt(params.getFirst("userId")));

            if (params.getFirst("active") != null) {
                user.setActive(params.getFirst("active"));
                user = userDAO.updateActivity(user);
                return Response.ok(user).build();
            }

            if (params.getFirst("firstName") == null)
                user.setFirstName(null);
            else
                user.setFirstName(params.getFirst("firstName"));

            if (params.getFirst("lastName") == null)
                user.setLastName(null);
            else
                user.setLastName(params.getFirst("lastName"));

            if (params.getFirst("initials") == null)
                user.setInitials(null);
            else
                user.setInitials(params.getFirst("initials"));

            if (params.getFirst("role") == null)
                user.setRole(null);
            else
                user.setRole(params.getFirst("role"));

            try {
                user = userDAO.updateUser(user);
                System.out.println("1");
                return Response.ok(user).build();
            } catch (Exception e) {
                e.printStackTrace();
                return Response.serverError().build();
            }
        }
    }
}