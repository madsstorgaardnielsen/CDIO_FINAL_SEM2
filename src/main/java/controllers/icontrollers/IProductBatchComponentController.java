package controllers.icontrollers;

import controllers.IngredientBatchController;
import dto.IngredientBatchDTO;

import java.util.ArrayList;

public interface IProductBatchComponentController {
    static IngredientBatchController getInstance() {
        return null;
    }

    void deleteProductBatchComponent(int id);

    void addProductBatchComponent(int batchId, int ingredientId, double amount);

    void updateProductBatchComponent(int batchId, int ingredientId, double amount);

    ArrayList<IngredientBatchDTO> getAllProductBatchComponents() throws Exception;

    IngredientBatchDTO getProductBatchComponents(int id) throws Exception;

}
