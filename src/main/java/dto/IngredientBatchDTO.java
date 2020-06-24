package dto;

import dto.idto.IIngredientBatchDTO;
import org.glassfish.jersey.server.model.Routed;

import java.math.BigDecimal;

/**
 * A class used when data has to be "transported" from the backend to the database,
 */
public class IngredientBatchDTO implements IIngredientBatchDTO {
    int ingredientBatchId;
    int ingredientId;
    String amount;
    String supplier;

    public IngredientBatchDTO() {
    }

    public IngredientBatchDTO(int ingredientBatchId, int ingredientId, String amount, String supplier) {
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String toString(){
        return "ID: " + ingredientBatchId;
    }
}
