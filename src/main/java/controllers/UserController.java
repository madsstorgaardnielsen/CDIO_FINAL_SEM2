package controllers;

import controllers.icontrollers.IUserController;
import dao.idao.IUserDAO;
import dao.UserDAO;
import dto.UserDTO;
import resources.InputValidation;

import javax.ws.rs.core.Response;

/**
 * The controller class for "Brugere", it takes input from the API class,
 * validates input and passes the data to the corresponding DAO class, when the DAO class saved/deleted/updated the data,
 * the controller class returns a Response to the corresponding API.
 */
public class UserController implements IUserController {
    private static final IUserController instance;

    static {
        instance = new UserController();
    }

    private final InputValidation validation;
    private final IUserDAO userDAO;

    private UserController() {
        this.userDAO = new UserDAO();
        this.validation = new InputValidation();
    }

    public static IUserController getInstance() {
        return instance;
    }

    @Override
    public Response addUser(UserDTO userDTO) { //validates input values and sends data to be stored
        if (validation.addUserInputValidation(userDTO)) { //validation of data
            try {
                userDAO.addUser(userDTO); //send data to database handler
                return Response.ok().build(); //response ok when things go good
            } catch (Exception e) {
                return Response.serverError().build(); //response server error if things go wrong server side
            }
        } else {
            //response bad input when input data dont match restrictions
            return Response.status(418, "Forkert input<br> Indtast venligst kun bogstaver").build();
        }
    }

    @Override
    public Response getAllUsers() { //fetches data for all users in the database
        try {
            return Response.ok(userDAO.getAllUsers()).build(); //fetch users and include in response as data
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build(); //response server error if things go wrong server side
        }
    }

    @Override
    public Response updateUser(int userId, String firstName, String lastName, String initials, String role, String active) {
        UserDTO user = new UserDTO(userId, firstName, lastName, initials, role, true);
        if (!active.equals("null")) {
            user.setActive(active);
            try {
                user = userDAO.updateActivity(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
            if (!validation.idValidation(userId)) {
                return Response.status(418, "Forkert input<br> Indtastet bruger id: " + userId + "<br> Id skal ligge i intervallet 1-99999999").build();
            } else if (!validation.nameValidation(firstName) || !validation.nameValidation(lastName)) {
                return Response.status(418, "Forkert input<br> Indtastet navn: " + firstName + " " + lastName + "<br> Indtast venligst kun bogstaver").build();
            } else
                return Response.status(418, "Forkert input<br> Indtastet initialer: " + initials + "<br> Indtast venligst kun bogstaver").build();
        }
    }

    //used for testing purposes
    public Response deleteUser(String name, String lastName) {
        try {
            userDAO.deleteUser(name, lastName);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @Override
    public Response getUser(String userId, String role) {
        try {
            if (validation.userValidation(userDAO.getUser(userId), role)) {
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