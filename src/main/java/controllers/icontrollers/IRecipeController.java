package controllers.icontrollers;

import controllers.RecipeController;
import dto.RecipeDTO;

import java.util.ArrayList;

public interface IRecipeController {
    public static RecipeController getInstance() {
        return null;
    }

    public void deleteRecipe(int ID);

    public void addRecipe(int recipeID, String recipeName, int ingredientID, double nonNetto, double tolerance) ;

    public void updateRecipe(int recipeID, String recipeName, int ingredientID, double nonNetto, double tolerance) ;

    public ArrayList<RecipeDTO> getAllRecipes() throws Exception ;

    public RecipeDTO getRecipe(int ID) throws Exception ;
}
