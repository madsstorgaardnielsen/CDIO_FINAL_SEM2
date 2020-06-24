package dto;

import dto.idto.IProductBatchComponentDTO;

/**
 * A class used when data has to be "transported" from the backend to the database,
 */
public class ProductBatchComponentDTO implements IProductBatchComponentDTO {
    private static final long serialVersionUID = 4545864587995944260L;
    private int id;
    private int productBatchID;
    private int ingredientID;
    private String ingredientName;
    private int ingredientBatchID; //
    private int laborantID; //
    private String tara; //
    private String netto;
    private int terminal; //
    private double amount;
    private double tolerance;
    private String brutto; //

    public ProductBatchComponentDTO(int id, int productBatchID, int ingredientBatchID, int ingredientID, int amount, int tolerance
            , int laborantID, String tara, String netto, String brutto, int terminal, String ingredientName) {
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
        this.ingredientID = ingredientID;
    }

    public ProductBatchComponentDTO() {
    }


    @Override
    public String getIngredientName() {
        return ingredientName;
    }

    @Override
    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
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
    public String getTara() {
        return tara;
    }

    @Override
    public void setTara(String tara) {
        this.tara = tara;
    }

    @Override
    public String getNetto() {
        return netto;
    }

    @Override
    public void setNetto(String netto) {
        this.netto = netto;
    }

    @Override
    public int getIngredientID() {
        return ingredientID;
    }

    @Override
    public void setIngredientID(int ingredientID) {
        this.ingredientID = ingredientID;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public double getTolerance() {
        return tolerance;
    }

    @Override
    public void setTolerance(double tolerance) {
        this.tolerance = tolerance;
    }

    public String getBrutto() {
        return brutto;
    }

    @Override
    public void setBrutto(String brutto) {
        this.brutto = brutto;
    }

    public String toString() {
        String out = "ComponentID: " + id +
                ", ProductBatchID: " + productBatchID +
                ", IngredientID: " + ingredientID +
                ", IngredientName: " + ingredientName +
                ", Amount: " + amount +
                ", Tolerance: " + tolerance +
                ", UserID" + laborantID +
                ", Tara: " + tara +
                ", Netto: " + netto +
                ", Brutto: " + brutto +
                ", Terminal: " + terminal;
        return out;
    }

}
