package controllers;

import dao.UserDAO;
import dto.UserDTO;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @Test
    void addUser() {
        UserDTO test = new UserDTO(99999999, "test", "test", "test", "Admin", false);
        assertEquals(Response.ok(true).build().toString(), UserController.getInstance().addUser(test).toString());
        UserController.getInstance().deleteUser(99999999);
    }

    @Test
    void getAllUsers() {
        assertEquals(Response.ok(true).build().toString(), UserController.getInstance().getAllUsers().toString());
    }

    @Test
    void updateUser() {

        UserController.getInstance().updateUser(99999999, "test", "test", "test", "Admin", "true");

        assertEquals(Response.ok(true).build().toString(), UserController.getInstance().updateUser(99999999, "test", "test", "test", "Admin", "true").toString());

        UserController.getInstance().updateUser(99999999, "test", "test", "test", "Admin", "false");
    }

    @Test
    void getUser() {
        assertEquals(Response.ok(true).build().toString(), UserController.getInstance().updateUser(99999999, "test", "test", "test", "Admin", "false").toString());
    }
}