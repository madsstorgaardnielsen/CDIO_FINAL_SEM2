package controllers;

import dao.IngredientBatchDAO;
import dto.IngredientBatchDTO;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class IngredientBatchControllerTest {

    @Test
    void deleteIngredientBatch() {
        IngredientBatchDTO ibdto = new IngredientBatchDTO(999,1,999.9999,"TestSupplier");
        assertEquals(Response.ok().build().toString(),IngredientBatchController.getInstance().addIngredientBatch(ibdto).toString());
        IngredientBatchController.getInstance().deleteIngredientBatch(999);
        assertEquals(Response.ok().build().toString(),IngredientBatchController.getInstance().getIngredientBatch(999).toString());
    }

    @Test
    void addIngredientBatch() {
        IngredientBatchDTO ibdto = new IngredientBatchDTO(999,1,999.9999,"TestSupplier");
        assertEquals(Response.ok().build().toString(),IngredientBatchController.getInstance().addIngredientBatch(ibdto).toString());
        IngredientBatchController.getInstance().deleteIngredientBatch(999);
    }


    @Test
    void updateIngredientBatch() throws SQLException, IOException {
        IngredientBatchDTO ibdto = new IngredientBatchDTO(999,1,999.9999,"TestSupplier");
        IngredientBatchDAO ibdao = new IngredientBatchDAO();
        ibdao.addIngredientBatch(ibdto);
        IngredientBatchController.getInstance().updateIngredientBatch(999,1,111.1111,"TestSupplierUpdated");
        assertEquals(Response.ok().build().toString(),IngredientBatchController.getInstance().updateIngredientBatch(999,1,111.1111,"TestSupplierUpdated").toString());
        IngredientBatchController.getInstance().deleteIngredientBatch(999);
    }


    @Test
    void getAllIngredientBatch() throws Exception {
        assertEquals(Response.ok().build().toString(),IngredientBatchController.getInstance().getAllIngredientBatch().toString());
    }

    @Test
    void getIngredientBatch() throws Exception {
        IngredientBatchDTO ibdto = new IngredientBatchDTO(999,1,999.9999,"TestSupplier");
        IngredientBatchDAO ibdao = new IngredientBatchDAO();
        ibdao.addIngredientBatch(ibdto);
        assertEquals(Response.ok().build().toString(),IngredientBatchController.getInstance().getIngredientBatch(999).toString());
        IngredientBatchController.getInstance().deleteIngredientBatch(999);
    }
}