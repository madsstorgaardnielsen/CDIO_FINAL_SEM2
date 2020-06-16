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
    void addProductBatch() throws IOException, SQLException {
        ProductBatchDAO.getInstance().addProductBatch(99999999, 1);
        int recipeId = ProductBatchDAO.getInstance().getProductBatchFromRecipeIdUserId(99999999, 1,123).getRecipeId();
        assertEquals(99999999, recipeId);
    }

    @Test
    void updateProductBatch() throws IOException, SQLException {
        ProductBatchDTO pbdto;
        pbdto = ProductBatchDAO.getInstance().getProductBatchFromRecipeIdUserId(99999999, 1,95);

        ProductBatchDAO pbdao = new ProductBatchDAO();
        pbdao.addProductBatch(99999999, 1);
        pbdto.setStatus(0);
        pbdao.updateProductBatch(pbdto);
        assertEquals(0, ProductBatchDAO.getInstance().getProductBatchFromRecipeIdUserId(99999999, 1,95).getStatus());
        ProductBatchDAO.getInstance().deleteProductBatchWithRecipeIdUserId(1, 1);
    }

    @Test
    void deleteProductBatch() throws IOException, SQLException {

        ProductBatchDAO pbdao = new ProductBatchDAO();
        pbdao.addProductBatch(99999999, 1);
        assertEquals(0, ProductBatchDAO.getInstance().getProductBatchFromRecipeIdUserId(99999999, 1,99999999).getStatus());
        ProductBatchDAO.getInstance().deleteProductBatchWithRecipeIdUserId(99999999, 1);
        assertEquals(0, ProductBatchDAO.getInstance().getProductBatchFromRecipeIdUserId(99999999, 1,99999999).getStatus());

    }

    @Test
    void getAllProductBatch() throws Exception {
        if (ProductBatchDAO.getInstance().getAllProductBatch().size() > 0) {
            assertTrue(true);
        } else {
            assertFalse(false);
        }
    }

    @Test
    void getProductBatch() throws SQLException, IOException {
        ProductBatchDAO pbdao = new ProductBatchDAO();
        pbdao.addProductBatch(99999999, 1);

        assertEquals(0, ProductBatchDAO.getInstance().getProductBatchFromRecipeIdUserId(99999999, 1,99999999).getStatus());
        ProductBatchDAO.getInstance().deleteProductBatchWithRecipeIdUserId(99999999, 1);
    }
}