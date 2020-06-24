package dao;

import controllers.IngredientController;
import dto.ProductBatchDTO;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ProductBatchDAOTest {

    @Test
    void addProductBatch() {
        ProductBatchDAO.getInstance().addProductBatch(99999999, 1);
        int recipeId = ProductBatchDAO.getInstance().getProductBatchFromRecipeIdUserId(99999999, 1).getRecipeId();
        assertEquals(99999999, recipeId);
    }

    @Test
    void updateProductBatch() {
        ProductBatchDTO pbdto;
        pbdto = ProductBatchDAO.getInstance().getProductBatchFromRecipeIdUserId(99999999, 1);

        ProductBatchDAO pbdao = new ProductBatchDAO();
        pbdao.addProductBatch(99999999, 1);
        pbdto.setStatus(0);
        pbdao.updateProductBatch(pbdto);
        assertEquals(0, ProductBatchDAO.getInstance().getProductBatchFromRecipeIdUserId(99999999, 1).getStatus());
        ProductBatchDAO.getInstance().deleteProductBatchWithRecipeIdUserId(1, 1);
    }

    @Test
    void deleteProductBatch() {

        ProductBatchDAO pbdao = new ProductBatchDAO();
        pbdao.addProductBatch(99999999, 1);
        assertEquals(0, ProductBatchDAO.getInstance().getProductBatchFromRecipeIdUserId(99999999, 1).getStatus());
        ProductBatchDAO.getInstance().deleteProductBatchWithRecipeIdUserId(99999999, 1);
        assertEquals(0, ProductBatchDAO.getInstance().getProductBatchFromRecipeIdUserId(99999999, 1).getStatus());

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
        ProductBatchDAO pbdao = new ProductBatchDAO();
        pbdao.addProductBatch(99999999, 1);

        assertEquals(0, ProductBatchDAO.getInstance().getProductBatchFromRecipeIdUserId(99999999, 1).getStatus());
        ProductBatchDAO.getInstance().deleteProductBatchWithRecipeIdUserId(99999999, 1);
    }
}