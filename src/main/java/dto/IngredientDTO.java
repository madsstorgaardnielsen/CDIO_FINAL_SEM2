package dto;

public class IngredientDTO {
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

    public String getSupplier() {
        return supplier;
    }

    public void setIngredientSupplier(String supplier) {
        this.supplier = supplier;
    }

    String ingredientName;

    public IngredientDTO(int ingredientID, String ingredientName, String supplier) {
        this.ingredientID = ingredientID;
        this.ingredientName = ingredientName;
        this.supplier = supplier;
    }

    String supplier;
}


