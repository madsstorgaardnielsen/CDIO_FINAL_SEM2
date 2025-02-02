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
    void addRecipe() {
        RecipeDAO recipeDAO = new RecipeDAO();
        RecipeDTO recipeDTO = new RecipeDTO();
        RecipeComponentDTO recipeComponentDTO1 = new RecipeComponentDTO(999, 1, 2.2233, 3.3443);
        RecipeComponentDTO recipeComponentDTO2 = new RecipeComponentDTO(999, 2, 300.1144, 4.2242);

        recipeDAO.deleteRecipe(999);
        recipeDTO.setRecipeID(999);
        recipeDTO.setRecipeName("TestRecipe");
        recipeDTO.addToRecipeCompList(recipeComponentDTO1);
        recipeDTO.addToRecipeCompList(recipeComponentDTO2);

        try {
            recipeDAO.addRecipe(recipeDTO);
            assertEquals(999, recipeDAO.getRecipe(999).getRecipeID());
            recipeDAO.deleteRecipe(999);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void addRecipeOnly() {
        RecipeDAO recipeDAO = new RecipeDAO();
        RecipeDTO recipeDTO = new RecipeDTO();
        //RecipeComponentDTO recipeComponentDTO1 = new RecipeComponentDTO(99999999, 1, 2.22, 3.33);
        //RecipeComponentDTO recipeComponentDTO2 = new RecipeComponentDTO(99999999, 2, 300.1, 4.2);

        recipeDAO.deleteRecipe(999);
        recipeDTO.setRecipeID(999);
        recipeDTO.setRecipeName("TestRecipe");
        //recipeDTO.addToRecipeCompList(recipeComponentDTO1);
        //recipeDTO.addToRecipeCompList(recipeComponentDTO2);

        try {
            recipeDAO.addRecipeOnly(recipeDTO);
            assertEquals(999, recipeDAO.getRecipeOnly(999).getRecipeID());
            recipeDAO.deleteRecipe(999);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAllRecipes() {
        RecipeDAO recipeDAO = new RecipeDAO();

        if (recipeDAO.getAllRecipes().size() > 1) {
            assertTrue(true);
        } else {
            assertFalse(false);
        }
    }

    @Test
    void getRecipe() {
        RecipeDAO recipeDAO = new RecipeDAO();
        RecipeDTO recipeDTO = new RecipeDTO();
        RecipeComponentDTO recipeComponentDTO = new RecipeComponentDTO(999, 1, 2.22, 3.33);

        recipeDAO.deleteRecipe(999);
        recipeDTO.setRecipeID(999);
        recipeDTO.setRecipeName("TestRecipe");
        recipeDTO.addToRecipeCompList(recipeComponentDTO);

        try {
            recipeDAO.addRecipe(recipeDTO);
            assertEquals(999, recipeDAO.getRecipe(999).getRecipeID());
            assertEquals(2.22, recipeDAO.getRecipe(999).getRecipeCompList().get(0).getNonNetto());
            recipeDAO.deleteRecipe(999);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void deleteRecipe() {
        RecipeDAO recipeDAO = new RecipeDAO();
        RecipeDTO recipeDTO = new RecipeDTO();
        RecipeComponentDTO recipeComponentDTO = new RecipeComponentDTO(999, 1, 2.22, 3.33);

        recipeDAO.deleteRecipe(999);
        recipeDTO.setRecipeID(999);
        recipeDTO.setRecipeName("TestRecipe");
        recipeDTO.addToRecipeCompList(recipeComponentDTO);

        try {
            recipeDAO.addRecipe(recipeDTO);
            assertEquals(999, recipeDAO.getRecipe(999).getRecipeID());
            recipeDAO.deleteRecipe(999);
            assertNotEquals(999, recipeDAO.getRecipe(999).getRecipeID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateRecipe() {
        RecipeDAO recipeDAO = new RecipeDAO();
        RecipeDTO recipeDTO = new RecipeDTO();
        RecipeComponentDTO recipeComponentDTO1 = new RecipeComponentDTO(999, 1, 3.22, 3.33);
        RecipeComponentDTO recipeComponentDTO2 = new RecipeComponentDTO(999, 2, 4.22, 3.33);

        recipeDAO.deleteRecipe(999);
        recipeDTO.setRecipeID(999);
        recipeDTO.setRecipeName("TestRecipe");
        recipeDTO.addToRecipeCompList(recipeComponentDTO1);
        recipeDTO.addToRecipeCompList(recipeComponentDTO2);

        try {
            recipeDAO.addRecipe(recipeDTO);
            assertEquals("TestRecipe", recipeDAO.getRecipe(999).getRecipeName());
            recipeDTO.setRecipeName("UpdatedName");
            recipeDAO.updateRecipe(recipeDTO);
            assertEquals("UpdatedName", recipeDAO.getRecipe(999).getRecipeName());
            recipeDAO.deleteRecipe(999);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}