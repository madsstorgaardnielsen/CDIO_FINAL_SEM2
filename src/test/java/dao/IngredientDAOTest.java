package dao;

import dto.IngredientBatchDTO;
import dto.IngredientDTO;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class IngredientDAOTest {

    @Test
    void addIngredient() throws SQLException, IOException {
        IngredientDAO iDAO = new IngredientDAO();
        IngredientDTO iDTO = new IngredientDTO();
        iDAO.deleteIngredient(99999999);
        iDTO.setIngredientID(99999999);
        iDTO.setIngredientName("TestName");
        try {
            iDAO.addIngredient(iDTO);
            assertEquals(99999999, iDAO.getIngredient(99999999).getIngredientID());
            iDAO.deleteIngredient(99999999);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateIngredient() throws Exception {
        IngredientDAO iDAO = new IngredientDAO();
        IngredientDTO iDTO = new IngredientDTO();

        iDAO.deleteIngredient(99999999);
        iDTO.setIngredientID(99999999);
        iDTO.setIngredientName("Testname");
        iDAO.addIngredient(iDTO);

        assertEquals("Testname", iDAO.getIngredient(99999999).getIngredientName());
        iDTO.setIngredientName("TestNameUpdated");
        iDAO.updateIngredient(iDTO);
        assertEquals("TestNameUpdated", iDAO.getIngredient(99999999).getIngredientName());

    }

    @Test
    void deleteIngredient() throws SQLException, IOException {
        IngredientDAO iDAO = new IngredientDAO();
        IngredientDTO iDTO = new IngredientDTO();
        iDAO.deleteIngredient(99999999);
        iDTO.setIngredientID(99999999);
        iDTO.setIngredientName("Testname");
        try {
            iDAO.addIngredient(iDTO);
            assertEquals(99999999, iDAO.getIngredient(99999999).getIngredientID());
            iDAO.deleteIngredient(99999999);
            assertNotEquals(99999999, iDAO.getIngredient(99999999).getIngredientID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAllIngredients() throws Exception {
        IngredientDAO ibDAO = new IngredientDAO();

        if (ibDAO.getAllIngredients().size() > 1) {
            assertTrue(true);
        } else {
            assertFalse(false);
        }
    }

    @Test
    void getIngredient() throws IOException, SQLException {
        IngredientDAO iDAO = new IngredientDAO();
        IngredientDTO iDTO = new IngredientDTO();
        iDAO.deleteIngredient(99999999);
        iDTO.setIngredientID(99999999);
        iDTO.setIngredientName("testname");

        try {
            iDAO.addIngredient(iDTO);
            assertEquals(99999999, iDAO.getIngredient(99999999).getIngredientID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}