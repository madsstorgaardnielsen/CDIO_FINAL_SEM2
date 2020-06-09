package dao;

import dto.IngredientBatchDTO;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class IngredientBatchDAOTest {

    @Test
    void addIngredientBatch() throws Exception {
        IngredientBatchDTO ibDTO = new IngredientBatchDTO(99999999,1,4.4444);
        IngredientBatchDAO ibDAO = new IngredientBatchDAO();
        ibDAO.deleteIngredientBatch(99999999);
        ibDAO.addIngredientBatch(ibDTO);

        assertEquals(1,ibDAO.getIngredientBatch(99999999).getIngredientId());
        assertEquals(4.4444,ibDAO.getIngredientBatch(99999999).getAmount());

    }

    @Test
    void updateIngredientBatch() throws Exception {

    }

    @Test
    void deleteIngredientBatch() {

    }

    @Test
    void getAllIngredientBatch() {
    }

    @Test
    void getIngredientBatch() {
    }
}