package controllers.icontrollers;

import dto.IngredientBatchDTO;

import javax.ws.rs.core.Response;

public interface IIngredientBatchController {

    Response deleteIngredientBatch(int id);

    Response addIngredientBatch(IngredientBatchDTO ingredientBatchDTO);

    Response updateIngredientBatch(int batchId, int ingredientId, String amount, String supplier);

    Response getAllIngredientBatch();

    Response getIngredientBatch(int id);

}
