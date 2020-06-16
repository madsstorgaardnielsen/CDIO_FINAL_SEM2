package dto;

public class RecipeComponentDTO {

    private static final long serialVersionUID = 4732984592846315285L;
    private int recipeID;
    private int ingredientID;
    private double nonNetto;
    private double tolerance;
    public RecipeComponentDTO(int recipeID, int ingredientID, double nonNetto, double tolerance) {
        this.recipeID = recipeID;
        this.ingredientID = ingredientID;
        this.nonNetto = nonNetto;
        this.tolerance = tolerance;
    }
    public RecipeComponentDTO() {
    }

    @Override
    public String toString() {
        return recipeID + " " + ingredientID + " " + nonNetto + " " + tolerance;
    }

    public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    public int getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(int ingredientID) {
        this.ingredientID = ingredientID;
    }

    public double getNonNetto() {
        return nonNetto;
    }

    public void setNonNetto(double nonNetto) {
        this.nonNetto = nonNetto;
    }

    public double getTolerance() {
        return tolerance;
    }

    public void setTolerance(double tolerance) {
        this.tolerance = tolerance;
    }
}
