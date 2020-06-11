package dto.idto;

import java.io.Serializable;

public interface IRecipeDTO  {

    String toString();

    int getRecipeID();

    void setRecipeID(int recipeID);

    String getRecipeName();

    void setRecipeName(String recipeName);

    int getIngredientID();

    void setIngredientID(int ingredientID);

    double getNonNetto();

    void setNonNetto(double nonNetto);

    double getTolerance();

    void setTolerance(double tolerance);
}
