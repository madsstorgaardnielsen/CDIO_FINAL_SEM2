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
        IngredientBatchDTO ibDTO = new IngredientBatchDTO();
        IngredientBatchDAO ibDAO = new IngredientBatchDAO();
        ibDTO.setIngredientBatchId(99999999);
        ibDTO.setIngredientId(ibDAO.getIngredientBatch(99999999).getIngredientId());
        ibDTO.setAmount(6.6666);

        ibDAO.updateIngredientBatch(ibDTO);

        assertEquals(6.6666, ibDTO.getAmount());

    }

    @Test
    void deleteIngredientBatch() throws Exception {
    }

    @Test
    void getAllIngredientBatch() {
    }

    @Test
    void getIngredientBatch() throws Exception {
    }
}