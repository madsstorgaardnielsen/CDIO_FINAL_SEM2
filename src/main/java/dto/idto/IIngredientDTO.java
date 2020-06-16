package dto.idto;

import java.io.Serializable;

public interface IIngredientDTO extends Serializable {

    int getIngredientID();

    void setIngredientID(int ingredientID);

    String getIngredientName();

    void setIngredientName(String ingredientName);

}
