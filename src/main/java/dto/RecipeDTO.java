package dto;

import dao.idao.IRecipeDAO;
import dto.idto.IRecipeDTO;

import java.io.Serializable;

public class RecipeDTO implements IRecipeDTO {
    public RecipeDTO(int recipeID, String recipeName, int ingredientID, double nonNetto, double tolerance){
        this.recipeID = recipeID;
        this.recipeName = recipeName;
        this.ingredientID = ingredientID;
        this.nonNetto = nonNetto;
        this.tolerance = tolerance;
    }

    public RecipeDTO(){
    }

    private int recipeID;
    private String recipeName;
    private int ingredientID;
    private double nonNetto;
    private double tolerance;
    private static final long serialVersionUID = 4732984592846315285L;

    public String toString() {return recipeID+" "+recipeName+" "+ingredientID+" "+nonNetto+" "+tolerance;}

    public int getRecipeID() {return recipeID;}

    public void setRecipeID(int recipeID) {this.recipeID = recipeID;}

    public String getRecipeName() {return recipeName;}

    public void setRecipeName(String recipeName) {this.recipeName = recipeName;}

    public int getIngredientID() {return ingredientID;}

    public void setIngredientID(int ingredientID) {this.ingredientID = ingredientID;}

    public double getNonNetto() {return nonNetto;}

    public void setNonNetto(double nonNetto) {this.nonNetto = nonNetto;}

    public double getTolerance() {return tolerance;}

    public void setTolerance(double tolerance) {this.tolerance = tolerance;}



}
