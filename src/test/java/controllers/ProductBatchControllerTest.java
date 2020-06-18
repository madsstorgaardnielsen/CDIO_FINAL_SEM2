package controllers;

import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

class ProductBatchControllerTest {

    @Test
    void addProductBatch() {
        assertEquals(Response.ok().build().toString(), ProductBatchController.getInstance().addProductBatch(1, 1).toString());
    }

    @Test
    void getProductBatch() {
        assertEquals(Response.ok(true).build().toString(), ProductBatchController.getInstance().getProductBatch(1).toString());
    }
}