package controllers;

import controllers.icontrollers.IProductBatchComponentController;
import dao.ProductBatchComponentDAO;
import dao.ProductBatchDAO;
import dao.RecipeDAO;
import dto.IngredientBatchDTO;
import dto.ProductBatchComponentDTO;
import validation.InputValidation;

import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductBatchComponentController implements IProductBatchComponentController {
    private static ProductBatchComponentController instance;

    static {
        try{
            instance = new ProductBatchComponentController();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    InputValidation validation;
    ProductBatchComponentDAO componentDAO;
    ProductBatchComponentDTO componentDTO;

    private ProductBatchComponentController() throws SQLException {
        this.validation = new InputValidation();
        this.componentDAO = new ProductBatchComponentDAO();
        this.componentDTO = new ProductBatchComponentDTO();
    }


    public static ProductBatchComponentController getInstance(){
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
    public Response updateProductBatchComponent(int batchId, int ingredientId, double amount) {
        return Response.ok().build();
    }

    @Override
    public Response getAllProductBatchComponents() throws Exception {
        return Response.ok(new ArrayList<IngredientBatchDTO>()).build();
    }

    @Override
    public Response getProductBatchComponents(int id) throws Exception {
        return Response.ok(new IngredientBatchDTO()).build();
    }

    @Override
    public Response getNextComponent(int batchID) throws Exception {
        try {
            ArrayList<ProductBatchComponentDTO> list = ProductBatchComponentDAO.getInstance().getProductBatchComponent(batchID);
            for (ProductBatchComponentDTO componentDTO : list) {
                if (componentDTO.getLaborantID() == 0) {

                    return Response.ok(componentDTO).build();
                }
            }
            return Response.status(400, "ProductBatch er fuldt afvejet").build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}
