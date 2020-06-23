package controllers;

import controllers.icontrollers.IRecipeComponentController;
import dao.RecipeComponentDAO;
import dto.RecipeComponentDTO;
import resources.InputValidation;

import javax.ws.rs.core.Response;

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
            if (!validation.idValidation(recipeComponent.getRecipeID())) {
                return Response.status(418, "Forkert input<br> Indtastet Recept id: " + recipeComponent.getRecipeID() + "<br> Id skal ligge i intervallet 1-99999999").build();
            } else if (!validation.idValidation(recipeComponent.getIngredientID())) {
                return Response.status(418, "Forkert input<br> Indtastet Råvare id: " + recipeComponent.getIngredientID() + "<br> Id skal ligge i intervallet 1-99999999").build();
            } else if (!validation.decimalValidation(String.valueOf(recipeComponent.getNonNetto()))) {
                return Response.status(418, "Forkert input <br>Indtastet Nominel netto: " + recipeComponent.getNonNetto() + "<br> Nominel netto skal indskrives med 4 decimaler").build();
            } else
                return Response.status(418, "Forkert input<br> Indtastet tolerance: " + recipeComponent.getTolerance() + "<br> Tolerance skal indskrives med 4 decimaler").build();
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
            if (!validation.idValidation(recipeID)) {
                return Response.status(418, "Forkert input<br> Indtastet Recept id: " + recipeID + "<br> Id skal ligge i intervallet 1-99999999").build();
            } else if (!validation.idValidation(ingredientID)) {
                return Response.status(418, "Forkert input<br> Indtastet Råvare id: " + ingredientID + "<br> Id skal ligge i intervallet 1-99999999").build();
            } else if (!validation.decimalValidation(String.valueOf(nonNetto))) {
                return Response.status(418, "Forkert input <br>Indtastet Nominel netto: " + nonNetto + "<br> Nominel netto skal indskrives med 4 decimaler").build();
            } else
                return Response.status(418, "Forkert input<br> Indtastet tolerance: " + tolerance + "<br> Tolerance skal indskrives med 4 decimaler").build();
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
