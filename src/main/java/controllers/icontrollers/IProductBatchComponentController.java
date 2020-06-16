package controllers.icontrollers;

import controllers.IngredientBatchController;
import controllers.ProductBatchComponentController;
import dto.IngredientBatchDTO;
import dto.ProductBatchComponentDTO;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

public interface IProductBatchComponentController {

    Response deleteProductBatchComponent(int id);

    Response addProductBatchComponent(int batchId, int ingredientId, double amount);

    Response updateProductBatchComponent(ProductBatchComponentDTO batchComponentDTO);

    Response getAllProductBatchComponents() throws Exception;

    Response getProductBatchComponents(int id) throws Exception;

    Response getNextComponent(int batchID) throws Exception;
}
