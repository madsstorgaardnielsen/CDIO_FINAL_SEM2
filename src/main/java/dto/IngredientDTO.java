package dto;

import dto.idto.IIngredientDTO;

/**
 * A class used when data has to be "transported" from the backend to the database,
 */
public class IngredientDTO implements IIngredientDTO {
    int ingredientID;
    String ingredientName;

    public IngredientDTO() {
    }

    public IngredientDTO(int ingredientID, String ingredientName) {
        this.ingredientID = ingredientID;
        this.ingredientName = ingredientName;
    }

    public int getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(int ingredientID) {
        this.ingredientID = ingredientID;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }


}


