package controllers;

import dao.IngredientDAO;
import dao.RecipeComponentDAO;
import dao.RecipeDAO;
import dto.IngredientDTO;
import dto.RecipeComponentDTO;
import dto.RecipeDTO;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RecipeControllerTest {

    @Test
    void deleteRecipe() {
        ArrayList<RecipeComponentDTO> components = new ArrayList<>();
        RecipeComponentDTO rcdto = new RecipeComponentDTO(999, 1, 2.2222, 3.3333);
        components.add(rcdto);

        RecipeDTO rdto = new RecipeDTO();
        rdto.setRecipeCompList(components);
        rdto.setRecipeName("TestName");
        rdto.setRecipeID(999);
        RecipeDAO.getInstance().addRecipe(rdto);

        assertEquals(Response.ok(RecipeController.getInstance().getRecipe(999)).build().toString(), RecipeController.getInstance().getRecipe(999).toString());
        RecipeController.getInstance().deleteRecipe(999);
        assertEquals(Response.ok(RecipeController.getInstance().getRecipe(999)).build().toString(), RecipeController.getInstance().getRecipe(999).toString());

    }

    @Test
    void addRecipe() {
        ArrayList<RecipeComponentDTO> components = new ArrayList<>();
        RecipeComponentDTO rcdto = new RecipeComponentDTO(999, 1, 2.2222, 3.3333);
        components.add(rcdto);

        RecipeDTO rdto = new RecipeDTO();
        rdto.setRecipeCompList(components);
        rdto.setRecipeName("TestName");
        rdto.setRecipeID(999);
        RecipeDAO.getInstance().addRecipe(rdto);

        assertEquals(Response.ok(RecipeController.getInstance().getRecipe(999)).build().toString(), RecipeController.getInstance().getRecipe(999).toString());
        RecipeController.getInstance().deleteRecipe(999);
    }

    @Test
    void addRecipeOnly() {
        RecipeDTO rdto = new RecipeDTO();
        rdto.setRecipeID(999);
        rdto.setRecipeName("TestName");
        RecipeController.getInstance().addRecipeOnly(rdto);
        assertEquals(Response.ok(RecipeController.getInstance().getRecipe(999)).build().toString(), RecipeController.getInstance().getRecipe(999).toString());
        RecipeController.getInstance().deleteRecipe(999);
    }

/*    @Test
    void updateRecipe() {
        ArrayList<RecipeComponentDTO> components = new ArrayList<>();
        RecipeComponentDTO rcdto = new RecipeComponentDTO(999,1,2.2222,3.3333);
        components.add(rcdto);

        RecipeDTO rdto = new RecipeDTO();
        rdto.setRecipeCompList(components);
        rdto.setRecipeName("TestName");
        rdto.setRecipeID(999);
        RecipeDAO.getInstance().addRecipe(rdto);

        assertEquals(Response.ok(RecipeController.getInstance().getRecipe(999)).build().toString(),RecipeController.getInstance().getRecipe(999).toString());
        rdto.setRecipeName("TestNameUpdated");
        RecipeDAO rdao = new RecipeDAO();
        rdao.updateRecipe(rdto);
        assertEquals(Response.ok(RecipeController.getInstance().getRecipe(999)).build().toString(),RecipeController.getInstance().getRecipe(999).toString());

        RecipeController.getInstance().deleteRecipe(999);

    }*/

    @Test
    void getAllRecipes() {
        assertEquals(Response.ok().build().toString(), RecipeController.getInstance().getAllRecipes().toString());
    }

    @Test
    void getRecipe() {
        RecipeDTO rdto = new RecipeDTO(999, "TestName");
        RecipeDAO rdao = new RecipeDAO();
        rdao.addRecipe(rdto);

        assertEquals(Response.ok(rdto).build().toString(), RecipeController.getInstance().getRecipe(999).toString());

        RecipeController.getInstance().deleteRecipe(999);
    }
}