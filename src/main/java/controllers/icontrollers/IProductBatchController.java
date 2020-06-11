package controllers.icontrollers;

import controllers.IngredientBatchController;
import dto.IngredientBatchDTO;

import java.util.ArrayList;

public interface IProductBatchController {
    static IngredientBatchController getInstance() {
        return null;
    }

    void deleteProductBatch(int id);

    void addProductBatch(int batchId, int ingredientId, double amount);

    void updateProductBatch(int batchId, int ingredientId, double amount);

    ArrayList<IngredientBatchDTO> getAllProductBatch() throws Exception;

    IngredientBatchDTO getProductBatch(int id) throws Exception;

}
