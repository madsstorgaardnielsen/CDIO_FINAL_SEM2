package dto;

import dao.idao.IIngredientBatchDAO;
import dto.idto.IIngredientBatchDTO;

public class IngredientBatchDTO implements IIngredientBatchDTO {
    int ingredientBatchId;
    int ingredientId;
    double amount;
    String supplier;

    public IngredientBatchDTO() {
    }

    public IngredientBatchDTO(int ingredientBatchId, int ingredientId, double amount, String supplier) {
        this.ingredientBatchId = ingredientBatchId;
        this.ingredientId = ingredientId;
        this.amount = amount;
        this.supplier = supplier;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getIngredientBatchId() {
        return ingredientBatchId;
    }

    public void setIngredientBatchId(int ingredientBatchId) {
        this.ingredientBatchId = ingredientBatchId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
