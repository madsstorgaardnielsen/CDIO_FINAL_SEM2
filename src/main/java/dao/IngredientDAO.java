package dao;

import db.DBConnection;
import dto.IngredientDTO;
import dto.UserDTO;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//TODO implement inputvalidation class
public class IngredientDAO {

    private static IngredientDAO instance;

    static {
        try {
            instance = new IngredientDAO();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    PreparedStatement statement;
    private DBConnection database;

    public IngredientDAO() throws SQLException {
        database = new DBConnection();
    }

    public static IngredientDAO getInstance() {
        return instance;
    }

    public void addIngredient(IngredientDTO ingredient) throws SQLException, IOException {

        String addIngredient = "{call AddIngredient(?,?,?)}";
        PreparedStatement statement = database.callableStatement(addIngredient);
        statement.setInt(1, ingredient.getIngredientID());
        statement.setString(2, ingredient.getIngredientName());
        statement.setString(3, ingredient.getSupplier());

        try {
            statement.executeUpdate();
            System.out.println("Ingredient successfully added to database");
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Ingredient could no be added");
        }
    }

    public void updateIngredient(IngredientDTO ingredient) throws IOException, SQLException {

        String updateIngredient = "{call UpdateIngredient(?,?,?)}";
        PreparedStatement statement = database.callableStatement(updateIngredient);

        statement.setInt(1, ingredient.getIngredientID());
        statement.setString(2, ingredient.getIngredientName());
        statement.setString(3, ingredient.getSupplier());

        try {
            statement.executeUpdate();
            System.out.println("Ingredient successfully updated");
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Ingredient could no be updated");
        }
    }

    public void deleteIngredient(int id) throws IOException, SQLException {

        String deleteIngredient = "{call DeleteIngredient(+"+id+")}";
        PreparedStatement statement = database.callableStatement(deleteIngredient);
        statement.setInt(1, id);

        try {
            statement.executeUpdate();
            System.out.println("Ingredient successfully deleted");
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Ingredient could no be deleted");
        }
    }


    public ArrayList<IngredientDTO> getAllIngredients() throws Exception {
        ArrayList<IngredientDTO> ingredientList = new ArrayList<>();
        CallableStatement stmt = database.callableStatement("{call GetAllIngredients}");
        ResultSet rs = stmt.executeQuery();
        IngredientDTO ingredientDTO;
        try {
            while (rs.next()) {
                ingredientDTO = new IngredientDTO();
                getIngredientInfo(rs, ingredientDTO);
                ingredientList.add(ingredientDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredientList;
    }

    private void getIngredientInfo(ResultSet rs, IngredientDTO ingredientDTO) throws SQLException {
        ingredientDTO.setIngredientID(rs.getInt(1));
        ingredientDTO.setIngredientName(rs.getString(2));
        ingredientDTO.setIngredientSupplier(rs.getString(3));
    }

    public IngredientDTO getIngredient(int ID) throws Exception {
        CallableStatement stmt = database.callableStatement("{call GetIngredient(?)}");
        stmt.setInt(1, ID);
        IngredientDTO ingredient = new IngredientDTO();
        ResultSet resultSet = stmt.executeQuery();
        try {
            while (resultSet.next()) {
                getIngredientInfo(resultSet, ingredient);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ingredient;
    }
}
