package dto;

import dto.idto.IProductBatchComponentDTO;

public class ProductBatchComponentDTO implements IProductBatchComponentDTO {
    private int id;
    private int productBatchID;
    private int ingredientID;
    private String ingredientName;
    private int ingredientBatchID; //
    private int laborantID; //
    private double tara; //
    private double netto;
    private int terminal; //
    private double amount;
    private double tolerance;
    private double brutto; //

    public ProductBatchComponentDTO() {
    }

    public ProductBatchComponentDTO(int id, int productBatchID, int ingredientBatchID, String ingredientName, int amount, int tolerance
                                    ,int laborantID, double tara, double netto,double brutto, int terminal) {
        this.id = id;
        this.productBatchID = productBatchID;
        this.ingredientBatchID = ingredientBatchID;
        this.laborantID = laborantID;
        this.tara = tara;
        this.netto = netto;
        this.brutto = brutto;
        this.terminal = terminal;
        this.ingredientName = ingredientName;
        this.tolerance = tolerance;
        this.amount = amount;
    }
    public void setIngredientID(int ingredientID){
        this.ingredientID = ingredientID;
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

    public String getIngredientName() {
        return ingredientName;
    }

    public double getAmount() {
        return amount;
    }

    public double getTolerance() {
        return tolerance;
    }

    public double getBrutto() {
        return brutto;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTolerance(double tolerance) {
        this.tolerance = tolerance;
    }

    public void setBrutto(double brutto) {
        this.brutto = brutto;
    }

    public String toString(){
        String out = "ComponentID: " + id +
                ", ProductBatchID: " + productBatchID +
                ", IngredientID: "+ ingredientID +
                ", IngredientName: " + ingredientName +
                ", Amount: " + amount+
                ", Tolerance: " + tolerance +
                ", UserID" + laborantID+
                ", Tara: " + tara+
                ", Netto: " + netto +
                ", Brutto: " + brutto+
                ", Terminal: " + terminal;
        return out;
    }

}
