package controllers;

import dao.IngredientBatchDAO;
import dao.IngredientDAO;
import dao.RecipeComponentDAO;
import dao.RecipeDAO;
import dto.IngredientBatchDTO;
import dto.IngredientDTO;
import dto.RecipeComponentDTO;
import dto.RecipeDTO;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class RecipeComponentControllerTest {

    @Test
    void deleteRecipeComponent() {
        RecipeDTO rdto = new RecipeDTO(999, "TestRecipe");
        RecipeDAO.getInstance().addRecipe(rdto);
        RecipeComponentDTO ibdto = new RecipeComponentDTO(999, 1, 99.9999, 99.9999);
        assertEquals(Response.ok(RecipeComponentController.getInstance().getRecipeComponent(999, 1)).build().toString(), RecipeComponentController.getInstance().addRecipeComponent(ibdto).toString());
        RecipeComponentController.getInstance().deleteRecipeComponent(999, 1);
        assertEquals(Response.ok(RecipeComponentController.getInstance().getRecipeComponent(999, 1)).build().toString(), RecipeComponentController.getInstance().getRecipeComponent(999, 1).toString());
        RecipeDAO.getInstance().deleteRecipe(999);
    }

    @Test
    void addRecipeComponent() {
        RecipeDTO rdto = new RecipeDTO(999, "TestRecipe");
        RecipeDAO.getInstance().addRecipe(rdto);
        RecipeComponentDTO rcdto = new RecipeComponentDTO(999, 1, 99.9999, 99.9999);
        assertEquals(Response.ok(RecipeComponentController.getInstance().getRecipeComponent(999, 1)).build().toString(), RecipeComponentController.getInstance().addRecipeComponent(rcdto).toString());
        RecipeComponentController.getInstance().deleteRecipeComponent(999, 1);
        RecipeDAO.getInstance().deleteRecipe(999);
    }

    @Test
    void updateRecipeComponent() {
        RecipeDTO rdto = new RecipeDTO(999, "TestRecipe");
        RecipeDAO rdao = new RecipeDAO();
        rdao.addRecipe(rdto);

        RecipeComponentDTO rcdto = new RecipeComponentDTO(999, 1, 99.9999, 99.9999);
        RecipeComponentDAO rcdao = new RecipeComponentDAO();

        rcdao.addRecipeComponent(rcdto);
        assertEquals(Response.ok(RecipeComponentController.getInstance().getRecipeComponent(999, 1)).build().toString(), RecipeComponentController.getInstance().updateRecipeComponent(999, 1, 999.9999, 99.9999).toString());
        RecipeComponentController.getInstance().deleteRecipeComponent(999, 1);
        rdao.deleteRecipe(999);
    }

    @Test
    void getAllRecipeComponents() {
        assertEquals(Response.ok(RecipeComponentController.getInstance().getAllRecipeComponents().toString()).build().toString(), RecipeComponentController.getInstance().getAllRecipeComponents().toString());
    }

    @Test
    void getRecipeComponent() {
        RecipeDTO rdto = new RecipeDTO(999, "TestRecipe");
        RecipeDAO rdao = new RecipeDAO();
        rdao.addRecipe(rdto);

        RecipeComponentDTO rcdto = new RecipeComponentDTO(999, 1, 99.9999, 99.9999);
        RecipeComponentDAO rcdao = new RecipeComponentDAO();
        rcdao.addRecipeComponent(rcdto);

        assertEquals(Response.ok(RecipeComponentController.getInstance().getRecipeComponent(999, 1)).build().toString(), RecipeComponentController.getInstance().getRecipeComponent(999, 1).toString());
        RecipeComponentController.getInstance().deleteRecipeComponent(999, 1);
        rdao.deleteRecipe(999);
    }
}