package dto;

import dto.idto.IProductBatchComponentDTO;

public class ProductBatchComponentDTO implements IProductBatchComponentDTO {
    int id;
    int productBatchID;
    int ingredientBatchID;
    String ingredientName;
    double amount;
    double tolerance;
    int laborantID;
    double tara;
    double netto;
    double brutto;
    int terminal;
    int del;

    public ProductBatchComponentDTO() {
    }

    public ProductBatchComponentDTO(int id, int productBatchID, int ingredientBatchID,
                                    int laborantID, double tara, double netto,double brutto, int terminal) {
        this.id = id;
        this.productBatchID = productBatchID;
        this.ingredientBatchID = ingredientBatchID;
        this.laborantID = laborantID;
        this.tara = tara;
        this.netto = netto;
        this.terminal = terminal;
    }

    public int getTerminal() {
        return terminal;
    }

    public void setTerminal(int terminal) {
        this.terminal = terminal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductBatchID() {
        return productBatchID;
    }

    public void setProductBatchID(int productBatchID) {
        this.productBatchID = productBatchID;
    }

    public int getIngredientBatchID() {
        return ingredientBatchID;
    }

    public void setIngredientBatchID(int ingredientBatchID) {
        this.ingredientBatchID = ingredientBatchID;
    }

    public int getLaborantID() {
        return laborantID;
    }

    public void setLaborantID(int laborantID) {
        this.laborantID = laborantID;
    }

    public double getTara() {
        return tara;
    }

    public void setTara(double tara) {
        this.tara = tara;
    }

    public double getNetto() {
        return netto;
    }

    public void setNetto(double netto) {
        this.netto = netto;
    }

}
