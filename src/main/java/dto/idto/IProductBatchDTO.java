package dto.idto;

import java.io.Serializable;

public interface IProductBatchDTO extends Serializable {

    double getTaraSum();

    void setTaraSum(double tara);

    double getNettoSum();

    void setNettoSum(double netto);

    String getFinishDate();

    void setFinishDate(String finishDate);

    String getCreationDate();

    void setCreationDate(String creationDate);

    int getProductBatchId();

    void setProductBatchId(int productBatchId);

    int getRecipeId();

    void setRecipeId(int recipeId);

    int getStatus();

    void setStatus(int status);

    int getUserId();

    void setUserId(int userId);

    int getIngredientBatchId();

    void setIngredientBatchId(int ingredientBatchId);
}
