package controllers.icontrollers;

import controllers.RecipeController;
import dto.RecipeDTO;

import javax.ws.rs.core.Response;

public interface IRecipeController {

    Response deleteRecipe(int ID);

    Response addRecipe(RecipeDTO recipeDTO) ;

    Response updateRecipe(int recipeID, String recipeName);

    Response getAllRecipes() throws Exception ;

    Response getRecipe(int ID) throws Exception ;
}
