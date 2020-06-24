package controllers;

import dao.ProductBatchDAO;
import dao.RecipeDAO;
import dto.RecipeComponentDTO;
import dto.RecipeDTO;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

class ProductBatchControllerTest {

    @Test
    void addProductBatch() {
        RecipeDAO.getInstance().deleteRecipe(999);
        ProductBatchDAO.getInstance().deleteProductBatchByRecipeId(999);
        RecipeDTO testing = new RecipeDTO();
        RecipeComponentDTO testing1 = new RecipeComponentDTO();
        testing1.setIngredientID(1);
        testing1.setNonNetto(1.2222);
        testing1.setRecipeID(999);
        testing1.setTolerance(1.1111);
        testing1.setIngredientName("Vand");
        testing.setRecipeID(999);
        testing.setRecipeName("Testing");
        testing.addToRecipeCompList(testing1);
        RecipeDAO.getInstance().addRecipe(testing);
        assertEquals(Response.ok().build().toString(), ProductBatchController.getInstance().addProductBatch(999, 1).toString());
        ProductBatchDAO.getInstance().deleteProductBatchByRecipeId(999);
        RecipeDAO.getInstance().deleteRecipe(999);
    }

    @Test
    void getProductBatch() {
        assertEquals(Response.ok(true).build().toString(), ProductBatchController.getInstance().getProductBatch(1).toString());
    }
}