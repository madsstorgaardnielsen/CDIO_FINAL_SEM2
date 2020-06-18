package controllers;

import dao.ProductBatchComponentDAO;
import dao.ProductBatchDAO;
import dao.RecipeDAO;
import dao.UserDAO;
import db.DBConnection;
import dto.ProductBatchDTO;
import dto.RecipeComponentDTO;
import dto.RecipeDTO;
import validation.InputValidation;

import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductBatchController {

    private static ProductBatchController instance;

    static {
        instance = new ProductBatchController();
    }

    private final ProductBatchDAO productBatchDAO;
    private final InputValidation validation;
    private ProductBatchDTO productBatchDTO;

    private ProductBatchController() {
        this.productBatchDAO = new ProductBatchDAO();
        validation = new InputValidation();
    }

    public static ProductBatchController getInstance() {
        return instance;
    }

    public Response addProductBatch(int recipeID, int userID) {
        productBatchDTO = new ProductBatchDTO(recipeID, userID);
        if (validation.productBatchInputValidation(productBatchDTO)) {
            try {
                int newBatchID = productBatchDAO.addProductBatch(recipeID, userID);
                //add batch and get recipe and new batch id to make components.
                ProductBatchComponentDAO.getInstance().addComponentsByRecipe(RecipeDAO.getInstance().getRecipe(recipeID), newBatchID);
                return Response.ok().build();
            } catch (Exception e) {
                return Response.serverError().build();
            }
        }
        else{
                return Response.status(418, "Bad input").build();
        }
    }

    public Response getProductBatch(int batchID) {
        try {
            //get batch
            ProductBatchDTO productBatch = ProductBatchDAO.getInstance().getProductBatch(batchID);
            if (productBatch.getProductBatchId() != 0) {
                //get and set componenents:
                productBatch.setComponents(ProductBatchComponentDAO.getInstance().getCompByBatch(batchID));
                //System.out.println(productBatch.toString()); //testing
                return Response.ok(productBatch).build();
            } else
                return Response.status(418, "Produktbatch ikke oprettet").build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    public Response getAllProductBatch() {
        try {
            return Response.ok(ProductBatchDAO.getInstance().getAllProductBatch()).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}
