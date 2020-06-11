package controllers;

import controllers.icontrollers.IProductBatchComponentController;
import dto.IngredientBatchDTO;

import java.util.ArrayList;

public class ProductBatchComponentController implements IProductBatchComponentController {
    @Override
    public void deleteProductBatchComponent(int id) {

    }

    @Override
    public void addProductBatchComponent(int batchId, int ingredientId, double amount) {

    }

    @Override
    public void updateProductBatchComponent(int batchId, int ingredientId, double amount) {

    }

    @Override
    public ArrayList<IngredientBatchDTO> getAllProductBatchComponents() throws Exception {
        return null;
    }

    @Override
    public IngredientBatchDTO getProductBatchComponents(int id) throws Exception {
        return null;
    }
}
