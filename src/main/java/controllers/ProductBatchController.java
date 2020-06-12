package controllers;

import controllers.icontrollers.IProductBatchController;
import dto.IngredientBatchDTO;

import java.util.ArrayList;

public class ProductBatchController implements IProductBatchController {
    @Override
    public void deleteProductBatch(int id) {
        
    }

    @Override
    public void addProductBatch(int batchId, int ingredientId, double amount) {

    }

    @Override
    public void updateProductBatch(int batchId, int ingredientId, double amount) {

    }

    @Override
    public ArrayList<IngredientBatchDTO> getAllProductBatch() throws Exception {
        return null;
    }

    @Override
    public IngredientBatchDTO getProductBatch(int id) throws Exception {
        return null;
    }
}
