package dao;

import db.DBConnection;
import dto.RecipeComponentDTO;
import dto.RecipeDTO;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
