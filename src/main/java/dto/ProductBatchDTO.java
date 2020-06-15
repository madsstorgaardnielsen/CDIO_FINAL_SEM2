package dto;
import dto.idto.IProductBatchDTO;
import java.util.ArrayList;

public class ProductBatchDTO implements IProductBatchDTO {
    int productBatchId;
    int recipeId;
    int status;
    int userId;
    int ingredientBatchId;
    String creationDate;
    String finishDate;
    double taraSum;
    double nettoSum;
    ArrayList<ProductBatchComponentDTO> components;

    public double getTaraSum() {
        return taraSum;
    }

    public void setTaraSum(double taraSum) {
        this.taraSum = taraSum;
    }

    public double getNettoSum() {
        return nettoSum;
    }

    public void setNettoSum(double nettoSum) {
        this.nettoSum = nettoSum;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public int getProductBatchId() {
        return productBatchId;
    }

    public void setProductBatchId(int productBatchId) {
        this.productBatchId = productBatchId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getIngredientBatchId() {
        return ingredientBatchId;
    }

    public void setIngredientBatchId(int ingredientBatchId) {
        this.ingredientBatchId = ingredientBatchId;
    }

    public ProductBatchDTO() {
    }

    public ProductBatchDTO(int recipeId, int userId) {
        this.productBatchId = productBatchId;
        this.recipeId = recipeId;
        this.status = status;
        this.userId = userId;
        this.ingredientBatchId = ingredientBatchId;
    }

    public ProductBatchDTO(int productBatchId, int recipeId, int status, int userId, int ingredientBatchId) {
        this.productBatchId = productBatchId;
        this.recipeId = recipeId;
        this.status = status;
        this.userId = userId;
        this.ingredientBatchId = ingredientBatchId;
    }
}
