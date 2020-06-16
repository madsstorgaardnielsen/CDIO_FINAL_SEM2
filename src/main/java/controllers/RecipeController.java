package controllers;

import controllers.icontrollers.IRecipeController;
import dao.RecipeDAO;
import dto.RecipeDTO;
import validation.InputValidation;

import javax.ws.rs.core.Response;
import java.sql.SQLException;

public class RecipeController implements IRecipeController {
    private static RecipeController instance;

    static {
        try {
            instance = new RecipeController();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private final InputValidation validation;
    private final RecipeDAO RecipeDAO;
    private RecipeDTO RecipeDTO;

    private RecipeController() throws SQLException {
        this.RecipeDAO = new RecipeDAO();
        this.RecipeDTO = new RecipeDTO();
        this.validation = new InputValidation();
    }

    public static RecipeController getInstance() {
        return instance;
    }

    public Response deleteRecipe(int ID) {
        try {
            RecipeDAO.deleteRecipe(ID);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(418, "Bad input").build();
        }
    }


    public Response addRecipe(RecipeDTO recipeDTO) {
        if (validation.recipeInputValidation(recipeDTO)) {
            try {
                RecipeDAO.addRecipe(RecipeDTO);
                return Response.ok().build();
            } catch (Exception e) {
                return Response.serverError().build();
            }
        } else {
            return Response.status(418, "Bad input").build();
        }
    }

    public Response addRecipeOnly(RecipeDTO recipeDTO) {
        if (validation.recipeInputValidation(recipeDTO)) {
            try {
                RecipeDAO.addRecipeOnly(recipeDTO);
                return Response.ok().build();
            } catch (Exception e) {
                return Response.serverError().build();
            }
        } else {
            return Response.status(418, "Bad input").build();
        }
    }

    public Response updateRecipe(int recipeID, String recipeName) {
        RecipeDTO recipeDTO = new RecipeDTO(recipeID, recipeName);
        if (validation.recipeInputValidation(recipeDTO)) {
            try {
                return Response.ok(RecipeDAO.updateRecipe(recipeDTO)).build();
            } catch (Exception e) {
                e.printStackTrace();
                return Response.serverError().build();

            }
        } else {
            return Response.status(418, "Bad input").build();
        }
    }

    public Response getAllRecipes() throws Exception {
        try {
            RecipeDAO.getAllRecipes();
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    public Response getAllRecipesOnly() throws Exception {
        try {
            return Response.ok(RecipeDAO.getAllRecipesOnly()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    public Response getRecipe(int ID) throws Exception {
        try {
            RecipeDTO recipe = RecipeDAO.getRecipe(ID);
            return Response.ok(recipe).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }

    }
}
