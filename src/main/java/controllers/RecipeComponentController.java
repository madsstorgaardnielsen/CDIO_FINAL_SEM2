package controllers;

import controllers.icontrollers.IRecipeComponentController;
import dao.RecipeComponentDAO;
import dto.RecipeComponentDTO;
import validation.InputValidation;

import javax.ws.rs.core.Response;
import java.sql.SQLException;

public class RecipeComponentController implements IRecipeComponentController {
    private static final RecipeComponentController instance;

    static {
        instance = new RecipeComponentController();
    }

    private final InputValidation validation;
    private final RecipeComponentDAO RecipeComponentDAO;
    private RecipeComponentDTO RecipeComponentDTO;

    private RecipeComponentController() {
        this.RecipeComponentDAO = new RecipeComponentDAO();
        this.RecipeComponentDTO = new RecipeComponentDTO();
        this.validation = new InputValidation();
    }

    public static RecipeComponentController getInstance() {
        return instance;
    }

    @Override
    public Response deleteRecipeComponent(int recipeID, int ingredientID) {
        try {
            RecipeComponentDAO.deleteRecipeComponent(recipeID, ingredientID);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(418, "Bad input").build();
        }
    }

    @Override
    public Response addRecipeComponent(RecipeComponentDTO recipeComponent) {
        if (validation.recipeComponentInputValidation(recipeComponent)) {
            try {
                RecipeComponentDAO.addRecipeComponent(recipeComponent);
                return Response.ok().build();
            } catch (Exception e) {
                return Response.serverError().build();
            }
        } else {
            return Response.status(418, "Bad input").build();
        }
    }

    @Override
    public Response updateRecipeComponent(int recipeID, int ingredientID, double nonNetto, double tolerance) {
        RecipeComponentDTO recipeComponentDTO = new RecipeComponentDTO(recipeID, ingredientID, nonNetto, tolerance);
        if (validation.recipeComponentInputValidation(recipeComponentDTO)) {
            try {
                return Response.ok(RecipeComponentDAO.updateRecipeComponent(recipeComponentDTO)).build();
            } catch (Exception e) {
                e.printStackTrace();
                return Response.serverError().build();
            }
        } else {
            return Response.status(418, "Bad input").build();
        }
    }

    @Override
    public Response getAllRecipeComponents() {
        try {
            return Response.ok(RecipeComponentDAO.getAllRecipeComponents()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @Override
    public Response getAllRecipeComponentsFromID(int recipeID) {
        try {
            return Response.ok(RecipeComponentDAO.getAllRecipeComponentsFromID(recipeID)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @Override
    public Response getRecipeComponent(int recipeID, int ingredientID) {
        try {
            RecipeComponentDAO.getRecipeComponent(recipeID, ingredientID);
            return Response.ok(RecipeComponentDAO.getRecipeComponent(recipeID, ingredientID)).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
