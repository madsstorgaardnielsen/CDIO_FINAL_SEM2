package dto;

import dto.idto.IIngredientDTO;

public class IngredientDTO implements IIngredientDTO {
    int ingredientID;

    public IngredientDTO() {
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




    String ingredientName;

    public IngredientDTO(int ingredientID, String ingredientName) {
        this.ingredientID = ingredientID;
        this.ingredientName = ingredientName;
    }


}


