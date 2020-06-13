package controllers.icontrollers;

import controllers.IngredientBatchController;
import dao.IngredientBatchDAO;
import dto.IngredientBatchDTO;
import validation.InputValidation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IIngredientBatchController {

    static IngredientBatchController getInstance() {
        return null;
    }

    void deleteIngredientBatch(int id);

    void addIngredientBatch(int batchId, int ingredientId, double amount, String supplier);

    void updateIngredientBatch(int batchId, int ingredientId, double amount, String supplier);

    ArrayList<IngredientBatchDTO> getAllIngredientBatch() throws Exception;

    IngredientBatchDTO getIngredientBatch(int id) throws Exception;

}
