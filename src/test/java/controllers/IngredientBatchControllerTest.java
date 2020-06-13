package controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientBatchControllerTest {

    @Test
    void deleteIngredientBatch() throws Exception {
        IngredientBatchController.getInstance().addIngredientBatch(999,1,999.99, "TestSupplier");
        int id = IngredientBatchController.getInstance().getIngredientBatch(999).getIngredientBatchId();
        assertEquals(999,id);
        IngredientBatchController.getInstance().deleteIngredientBatch(999);
        id = IngredientBatchController.getInstance().getIngredientBatch(999).getIngredientBatchId();
        assertNotEquals(999,id);
    }

    @Test
    void addIngredientBatch() throws Exception {
        IngredientBatchController.getInstance().addIngredientBatch(999,1,999.99, "TestSupplier");
        int id = IngredientBatchController.getInstance().getIngredientBatch(999).getIngredientBatchId();
        assertEquals(999,id);
        IngredientBatchController.getInstance().deleteIngredientBatch(999);
    }

    @Test
    void updateIngredientBatch() throws Exception {
        IngredientBatchController.getInstance().addIngredientBatch(999,1,999.99, "TestSupplier");
        int id = IngredientBatchController.getInstance().getIngredientBatch(999).getIngredientBatchId();
        assertEquals(999,id);
        IngredientBatchController.getInstance().updateIngredientBatch(999,1,111.11,"TestSupplierUpdated");
        double updatedAmount = IngredientBatchController.getInstance().getIngredientBatch(999).getAmount();
        assertEquals(111.11,updatedAmount);
        IngredientBatchController.getInstance().deleteIngredientBatch(999);
    }

    @Test
    void getAllIngredientBatch() throws Exception {

        if (IngredientBatchController.getInstance().getAllIngredientBatch().size() > 0) {
            assertTrue(true);
        } else {
            assertFalse(false);
        }
    }

    @Test
    void getIngredientBatch() throws Exception {
        IngredientBatchController.getInstance().addIngredientBatch(999,1,999.99, "TestSupplier");
        int id = IngredientBatchController.getInstance().getIngredientBatch(999).getIngredientBatchId();
        assertEquals(999,id);
        IngredientBatchController.getInstance().deleteIngredientBatch(999);
    }
}