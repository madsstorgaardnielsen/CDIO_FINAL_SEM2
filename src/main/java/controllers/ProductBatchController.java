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

public class ProductBatchController {

    private final ProductBatchDAO productBatchDAO;
    private static ProductBatchController instance;

    static {
        try {
            instance = new ProductBatchController();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private ProductBatchController() throws SQLException {
        this.productBatchDAO = new ProductBatchDAO();
    }

    public static ProductBatchController getInstance() {
        return instance;
    }

    public Response addProductBatch(int recipeID, int userID) throws Exception {
        try {
            //add batch and get recipe and new batch id to make components.
            int newBatchID = productBatchDAO.addProductBatch(recipeID, userID);
            ProductBatchComponentDAO.getInstance().addComponentsByRecipe(RecipeDAO.getInstance().getRecipe(recipeID),newBatchID); ;

            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    public Response getProductBatch(int batchID) throws Exception{
        try {
            return Response.ok(ProductBatchDAO.getInstance().getProductBatch(batchID)).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}
