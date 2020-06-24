package dao;

import com.mysql.cj.protocol.Resultset;
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
import java.util.concurrent.Callable;

/**
 * The DAO Class for "Råvarer" used to access the database and do CRUD operations in the database.
 */
public class IngredientDAO implements IIngredientDAO {

    private static final IngredientDAO instance;

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

    public IngredientDTO updateIngredient(IngredientDTO ingredient) {
        IngredientDTO ingredientDTO = new IngredientDTO();
        try {
            String updateIngredient = "{call UpdateIngredient(?,?)}";
            CallableStatement statement = database.callableStatement(updateIngredient);
            statement.setInt(1, ingredient.getIngredientID());
            statement.setString(2, ingredient.getIngredientName());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                getIngredientInfo(rs, ingredientDTO);
            }
            System.out.println("Ingredient successfully updated");
        } catch (Exception e) {
            e.printStackTrace();
            throw new NotFoundException();
        }
        return ingredientDTO;
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
        IngredientDTO ingredientDTO;
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

    public IngredientDTO getIngredient2(int ID) {
        IngredientDTO ingredient;
        try {
            CallableStatement stmt = database.callableStatement("{call GetIngredient(?)}");
            stmt.setInt(1, ID);
            ResultSet resultSet = stmt.executeQuery();
            ingredient = new IngredientDTO();
            while (resultSet.next()) {
                getIngredientInfo(resultSet, ingredient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
        return ingredient;
    }
}
