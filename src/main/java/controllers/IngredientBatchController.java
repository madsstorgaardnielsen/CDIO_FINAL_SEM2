package controllers;

import dao.IngredientBatchDAO;
import dao.IngredientDAO;
import dto.IngredientBatchDTO;
import dto.IngredientDTO;
import validation.InputValidation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientBatchController {

    private static IngredientBatchController instance;

    static {
        try {
            instance = new IngredientBatchController();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private final InputValidation validation;
    private final IngredientBatchDAO ingredientBatchDAO;
    private IngredientBatchDTO ingredientBatchDTO;

    private IngredientBatchController() throws SQLException {
        this.ingredientBatchDAO = new IngredientBatchDAO();
        this.ingredientBatchDTO = new IngredientBatchDTO();
        this.validation = new InputValidation();
    }

    public static IngredientBatchController getInstance() {
        return instance;
    }

    public void deleteIngredient(int id) {
        try {
            ingredientBatchDAO.deleteIngredientBatch(id);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void addIngredient(int batchId, int ingredientId, double amount) {
        ingredientBatchDTO = new IngredientBatchDTO(batchId, ingredientId, amount);
        try {
            ingredientBatchDAO.addIngredientBatch(ingredientBatchDTO);
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateIngredient(int batchId, int ingredientId, double amount) {
        ingredientBatchDTO = new IngredientBatchDTO(batchId, ingredientId, amount);

        try {
            ingredientBatchDAO.updateIngredientBatch(ingredientBatchDTO);
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<IngredientBatchDTO> getAllIngredients() throws Exception {
        return ingredientBatchDAO.getAllIngredientBatch();
    }

    public IngredientBatchDTO getIngredient(int id) throws Exception {
        return ingredientBatchDAO.getIngredientBatch(id);
    }

}
