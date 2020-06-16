package dto;

public class RecipeComponentDTO {

    public RecipeComponentDTO(int recipeID, int ingredientID, double nonNetto, double tolerance) {
        this.recipeID = recipeID;
        this.ingredientID = ingredientID;
        this.nonNetto = nonNetto;
        this.tolerance = tolerance;
    }

    public RecipeComponentDTO(int recipeID, int ingredientID, double nonNetto, double tolerance, String ingredient) {
        this.recipeID = recipeID;
        this.ingredientID = ingredientID;
        this.nonNetto = nonNetto;
        this.tolerance = tolerance;
        this.ingredientName = ingredient;
    }

    public RecipeComponentDTO(){
    }

    private int recipeID;
    private int ingredientID;
    private double nonNetto;
    private double tolerance;
    private String ingredientName;
    private static final long serialVersionUID = 4732984592846315285L;

    @Override
    public String toString() {return recipeID+" "+ingredientID+" "+nonNetto+" "+tolerance;}

    public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    public int getIngredientID() {return ingredientID;}

    public void setIngredientID(int ingredientID) {this.ingredientID = ingredientID;}

    public double getNonNetto() {return nonNetto;}

    public void setNonNetto(double nonNetto) {this.nonNetto = nonNetto;}

    public double getTolerance() {return tolerance;}

    public void setTolerance(double tolerance) {this.tolerance = tolerance;}

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
