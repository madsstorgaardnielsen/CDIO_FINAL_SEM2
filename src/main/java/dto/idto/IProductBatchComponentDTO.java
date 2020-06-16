package dto.idto;

import java.io.Serializable;

public interface IProductBatchComponentDTO extends Serializable {

    String getIngredientName();

    void setIngredientName(String name);

    int getId();

    void setId(int id);

    int getProductBatchID();

    void setProductBatchID(int productBatchID);

    int getIngredientBatchID();

    void setIngredientBatchID(int ingredientBatchID);

    int getLaborantID();

    void setLaborantID(int laborantID);

    double getTara();

    void setTara(double tara);

    double getNetto();

    void setNetto(double netto);

    int getIngredientID();

    void setIngredientID(int IngredientID);

    double getAmount();

    void setAmount(double amount);

    double getTolerance();

    void setTolerance(double tolerance);

    double getBrutto();

    void setBrutto(double brutto);

    int getTerminal();

    void setTerminal(int terminal);
}