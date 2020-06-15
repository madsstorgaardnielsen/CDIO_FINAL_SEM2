package dao;

import db.DBConnection;
import dto.ProductBatchComponentDTO;
import dto.RecipeComponentDTO;
import dto.RecipeDTO;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductBatchComponentDAO {
    private static ProductBatchComponentDAO instance;
    static {
        try {
            instance = new ProductBatchComponentDAO();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    PreparedStatement statement;
    private DBConnection database;

    public ProductBatchComponentDAO() throws SQLException {
        database = new DBConnection();
    }

    public static ProductBatchComponentDAO getInstance() {
        return instance;
    }

    public void addComponentsByRecipe(RecipeDTO recipe, int batchID) throws SQLException, IOException {
        // making string for statement
        String statementString = "{call AddComponents(?,?)}";
        //adding value set for each component
        for (RecipeComponentDTO comp : recipe.getRecipeCompList()){
            statementString += "(" + batchID + ", " + comp.getIngredientID() + "),";
        }
        statementString = statementString.substring(0, statementString.length()-1);
        //System.out.println(statementString); //testing
        statement = database.prepareStatement(statementString);
        try {
            statement.executeUpdate();
            System.out.println("ProductBatchComponents successfully added to database");
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Something went wrong with addComponentsByRecipe()");
        }
    }
    public ArrayList<ProductBatchComponentDTO> getCompByBatch(int batchID) throws SQLException, IOException {
        String statementString = "{call GetCompByBatch(?)}";
        statement = database.callableStatement(statementString);
        statement.setInt(1,batchID);
        ArrayList<ProductBatchComponentDTO> components = new ArrayList<ProductBatchComponentDTO>();
        try{
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                components.add(getComponentInfo(rs));
            }
            return components;
        } catch (Exception e){
            e.printStackTrace();
            throw new IOException("Something went wrong with getCompByBatch()");
        }

    }
    private ProductBatchComponentDTO getComponentInfo(ResultSet rs) throws SQLException {
        ProductBatchComponentDTO comp = new ProductBatchComponentDTO();

        comp.setIngredientID(rs.getInt(1));
        comp.setId(rs.getInt(2));
        comp.setProductBatchID(rs.getInt(3));
        comp.setIngredientBatchID(rs.getInt(4));
        comp.setLaborantID(rs.getInt(5));
        comp.setTara(rs.getDouble(6));
        comp.setNetto(rs.getDouble(7));
        comp.setTerminal(rs.getInt(8));
        comp.setAmount(rs.getDouble(9));
        comp.setIngredientName(rs.getString(10));
        return comp;
    }
}
