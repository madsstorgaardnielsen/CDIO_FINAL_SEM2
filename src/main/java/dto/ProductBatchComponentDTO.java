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
    private static final long serialVersionUID = 4545864587995944260L;

    public ProductBatchComponentDTO(int id, int productBatchID, int ingredientBatchID, int ingredientID, int amount, int tolerance
            ,int laborantID, double tara, double netto, double brutto, int terminal) {
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

    public ProductBatchComponentDTO() {
    }


    @Override
    public int getTerminal() {
        return terminal;
    }

    @Override
    public void setTerminal(int terminal) {
        this.terminal = terminal;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getProductBatchID() {
        return productBatchID;
    }

    @Override
    public void setProductBatchID(int productBatchID) {
        this.productBatchID = productBatchID;
    }

    @Override
    public int getIngredientBatchID() {
        return ingredientBatchID;
    }

    @Override
    public void setIngredientBatchID(int ingredientBatchID) {
        this.ingredientBatchID = ingredientBatchID;
    }

    @Override
    public int getLaborantID() {
        return laborantID;
    }

    @Override
    public void setLaborantID(int laborantID) {
        this.laborantID = laborantID;
    }

    @Override
    public double getTara() {
        return tara;
    }

    @Override
    public void setTara(double tara) {
        this.tara = tara;
    }

    @Override
    public double getNetto() {
        return netto;
    }

    @Override
    public void setNetto(double netto) {
        this.netto = netto;
    }

    @Override
    public int getIngredientID() {
        return ingredientID;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public double getTolerance() {
        return tolerance;
    }

    public double getBrutto() {
        return brutto;
    }

    @Override
    public void setIngredientID(int ingredientID) {
        this.ingredientID = ingredientID;
    }

    @Override
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public void setTolerance(double tolerance) {
        this.tolerance = tolerance;
    }

    @Override
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
