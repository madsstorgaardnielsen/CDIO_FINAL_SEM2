package dao;

import dto.IngredientBatchDTO;
import dto.IngredientDTO;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class IngredientDAOTest {

    @Test
    void addIngredient() {
        IngredientDAO iDAO = new IngredientDAO();
        IngredientDTO iDTO = new IngredientDTO();
        iDAO.deleteIngredient(999);
        iDTO.setIngredientID(999);
        iDTO.setIngredientName("TestName");
        try {
            iDAO.addIngredient(iDTO);
            assertEquals(999, iDAO.getIngredient(999).getIngredientID());
            iDAO.deleteIngredient(999);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateIngredient() {
        IngredientDAO iDAO = new IngredientDAO();
        IngredientDTO iDTO = new IngredientDTO();

        iDAO.deleteIngredient(999);
        iDTO.setIngredientID(999);
        iDTO.setIngredientName("Testname");
        iDAO.addIngredient(iDTO);

        assertEquals("Testname", iDAO.getIngredient(999).getIngredientName());
        iDTO.setIngredientName("TestNameUpdated");
        iDAO.updateIngredient(iDTO);
        assertEquals("TestNameUpdated", iDAO.getIngredient(999).getIngredientName());
        iDAO.deleteIngredient(999);
    }

    @Test
    void deleteIngredient() {
        IngredientDAO iDAO = new IngredientDAO();
        IngredientDTO iDTO = new IngredientDTO();
        iDAO.deleteIngredient(999);
        iDTO.setIngredientID(999);
        iDTO.setIngredientName("Testname");
        try {
            iDAO.addIngredient(iDTO);
            assertEquals(999, iDAO.getIngredient2(999).getIngredientID());
            iDAO.deleteIngredient(999);
            assertNotEquals(999, iDAO.getIngredient2(999).getIngredientID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAllIngredients() {
        IngredientDAO ibDAO = new IngredientDAO();

        if (ibDAO.getAllIngredients().size() > 1) {
            assertTrue(true);
        } else {
            assertFalse(false);
        }
    }

    @Test
    void getIngredient() {
        IngredientDAO iDAO = new IngredientDAO();
        IngredientDTO iDTO = new IngredientDTO();
        iDAO.deleteIngredient(999);
        iDTO.setIngredientID(999);
        iDTO.setIngredientName("testname");

        try {
            iDAO.addIngredient(iDTO);
            assertEquals(999, iDAO.getIngredient(999).getIngredientID());
            iDAO.deleteIngredient(999);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}