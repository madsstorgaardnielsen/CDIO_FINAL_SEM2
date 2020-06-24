package dao;

import dto.IngredientDTO;
import dto.UserDTO;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    @Test
    void addUser() {
        UserDTO test = new UserDTO();
        test.setFirstName("Test");
        test.setLastName("Test");
        test.setInitials("Test");
        test.setActive(false);
        test.setRole("Admin");

        UserDAO.getInstance().addUser(test);

        assertEquals("Test", UserDAO.getInstance().getUserFromFirstNameLastName("Test", "Test").getFirstName());

    }

    @Test
    void getAllUsers() {
        if (UserDAO.getInstance().getAllUsers().size() > 0) {
            assertTrue(true);
        } else {
            assertFalse(false);
        }
    }

    @Test
    void getUser() {
        assertEquals(1, UserDAO.getInstance().getUser("1").getUserId());
    }

    @Test
    void updateUser() {
        try {
            UserDTO test;
            test = UserDAO.getInstance().getUser("1");
            test.setFirstName("PoulOpdateres");

            UserDAO.getInstance().updateUser(test);

            assertEquals("PoulOpdateres", UserDAO.getInstance().getUser("1").getFirstName());
            test.setFirstName("Poul");
            UserDAO.getInstance().updateUser(test);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateActivity() {
        try {
            UserDTO test;
            test = UserDAO.getInstance().getUser("1");
            test.setActive(false);

            UserDAO.getInstance().updateUser(test);

            assertFalse(UserDAO.getInstance().getUser("1").getActive());
            test.setActive(true);
            ;
            UserDAO.getInstance().updateUser(test);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteUser() {

        UserDTO test = new UserDTO();
        test.setFirstName("Test");
        test.setLastName("Test");
        test.setInitials("Test");
        test.setActive(false);
        test.setRole("Admin");

        UserDAO.getInstance().addUser(test);

        assertEquals("Test", UserDAO.getInstance().getUserFromFirstNameLastName("Test", "Test").getFirstName());
        test = UserDAO.getInstance().getUserFromFirstNameLastName("Test", "Test");
        UserDAO.getInstance().deleteUser(test.getUserId());

    }
}