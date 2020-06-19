package controllers;

import controllers.icontrollers.IRecipeController;
import dao.RecipeDAO;
import dto.RecipeDTO;
import validation.InputValidation;

import javax.ws.rs.core.Response;

public class RecipeController implements IRecipeController {
    private static final RecipeController instance;

    static {
        instance = new RecipeController();
    }

    private final InputValidation validation;
    private final RecipeDAO RecipeDAO;
    private RecipeDTO RecipeDTO;

    private RecipeController() {
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
            if (!validation.idValidation(recipeDTO.getRecipeID())) {
                return Response.status(418, "Forkert input<br> Indtastet Recept id: " + recipeDTO.getRecipeID() + "<br> Id skal ligge i intervallet 1-99999999").build();
            } else
                return Response.status(418, "Forkert input<br> Indtastet Recept navn: " + recipeDTO.getRecipeName() + "<br> Indtast venligst kun bogstaver").build();

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
            if (!validation.idValidation(recipeDTO.getRecipeID())) {
                return Response.status(418, "Forkert input<br> Indtastet Recept id: " + recipeDTO.getRecipeID() + "<br> Id skal ligge i intervallet 1-99999999").build();
            } else
                return Response.status(418, "Forkert input<br> Indtastet Recept navn: " + recipeDTO.getRecipeName() + "<br> Indtast venligst kun bogstaver").build();
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
            if (!validation.idValidation(recipeDTO.getRecipeID())) {
                return Response.status(418, "Forkert input<br> Indtastet Recept id: " + recipeDTO.getRecipeID() + "<br> Id skal ligge i intervallet 1-99999999").build();
            } else
                return Response.status(418, "Forkert input<br> Indtastet Recept navn: " + recipeDTO.getRecipeName() + "<br> Indtast venligst kun bogstaver").build();
        }
    }

    public Response getAllRecipes() {
        try {
            RecipeDAO.getAllRecipes();
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    public Response getAllRecipesOnly() {
        try {
            return Response.ok(RecipeDAO.getAllRecipesOnly()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    public Response getRecipe(int ID) {
        try {
            RecipeDTO recipe = RecipeDAO.getRecipe(ID);
            return Response.ok(recipe).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
