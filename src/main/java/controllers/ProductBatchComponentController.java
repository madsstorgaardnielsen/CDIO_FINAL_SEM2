package controllers;

import controllers.icontrollers.IProductBatchComponentController;
import dao.IngredientBatchDAO;
import dao.ProductBatchComponentDAO;
import dao.ProductBatchDAO;
import dao.RecipeDAO;
import dto.IngredientBatchDTO;
import dto.ProductBatchComponentDTO;
import dto.ProductBatchDTO;
import dto.RecipeComponentDTO;
import validation.InputValidation;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

public class ProductBatchComponentController implements IProductBatchComponentController {
    private static final ProductBatchComponentController instance;

    static {
        instance = new ProductBatchComponentController();
    }

    InputValidation validation;
    ProductBatchComponentDAO componentDAO;
    ProductBatchComponentDTO componentDTO;

    private ProductBatchComponentController()  {
        this.validation = new InputValidation();
        this.componentDAO = new ProductBatchComponentDAO();
        this.componentDTO = new ProductBatchComponentDTO();
    }


    public static ProductBatchComponentController getInstance() {
        return instance;
    }

    @Override
    public Response deleteProductBatchComponent(int id) {
        return Response.ok().build();
    }

    @Override
    public Response addProductBatchComponent(int batchId, int ingredientId, double amount) {
        return Response.ok().build();
    }

    @Override
    public Response updateProductBatchComponent(ProductBatchComponentDTO batchComponent) {
        try {
            ProductBatchComponentDTO batch = ProductBatchComponentDAO.getInstance().getProductBatchComponentByID(batchComponent.getId());
            batchComponent.setNetto(batchComponent.getBrutto() - batchComponent.getTara());
            if (InputValidation.getInstance().validateAfvejning2(batchComponent, batch)) {
                ProductBatchComponentDAO.getInstance().updateProductBatchComponent(batchComponent);
                return Response.ok().build();
            } else
                return Response.status(418, "difference større end tolerance").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @Override
    public Response getAllProductBatchComponents()  {
        return Response.ok(new ArrayList<IngredientBatchDTO>()).build();
    }

    @Override
    public Response getProductBatchComponents(int id) {
        return Response.ok(new IngredientBatchDTO()).build();
    }

    @Override
    public Response getNextComponent(int batchID){
        try {
            ArrayList<ProductBatchComponentDTO> list = ProductBatchComponentDAO.getInstance().getProductBatchComponent(batchID);
            for (ProductBatchComponentDTO componentDTO : list) {
                if (componentDTO.getLaborantID() == 0) {
                    ProductBatchDAO.getInstance().setStatus(batchID);
                    return Response.ok(componentDTO).build();
                }
            }
            ProductBatchDTO batch = ProductBatchDAO.getInstance().getProductBatch(batchID);
            if (batch.getStatus() == 1) {
                double taraSum = 0;
                double nettoSum = 0;
                for (ProductBatchComponentDTO componentDTO : ProductBatchComponentDAO.getInstance().getCompByBatch(batchID)) {
                    taraSum += componentDTO.getTara();
                    nettoSum += componentDTO.getNetto();
                }
                batch.setTaraSum(taraSum);
                batch.setNettoSum(nettoSum);
                ProductBatchDAO.getInstance().setStatusDone(batch);
            }
            return Response.status(400, "ProductBatch er fuldt afvejet").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    public Response validateIngredientBatch(int ID, int batchID) {
        ProductBatchComponentDTO componentDTO = ProductBatchComponentDAO.getInstance().getProductBatchComponentByID(ID);
        IngredientBatchDTO ingredientBatchDTO = IngredientBatchDAO.getInstance().getIngredientBatch(batchID);
        if (InputValidation.getInstance().validateIngredientBatch(ingredientBatchDTO, componentDTO))
            return Response.ok().build();
        else
            return Response.status(418, "IngrediensBatch matcher ikke ingrediens").build();
    }
}
