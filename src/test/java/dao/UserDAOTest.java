package dao;

import dto.RecipeDTO;
import dto.UserDTO;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
/*
    @Test
    void addUser() throws SQLException {
        UserDAO userDAO = new UserDAO();
        UserDTO userDTO = new UserDTO();

        userDTO.setUserId(99999999);
        userDTO.setActive(true);
        userDTO.setFirstName("Test");
        userDTO.setLastName("Testsen");
        userDTO.setInitials("TT");
        userDTO.setRole("Admin");

        try {
            userDAO.addUser(userDTO);
            assertEquals(String.valueOf(99999999), userDAO.getUser("99999999").getUserId());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAllUsers() {
        RecipeDAO recipeDAO = new RecipeDAO();

        if (recipeDAO.getAllRecipes().size() > 1) {
            assertTrue(true);
        } else {
            assertFalse(false);
        }
    }

    @Test
    void getUser() {
        RecipeDAO recipeDAO = new RecipeDAO();
        RecipeDTO recipeDTO = new RecipeDTO();

        recipeDAO.deleteRecipe(99999999);
        recipeDTO.setRecipeID(99999999);
        recipeDTO.setRecipeName("TestRecipe");
        recipeDTO.setIngredientID(1);
        recipeDTO.setNonNetto(2.22);
        recipeDTO.setTolerance(3.33);

        try {
            recipeDAO.addRecipe(recipeDTO);
            assertEquals(99999999, recipeDAO.getRecipe(99999999).getRecipeID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateUser() {
        RecipeDAO recipeDAO = new RecipeDAO();
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDAO.deleteRecipe(99999999);
        recipeDTO.setRecipeID(99999999);
        recipeDTO.setRecipeName("TestRecipe");
        recipeDTO.setIngredientID(1);
        recipeDTO.setNonNetto(2.22);
        recipeDTO.setTolerance(3.33);
        try {
            recipeDAO.addRecipe(recipeDTO);
            assertEquals(99999999, recipeDAO.getRecipe(99999999).getRecipeID());
            recipeDAO.deleteRecipe(99999999);
            assertNotEquals(99999999,recipeDAO.getRecipe(99999999).getRecipeID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateActivity() {
        RecipeDAO recipeDAO = new RecipeDAO();
        RecipeDTO recipeDTO = new RecipeDTO();

        recipeDAO.deleteRecipe(99999999);
        recipeDTO.setRecipeID(99999999);
        recipeDTO.setRecipeName("TestRecipe");
        recipeDTO.setIngredientID(1);
        recipeDTO.setNonNetto(2.22);
        recipeDTO.setTolerance(3.33);

        try {
            recipeDAO.addRecipe(recipeDTO);
            assertEquals("TestRecipe", recipeDAO.getRecipe(99999999).getRecipeName());
            recipeDTO.setRecipeName("UpdatedName");
            recipeDAO.updateRecipe(recipeDTO);
            assertEquals("UpdatedName", recipeDAO.getRecipe(99999999).getRecipeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }*/
}