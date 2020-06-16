package controllers.icontrollers;

import dto.RecipeComponentDTO;

import javax.ws.rs.core.Response;

public interface IRecipeComponentController {
    Response getAllRecipeComponentsFromID(int recipeID);

    Response deleteRecipeComponent(int recipeID, int ingredientID);

    Response addRecipeComponent(RecipeComponentDTO recipeComponent);

    Response updateRecipeComponent(int recipeID, int ingredientID, double nonNetto, double tolerance);

    Response getAllRecipeComponents() throws Exception;

    Response getRecipeComponent(int recipeID, int ingredientID) throws Exception;
}
