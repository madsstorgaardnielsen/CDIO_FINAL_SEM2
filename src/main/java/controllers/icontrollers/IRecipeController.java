package controllers.icontrollers;

import controllers.RecipeController;
import dto.RecipeComponentDTO;
import dto.RecipeDTO;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

public interface IRecipeController {
    public static RecipeController getInstance() {
        return null;
    }

    public Response deleteRecipe(int ID);

    public Response addRecipe(RecipeDTO recipeDTO) ;

    public Response updateRecipe(int recipeID, String recipeName);

    public Response getAllRecipes() throws Exception ;

    public Response getRecipe(int ID) throws Exception ;
}
