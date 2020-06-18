package dao;

import dao.exceptions.DatabaseConnectionException;
import dao.exceptions.DatabaseException;
import dao.idao.IIngredientDAO;
import db.DBConnection;
import dto.IngredientDTO;

import javax.ws.rs.NotFoundException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//TODO implement inputvalidation class
public class IngredientDAO implements IIngredientDAO {

    private static IngredientDAO instance;

    static {
        instance = new IngredientDAO();
    }

    PreparedStatement statement;
    private DBConnection database;

    public IngredientDAO() {
        database = new DBConnection();
    }

    public static IngredientDAO getInstance() {
        return instance;
    }

    public void addIngredient(IngredientDTO ingredient) {
        try {
            String addIngredient = "{call AddIngredient(?,?)}";
            PreparedStatement statement = database.callableStatement(addIngredient);
            statement.setInt(1, ingredient.getIngredientID());
            statement.setString(2, ingredient.getIngredientName());

            statement.execute();
            System.out.println("Ingredient successfully added to database");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    public void updateIngredient(IngredientDTO ingredient) {
        try {
            String updateIngredient = "{call UpdateIngredient(?,?)}";
            PreparedStatement statement = database.callableStatement(updateIngredient);

            statement.setInt(1, ingredient.getIngredientID());
            statement.setString(2, ingredient.getIngredientName());

            statement.executeUpdate();
            System.out.println("Ingredient successfully updated");
        } catch (Exception e) {
            e.printStackTrace();
            throw new NotFoundException();
        }
    }

    public void deleteIngredient(int id) {
        try {
            String deleteIngredient = "{call DeleteIngredient(?)}";
            PreparedStatement statement = database.callableStatement(deleteIngredient);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Ingredient successfully deleted");
        } catch (Exception e) {
            e.printStackTrace();
            throw new NotFoundException();
        }
    }


    public ArrayList<IngredientDTO> getAllIngredients() {
        ArrayList<IngredientDTO> ingredientList = new ArrayList<>();
        IngredientDTO ingredientDTO = null;

        try {
            CallableStatement stmt = database.callableStatement("{call GetAllIngredients}");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ingredientDTO = new IngredientDTO();
                getIngredientInfo(rs, ingredientDTO);
                ingredientList.add(ingredientDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NotFoundException();
        }
        return ingredientList;
    }

    public void getIngredientInfo(ResultSet rs, IngredientDTO ingredientDTO) {
        try {
            ingredientDTO.setIngredientID(rs.getInt(1));
            ingredientDTO.setIngredientName(rs.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NotFoundException();
        }
    }

    public IngredientDTO getIngredient(int ID) {
        IngredientDTO ingredient = null;

        try {
            CallableStatement stmt = database.callableStatement("{call GetIngredient(?)}");
            stmt.setInt(1, ID);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                ingredient = new IngredientDTO();
                getIngredientInfo(resultSet, ingredient);
            }

            if (ingredient == null) {
                throw new NotFoundException();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }

        return ingredient;
    }
}
