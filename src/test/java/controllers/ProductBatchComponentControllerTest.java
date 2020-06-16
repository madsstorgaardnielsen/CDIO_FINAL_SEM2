package controllers;

import dto.ProductBatchComponentDTO;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

class ProductBatchComponentControllerTest {

    @Test
    void updateProductBatchComponent() {
        ProductBatchComponentDTO test = new ProductBatchComponentDTO();
        test.setId(555);
        assertEquals(Response.ok().build().toString(), ProductBatchComponentController.getInstance().updateProductBatchComponent(test).toString());
    }

    @Test
    void getNextComponent() throws Exception {
        assertEquals(Response.ok(true).build().toString(),ProductBatchComponentController.getInstance().getNextComponent(35).toString());
    }
}