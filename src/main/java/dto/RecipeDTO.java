package dto;

import dto.idto.IRecipeDTO;

import java.util.ArrayList;


public class RecipeDTO implements IRecipeDTO {
    public RecipeDTO(int recipeID, String recipeName, ArrayList<RecipeComponentDTO> recipeCompList){
        this.recipeID = recipeID;
        this.recipeName = recipeName;
        this.recipeCompList = recipeCompList;
    }

    public RecipeDTO(int recipeID, String recipeName){
        this.recipeID = recipeID;
        this.recipeName = recipeName;
    }

    public RecipeDTO() {

    }

    private int recipeID;
    private String recipeName;
    private ArrayList<RecipeComponentDTO> recipeCompList = new ArrayList<RecipeComponentDTO>();
    private static final long serialVersionUID = 4732984592846315285L;

    public String toString() {return recipeID+" "+recipeName;}

    public int getRecipeID() {return recipeID;}

    public void setRecipeID(int recipeID) {this.recipeID = recipeID;}

    public String getRecipeName() {return recipeName;}

    public void setRecipeName(String recipeName) {this.recipeName = recipeName;}

    public ArrayList<RecipeComponentDTO> getRecipeCompList() {
        return recipeCompList;
    }

    public void setRecipeCompList(ArrayList<RecipeComponentDTO> recipeCompList) {
        this.recipeCompList = recipeCompList;
    }

    public void addToRecipeCompList(RecipeComponentDTO recipeComponent) {
        this.recipeCompList.add(recipeComponent);
    }

    public void removeFromRecipeCompList(int recipeComponentID) {
        this.recipeCompList.remove(recipeComponentID);
    }
}
