package dao;

import controllers.IngredientController;
import dto.ProductBatchDTO;
import dto.RecipeDTO;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ProductBatchDAOTest {

    @Test
    void addProductBatch() {
        RecipeDTO testing = new RecipeDTO();
        testing.setRecipeID(999);
        testing.setRecipeName("Testing");
        RecipeDAO.getInstance().addRecipe(testing);
        ProductBatchDAO.getInstance().addProductBatch(999, 1);
        int recipeId = ProductBatchDAO.getInstance().getProductBatchFromRecipeIdUserId(999, 1).getRecipeId();
        assertEquals(999, recipeId);
        ProductBatchDAO.getInstance().deleteProductBatchByRecipeId(999);
        RecipeDAO.getInstance().deleteRecipe(999);
    }

    @Test
    void updateProductBatch() {
        RecipeDTO testing = new RecipeDTO();
        testing.setRecipeID(999);
        testing.setRecipeName("Testing");
        RecipeDAO.getInstance().addRecipe(testing);
        ProductBatchDTO pbdto;
        ProductBatchDAO.getInstance().addProductBatch(999, 1);
        pbdto = ProductBatchDAO.getInstance().getProductBatchFromRecipeIdUserId(999, 1);

        ProductBatchDAO pbdao = new ProductBatchDAO();
        pbdto.setStatus(1);
        pbdto.setUserId(3);
        pbdto.setCreationDate("2020-06-24");
        pbdao.updateProductBatch(pbdto);
        assertEquals(0, ProductBatchDAO.getInstance().getProductBatchFromRecipeIdUserId(999, 1).getStatus());
        ProductBatchDAO.getInstance().deleteProductBatchByRecipeId(999);
        RecipeDAO.getInstance().deleteRecipe(999);
    }

    @Test
    void deleteProductBatch() {
        RecipeDTO testing = new RecipeDTO();
        testing.setRecipeID(999);
        testing.setRecipeName("Testing");
        RecipeDAO.getInstance().addRecipe(testing);
        ProductBatchDAO pbdao = new ProductBatchDAO();
        pbdao.addProductBatch(999, 1);
        assertEquals(0, ProductBatchDAO.getInstance().getProductBatchFromRecipeIdUserId(999, 1).getStatus());
        ProductBatchDAO.getInstance().deleteProductBatchWithRecipeIdUserId(999, 1);
        assertEquals(0, ProductBatchDAO.getInstance().getProductBatchFromRecipeIdUserId(999, 1).getStatus());
        RecipeDAO.getInstance().deleteRecipe(999);
    }

    @Test
    void getAllProductBatch() {
        if (ProductBatchDAO.getInstance().getAllProductBatch().size() > 0) {
            assertTrue(true);
        } else {
            assertFalse(false);
        }
    }

    @Test
    void getProductBatch() {
        RecipeDTO testing = new RecipeDTO();
        testing.setRecipeID(999);
        testing.setRecipeName("Testing");
        RecipeDAO.getInstance().addRecipe(testing);
        ProductBatchDAO pbdao = new ProductBatchDAO();
        pbdao.addProductBatch(999, 1);

        assertEquals(0, ProductBatchDAO.getInstance().getProductBatchFromRecipeIdUserId(999, 1).getStatus());
        ProductBatchDAO.getInstance().deleteProductBatchWithRecipeIdUserId(999, 1);
        RecipeDAO.getInstance().deleteRecipe(999);
    }
}