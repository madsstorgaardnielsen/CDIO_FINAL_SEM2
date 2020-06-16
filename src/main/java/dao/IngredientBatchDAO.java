package dao;

import dao.idao.IIngredientBatchDAO;
import dao.idao.IIngredientDAO;
import db.DBConnection;
import dto.IngredientBatchDTO;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//TODO implement inputvalidation class
public class IngredientBatchDAO implements IIngredientBatchDAO {

    private static IngredientBatchDAO instance;

    static {
        try {
            instance = new IngredientBatchDAO();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    PreparedStatement statement;
    private DBConnection database;

    public IngredientBatchDAO() throws SQLException {
        database = new DBConnection();
    }

    public static IngredientBatchDAO getInstance() {
        return instance;
    }

    public void addIngredientBatch(IngredientBatchDTO ingredientBatch) throws SQLException, IOException {

        String addIngredientBatch = "{call AddIngredientBatch(?,?,?,?)}";
        PreparedStatement statement = database.callableStatement(addIngredientBatch);
        statement.setInt(1, ingredientBatch.getIngredientBatchId());
        statement.setInt(2, ingredientBatch.getIngredientId());
        statement.setDouble(3, ingredientBatch.getAmount());
        statement.setString(4, ingredientBatch.getSupplier());

        try {
            statement.executeUpdate();
            System.out.println("Ingredient batch successfully added to database");
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Ingredient batch could no be added");
        }
    }

    public void updateIngredientBatch(IngredientBatchDTO ingredientBatch) throws IOException, SQLException {

        String updateIngredientBatch = "{call UpdateIngredientBatch(?,?,?,?)}";
        PreparedStatement statement = database.callableStatement(updateIngredientBatch);

        statement.setInt(1, ingredientBatch.getIngredientBatchId());
        statement.setInt(2, ingredientBatch.getIngredientId());
        statement.setDouble(3, ingredientBatch.getAmount());
        statement.setString(4, ingredientBatch.getSupplier());

        try {
            statement.executeUpdate();
            System.out.println("Ingredient batch successfully updated");
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Ingredient batch could no be updated");
        }
    }

    public void deleteIngredientBatch(int id) throws IOException, SQLException {

        String deleteIngredientBatch = "{call DeleteIngredientBatch(?)}";
        PreparedStatement statement = database.callableStatement(deleteIngredientBatch);
        statement.setInt(1, id);

        try {
            statement.executeUpdate();
            System.out.println("Ingredient batch successfully deleted");
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Ingredient batch could no be deleted");
        }
    }


    public ArrayList<IngredientBatchDTO> getAllIngredientBatch() throws Exception {
        ArrayList<IngredientBatchDTO> ingredientBatchList = new ArrayList<>();
        CallableStatement stmt = database.callableStatement("{call GetAllIngredientBatch}");
        ResultSet rs = stmt.executeQuery();
        IngredientBatchDTO ingredientBatchDTO;
        try {
            while (rs.next()) {
                ingredientBatchDTO = new IngredientBatchDTO();
                getIngredientBatchInfo(rs, ingredientBatchDTO);
                ingredientBatchList.add(ingredientBatchDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredientBatchList;
    }

    public void getIngredientBatchInfo(ResultSet rs, IngredientBatchDTO ingredientBatchDTO) throws SQLException {
        ingredientBatchDTO.setIngredientBatchId(rs.getInt(1));
        ingredientBatchDTO.setIngredientId(rs.getInt(2));
        ingredientBatchDTO.setAmount(rs.getDouble(3));
    }

    public IngredientBatchDTO getIngredientBatch(int ID) throws Exception {
        CallableStatement stmt = database.callableStatement("{call GetIngredientBatch(?)}");
        stmt.setInt(1, ID);
        IngredientBatchDTO ingredientBatch = new IngredientBatchDTO();
        ResultSet resultSet = stmt.executeQuery();
        try {
            while (resultSet.next()) {
                getIngredientBatchInfo(resultSet, ingredientBatch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ingredientBatch;
    }

}
