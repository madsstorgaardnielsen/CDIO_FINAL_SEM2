package controllers.icontrollers;

import controllers.IngredientBatchController;
import dao.IngredientBatchDAO;
import dto.IngredientBatchDTO;
import dto.IngredientDTO;
import validation.InputValidation;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IIngredientBatchController {

    static IngredientBatchController getInstance() {
        return null;
    }

    Response deleteIngredientBatch(int id);

    Response addIngredientBatch(IngredientDTO ingredientDTO);

    Response updateIngredientBatch(int batchId, int ingredientId, double amount, String supplier);

    Response getAllIngredientBatch();

    Response getIngredientBatch(int id);

}
