package dto.idto;

import java.io.Serializable;

public interface IRecipeDTO extends Serializable{

    String toString();

    int getRecipeID();

    void setRecipeID(int recipeID);

    String getRecipeName();

    void setRecipeName(String recipeName);
}
