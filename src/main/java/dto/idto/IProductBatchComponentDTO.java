package dto.idto;

import java.io.Serializable;

public interface IProductBatchComponentDTO extends Serializable {


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
}
