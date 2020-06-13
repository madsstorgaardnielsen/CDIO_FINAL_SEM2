package controllers;

import controllers.icontrollers.IIngredientBatchController;
import dao.IngredientBatchDAO;
import dto.IngredientBatchDTO;
import validation.InputValidation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientBatchController implements IIngredientBatchController {

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

    public void deleteIngredientBatch(int id) {
        try {
            ingredientBatchDAO.deleteIngredientBatch(id);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void addIngredientBatch(int batchId, int ingredientId, double amount, String supplier) {
        ingredientBatchDTO = new IngredientBatchDTO(batchId, ingredientId, amount, supplier);
        try {
            ingredientBatchDAO.addIngredientBatch(ingredientBatchDTO);
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateIngredientBatch(int batchId, int ingredientId, double amount, String supplier) {
        ingredientBatchDTO = new IngredientBatchDTO(batchId, ingredientId, amount, supplier);

        try {
            ingredientBatchDAO.updateIngredientBatch(ingredientBatchDTO);
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<IngredientBatchDTO> getAllIngredientBatch() throws Exception {
        return ingredientBatchDAO.getAllIngredientBatch();
    }

    public IngredientBatchDTO getIngredientBatch(int id) throws Exception {
        return ingredientBatchDAO.getIngredientBatch(id);
    }

}
