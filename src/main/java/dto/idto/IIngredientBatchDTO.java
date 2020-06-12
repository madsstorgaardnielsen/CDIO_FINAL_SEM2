package dto.idto;

import java.io.Serializable;

public interface IIngredientBatchDTO extends Serializable {
    String getSupplier();

    void setSupplier(String supplier);

    int getIngredientBatchId();

    void setIngredientBatchId(int ingredientBatchId);

    int getIngredientId();

    void setIngredientId(int ingredientId);

    double getAmount();

    void setAmount(double amount);

}
