package controllers;

import dao.ProductBatchDAO;
import dao.RecipeDAO;
import dao.UserDAO;
import db.DBConnection;
import dto.ProductBatchDTO;
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
            productBatchDAO.addProductBatch(recipeID, userID);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }


}
