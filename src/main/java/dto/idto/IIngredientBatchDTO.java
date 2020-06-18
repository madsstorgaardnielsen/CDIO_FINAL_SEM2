package dto.idto;

import java.io.Serializable;
import java.math.BigDecimal;

public interface IIngredientBatchDTO extends Serializable {
    String getSupplier();

    void setSupplier(String supplier);

    int getIngredientBatchId();

    void setIngredientBatchId(int ingredientBatchId);

    int getIngredientId();

    void setIngredientId(int ingredientId);

    String getAmount();

    void setAmount(String amount);

}
