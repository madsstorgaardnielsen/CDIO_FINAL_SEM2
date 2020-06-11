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

    void deleteIngredient(int id);

    void addIngredient(int batchId, int ingredientId, double amount);

    void updateIngredient(int batchId, int ingredientId, double amount);

    ArrayList<IngredientBatchDTO> getAllIngredients() throws Exception;

    IngredientBatchDTO getIngredient(int id) throws Exception;

}
