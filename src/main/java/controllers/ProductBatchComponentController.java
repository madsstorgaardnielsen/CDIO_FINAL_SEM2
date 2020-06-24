package controllers;

import controllers.icontrollers.IProductBatchComponentController;
import dao.IngredientBatchDAO;
import dao.ProductBatchComponentDAO;
import dao.ProductBatchDAO;
import dto.IngredientBatchDTO;
import dto.ProductBatchComponentDTO;
import dto.ProductBatchDTO;
import resources.InputValidation;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * The controller class for "Produkt batch komponenter", it takes input from the API class,
 * validates input and passes the data to the corresponding DAO class, when the DAO class saved/deleted/updated the data,
 * the controller class returns a Response to the corresponding API.
 */
public class ProductBatchComponentController implements IProductBatchComponentController {
    private static final ProductBatchComponentController instance;

    static {
        instance = new ProductBatchComponentController();
    }

    InputValidation validation;
    ProductBatchComponentDAO componentDAO;
    ProductBatchComponentDTO componentDTO;

    private ProductBatchComponentController() {
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
    //Logic of validating and saving change to productbatch
    public Response updateProductBatchComponent(ProductBatchComponentDTO batchComponent) {
        try {
            //get info about productbatchcomponent
            ProductBatchComponentDTO batch = ProductBatchComponentDAO.getInstance().
                    getProductBatchComponentByID(batchComponent.getId());
            //set net value
            batchComponent.setNetto(String.valueOf(Double.parseDouble(batchComponent.getBrutto())
                    - Double.parseDouble(batchComponent.getTara())));
            //validate net weight is within bounds
            if (InputValidation.getInstance().validateAfvejning2(batchComponent, batch)) {
                //try updating the info
                ProductBatchComponentDAO.getInstance().updateProductBatchComponent(batchComponent);
                //update ingredientbatch weight
                IngredientBatchDAO.getInstance().subtractFromIngredientAmount(batchComponent.getIngredientBatchID(),
                        Double.parseDouble(batchComponent.getNetto()));
                return Response.ok().build(); //send response status 200
            } else
                //if weight not within bounds
                return Response.status(418, "difference større end tolerance").build();
        } catch (Exception e) { //error handling
            e.printStackTrace();
            return Response.serverError().build(); //send status internal server error
        }
    }

    @Override
    public Response getAllProductBatchComponents() {
        return Response.ok(new ArrayList<IngredientBatchDTO>()).build();
    }

    @Override
    public Response getProductBatchComponents(int id) {
        return Response.ok(new IngredientBatchDTO()).build();
    }

    @Override
    public Response getNextComponent(int batchID) {
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
                    taraSum += Double.parseDouble(componentDTO.getTara());
                    nettoSum += Double.parseDouble(componentDTO.getNetto());
                }
                batch.setTaraSum(String.valueOf(taraSum));
                batch.setNettoSum(String.valueOf(nettoSum));
                ProductBatchDAO.getInstance().setStatusDone(batch);
            }
            return Response.status(400, "Productbatch er fuldt afvejet").build();
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
            return Response.status(418, "Råvatebatch matcher ikke Råvare").build();
    }
}
