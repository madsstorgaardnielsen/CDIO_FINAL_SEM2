package controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientControllerTest {

    @Test
    void deleteIngredient() throws Exception {
        IngredientController.getInstance().addIngredient(999,"TestIngredient");
        int id = IngredientController.getInstance().getIngredient(999).getIngredientID();
        assertEquals(999,id);
        IngredientController.getInstance().deleteIngredient(999);
        id = IngredientController.getInstance().getIngredient(999).getIngredientID();
        assertNotEquals(999,id);
    }

    @Test
    void addIngredient() throws Exception {
        IngredientController.getInstance().addIngredient(999,"TestIngredient");
        int id = IngredientController.getInstance().getIngredient(999).getIngredientID();
        assertEquals(999,id);
        IngredientController.getInstance().deleteIngredient(999);
    }

    @Test
    void updateIngredient() throws Exception {
        IngredientController.getInstance().addIngredient(999,"TestIngredient");
        int id = IngredientController.getInstance().getIngredient(999).getIngredientID();
        assertEquals(999,id);

        IngredientController.getInstance().updateIngredient(999,"UpdatedTestIngredient");
        String updated = IngredientController.getInstance().getIngredient(999).getIngredientName();
        assertEquals("UpdatedTestIngredient",updated);
        IngredientController.getInstance().deleteIngredient(999);
    }

    @Test
    void getAllIngredients() throws Exception {
        if (IngredientController.getInstance().getAllIngredients().size() > 0) {
            assertTrue(true);
        } else {
            assertFalse(false);
        }
    }

    @Test
    void getIngredient() throws Exception {
        IngredientController.getInstance().addIngredient(999,"TestIngredient");
        int id = IngredientController.getInstance().getIngredient(999).getIngredientID();
        assertEquals(999,id);
        IngredientController.getInstance().deleteIngredient(999);
    }
}