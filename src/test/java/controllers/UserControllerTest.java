package controllers;

import dao.UserDAO;
import dto.UserDTO;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @Test
    void addUser() {
        UserDTO test = new UserDTO("test", "test", "test", "Admin", false);
        assertEquals(Response.ok().build().toString(), UserController.getInstance().addUser(test).toString());
        UserController.getInstance().deleteUser("test","test");
    }

    @Test
    void getAllUsers() {
        assertEquals(Response.ok(true).build().toString(), UserController.getInstance().getAllUsers().toString());
    }

    @Test
    void updateUser() {
        UserController.getInstance().updateUser(1, "Pernille", "Rosenkrantz", "PR", "Admin", "false");
        assertEquals(Response.ok(true).build().toString(), UserController.getInstance().updateUser(1, "Pernille", "Rosenkrantz", "PR", "Admin", "false").toString());
        UserController.getInstance().updateUser(1, "test", "test", "test", "Admin", "true");
    }

    @Test
    void getUser() {
        assertEquals(Response.ok(true).build().toString(), UserController.getInstance().getUser("1","Admin").toString());
    }
}