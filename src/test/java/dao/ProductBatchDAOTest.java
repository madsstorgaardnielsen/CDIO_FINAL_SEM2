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
        assertEquals(1, ProductBatchDAO.getInstance().getProductBatchFromRecipeIdUserId(1, 1).getRecipeId());
    }

    @Test
    void updateProductBatch() throws IOException, SQLException {
        ProductBatchDTO pbdto;
        pbdto = ProductBatchDAO.getInstance().getProductBatchFromRecipeIdUserId(1, 1);

        ProductBatchDAO pbdao = new ProductBatchDAO();
        pbdao.addProductBatch(99999999,1);
        pbdto.setStatus(1);
        pbdto.setUserId(1);
        pbdto.setRecipeId(1);
        pbdao.updateProductBatch(pbdto);
        assertEquals(1,ProductBatchDAO.getInstance().getProductBatchFromRecipeIdUserId(1,1).getStatus());
        ProductBatchDAO.getInstance().deleteProductBatchWithRecipeIdUserId(1,1);
    }

    @Test
    void deleteProductBatch() throws IOException, SQLException {

        ProductBatchDAO pbdao = new ProductBatchDAO();
        pbdao.addProductBatch(99999999,1);
        assertEquals(0,ProductBatchDAO.getInstance().getProductBatchFromRecipeIdUserId(99999999,1).getStatus());
        ProductBatchDAO.getInstance().deleteProductBatchWithRecipeIdUserId(99999999,1);
        assertEquals(0,ProductBatchDAO.getInstance().getProductBatchFromRecipeIdUserId(99999999,1).getStatus());

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
        pbdao.addProductBatch(99999999,1);

        assertEquals(0,ProductBatchDAO.getInstance().getProductBatchFromRecipeIdUserId(99999999,1).getStatus());
        ProductBatchDAO.getInstance().deleteProductBatchWithRecipeIdUserId(99999999,1);
    }
}