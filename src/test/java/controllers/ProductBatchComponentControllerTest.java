package controllers;

import dto.ProductBatchComponentDTO;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

class ProductBatchComponentControllerTest {

    @Test
    void updateProductBatchComponent() {
/*        ProductBatchComponentDTO test = new ProductBatchComponentDTO();
        test.setId(555);
        test.setBrutto("222");
        test.setTara("1");
        test.setTolerance(2);
        test.setAmount(500);
        assertEquals(Response.ok().build().toString(), ProductBatchComponentController.getInstance().updateProductBatchComponent(test).toString());*/
    }

    @Test
    void getNextComponent() {
        //assertEquals(Response.ok(true).build().toString(),ProductBatchComponentController.getInstance().getNextComponent(35).toString());
    }
}