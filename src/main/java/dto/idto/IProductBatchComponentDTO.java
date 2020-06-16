package dto.idto;

import java.io.Serializable;

public interface IProductBatchComponentDTO extends Serializable {

    void setIngredientName(String name);

    String getIngredientName();

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

    double getTolerance();

    double getBrutto();

    void setAmount(double amount);

    void setTolerance(double tolerance);

    void setBrutto(double brutto);

    int getTerminal();

    void setTerminal(int terminal);
}