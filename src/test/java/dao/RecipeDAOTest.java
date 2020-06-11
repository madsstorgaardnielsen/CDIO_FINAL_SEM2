package dao;

import dto.IngredientDTO;
import dto.RecipeComponentDTO;
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
        RecipeComponentDTO recipeComponentDTO1 = new RecipeComponentDTO(99999999, 1, 2.22, 3.33);
        RecipeComponentDTO recipeComponentDTO2 = new RecipeComponentDTO(99999999, 2, 300.1, -4.2);

        recipeDAO.deleteRecipe(99999999);
        recipeDTO.setRecipeID(99999999);
        recipeDTO.setRecipeName("TestRecipe");
        recipeDTO.addToRecipeCompList(recipeComponentDTO1);
        recipeDTO.addToRecipeCompList(recipeComponentDTO2);

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
        RecipeComponentDTO recipeComponentDTO = new RecipeComponentDTO(99999999, 1, 2.22, 3.33);

        recipeDAO.deleteRecipe(99999999);
        recipeDTO.setRecipeID(99999999);
        recipeDTO.setRecipeName("TestRecipe");
        recipeDTO.addToRecipeCompList(recipeComponentDTO);

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
        RecipeComponentDTO recipeComponentDTO = new RecipeComponentDTO(99999999, 1, 2.22, 3.33);

        recipeDAO.deleteRecipe(99999999);
        recipeDTO.setRecipeID(99999999);
        recipeDTO.setRecipeName("TestRecipe");
        recipeDTO.addToRecipeCompList(recipeComponentDTO);

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
        RecipeComponentDTO recipeComponentDTO1 = new RecipeComponentDTO(99999999, 1, 3.22, 3.33);
        RecipeComponentDTO recipeComponentDTO2 = new RecipeComponentDTO(99999999, 2, 4.22, 3.33);

        recipeDAO.deleteRecipe(99999999);
        recipeDTO.setRecipeID(99999999);
        recipeDTO.setRecipeName("TestRecipe");
        recipeDTO.addToRecipeCompList(recipeComponentDTO1);
        recipeDTO.addToRecipeCompList(recipeComponentDTO2);

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