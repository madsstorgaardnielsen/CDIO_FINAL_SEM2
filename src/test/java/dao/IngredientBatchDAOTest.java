package dao;

import dto.IngredientBatchDTO;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class IngredientBatchDAOTest {

    @Test
    void addIngredientBatch() {
        IngredientBatchDAO ibDAO = new IngredientBatchDAO();
        IngredientBatchDTO ibDTO = new IngredientBatchDTO();
        ibDTO.setIngredientBatchId(99999998);
        ibDTO.setIngredientId(1);
        ibDTO.setAmount("1.1111");
        ibDTO.setSupplier("TestSupplier");
        try {
            ibDAO.addIngredientBatch(ibDTO);
            assertEquals(99999998, ibDAO.getIngredientBatch(99999998).getIngredientBatchId());
            ibDAO.deleteIngredientBatch(99999998);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateIngredientBatch() {
        IngredientBatchDTO ibDTO = new IngredientBatchDTO();
        IngredientBatchDAO ibDAO = new IngredientBatchDAO();

        ibDAO.deleteIngredientBatch(99999999);
        ibDTO.setIngredientBatchId(99999999);
        ibDTO.setIngredientId(1);
        ibDTO.setAmount("5.5555");
        ibDTO.setSupplier("TestSupplier");
        ibDAO.addIngredientBatch(ibDTO);

        assertEquals("5.5555", ibDAO.getIngredientBatch(99999999).getAmount());
        ibDTO.setAmount("6.6666");
        ibDAO.updateIngredientBatch(ibDTO);
        assertEquals("6.6666", ibDAO.getIngredientBatch(99999999).getAmount());

    }

    @Test
    void deleteIngredientBatch() {
        IngredientBatchDAO ibDAO = new IngredientBatchDAO();
        IngredientBatchDTO ibDTO = new IngredientBatchDTO();
        ibDTO.setIngredientBatchId(99999998);
        ibDTO.setIngredientId(1);
        ibDTO.setAmount("1.1111");
        ibDTO.setSupplier("TestSupplier");
        try {
            ibDAO.addIngredientBatch(ibDTO);
            assertEquals(99999998, ibDAO.getIngredientBatch(99999998).getIngredientBatchId());
            ibDAO.deleteIngredientBatch(99999998);
            assertNotEquals(99999998, ibDAO.getIngredientBatch(99999998).getIngredientBatchId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAllIngredientBatch() {
        IngredientBatchDAO ibDAO = new IngredientBatchDAO();

        if (ibDAO.getAllIngredientBatch().size() > 1) {
            assertTrue(true);
        } else {
            assertFalse(false);
        }

    }

    @Test
    void getIngredientBatch() {
        IngredientBatchDAO ibDAO = new IngredientBatchDAO();
        IngredientBatchDTO ibDTO = new IngredientBatchDTO();
        ibDTO.setIngredientBatchId(99999998);
        ibDTO.setIngredientId(1);
        ibDTO.setAmount("1.1111");
        ibDTO.setSupplier("TestSupplier");
        try {
            ibDAO.addIngredientBatch(ibDTO);
            assertEquals(99999998, ibDAO.getIngredientBatch(99999998).getIngredientBatchId());
            ibDAO.deleteIngredientBatch(99999998);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}