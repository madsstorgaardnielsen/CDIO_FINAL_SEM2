package controllers;

import dao.RecipeDAO;
import dao.UserDAO;
import dto.RecipeDTO;
import dto.UserDTO;
import validation.InputValidation;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.sql.SQLException;

public class RecipeController {
    private final InputValidation validation;
    private final RecipeDAO recipeDAO;
    private static RecipeController instance;

    static {
        try {
            instance = new RecipeController();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private RecipeController() throws SQLException {
        this.recipeDAO = new RecipeDAO();
        this.validation = new InputValidation();
    }

    public static RecipeController getInstance()
    {
        return instance;
    }

    public Response addRecipe(RecipeDTO recipeDTO) throws Exception {
        try {
            recipeDAO.addRecipe(recipeDTO);
            return Response.ok().build();
        } catch (Exception e){
            return Response.serverError().build();
        }
    }

    public Response getAllRecipes(UriInfo uriInfo) throws Exception {
            try {
                return Response.ok(recipeDAO.getAllRecipes()).build();
            } catch (Exception e) {
                e.printStackTrace();
                return Response.serverError().build();
            }
        }
}
