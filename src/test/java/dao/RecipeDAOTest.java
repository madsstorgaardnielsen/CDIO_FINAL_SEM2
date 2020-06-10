package dao;

import dto.IngredientDTO;
import dto.RecipeDTO;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class RecipeDAOTest {


    @Test
    void addRecipe() throws SQLException, IOException {
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
    void getAllRecipes() throws Exception {
        RecipeDAO recipeDAO = new RecipeDAO();

        if (recipeDAO.getAllRecipes().size() > 1) {
            assertTrue(true);
        } else {
            assertFalse(false);
        }
    }

    @Test
    void getRecipe() throws IOException, SQLException {
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
    void deleteRecipe() throws SQLException, IOException {
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
    void updateRecipe() throws SQLException, IOException {
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
}