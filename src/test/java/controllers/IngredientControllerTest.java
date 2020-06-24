package controllers;

import dao.IngredientDAO;
import dto.IngredientDTO;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

class IngredientControllerTest {


    @Test
    void deleteIngredient() {
        IngredientDTO ibdto = new IngredientDTO(999, "TestSupplier");
        assertEquals(Response.ok().build().toString(), IngredientController.getInstance().addIngredient(ibdto).toString());

        IngredientController.getInstance().deleteIngredient(999);
        assertEquals(Response.ok(true).build().toString(), IngredientController.getInstance().getIngredient2(999).toString());
    }

    @Test
    void addIngredient() {
        IngredientDTO ibdto = new IngredientDTO(999, "TestSupplier");
        assertEquals(Response.ok().build().toString(), IngredientController.getInstance().addIngredient(ibdto).toString());
        IngredientController.getInstance().deleteIngredient(999);
    }

    @Test
    void updateIngredient() {
        IngredientDTO ibdto = new IngredientDTO(999, "TestSupplier");
        IngredientDAO ibdao = new IngredientDAO();
        ibdao.addIngredient(ibdto);
        IngredientController.getInstance().updateIngredient(999, "TestSupplierUpdated");
        assertEquals(Response.ok(true).build().toString(), IngredientController.getInstance().updateIngredient(999, "TestSupplierUpdated").toString());
        IngredientController.getInstance().deleteIngredient(999);
    }

    @Test
    void getAllIngredients() {
        assertEquals(Response.ok(true).build().toString(), IngredientController.getInstance().getAllIngredients().toString());
    }

    @Test
    void getIngredient() {
        IngredientDTO ibdto = new IngredientDTO(999, "TestSupplier");
        IngredientDAO ibdao = new IngredientDAO();
        ibdao.addIngredient(ibdto);
        assertEquals(Response.ok(true).build().toString(), IngredientController.getInstance().getIngredient2(999).toString());
        IngredientController.getInstance().deleteIngredient(999);
    }
}
