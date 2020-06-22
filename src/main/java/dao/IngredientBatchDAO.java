package dao;

import dao.exceptions.DatabaseException;
import dao.idao.IIngredientBatchDAO;
import dao.idao.IIngredientDAO;
import db.DBConnection;
import dto.IngredientBatchDTO;

import javax.ws.rs.NotFoundException;
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
        instance = new IngredientBatchDAO();
    }

    PreparedStatement statement;
    private DBConnection database;

    public IngredientBatchDAO() {
        database = new DBConnection();
    }

    public static IngredientBatchDAO getInstance() {
        return instance;
    }

    public void addIngredientBatch(IngredientBatchDTO ingredientBatch) {
        String addIngredientBatch = "{call AddIngredientBatch(?,?,?,?)}";
        setIngredientInfo(ingredientBatch, addIngredientBatch);
        System.out.println("Ingredient batch successfully added to database");
    }

    public void updateIngredientBatch(IngredientBatchDTO ingredientBatch) {
        String updateIngredientBatch = "{call UpdateIngredientBatch(?,?,?,?)}";
        setIngredientInfo(ingredientBatch, updateIngredientBatch);
        System.out.println("Ingredient batch successfully updated");
    }

    private void setIngredientInfo(IngredientBatchDTO ingredientBatch, String updateIngredientBatch) {
        PreparedStatement statement = database.callableStatement(updateIngredientBatch);

        try {
            statement.setInt(1, ingredientBatch.getIngredientBatchId());
            statement.setInt(2, ingredientBatch.getIngredientId());
            statement.setString(3, ingredientBatch.getAmount());
            statement.setString(4, ingredientBatch.getSupplier());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    public void deleteIngredientBatch(int id) {
        try {
            String deleteIngredientBatch = "{call DeleteIngredientBatch(?)}";
            PreparedStatement statement = database.callableStatement(deleteIngredientBatch);
            statement.setInt(1, id);

            statement.executeUpdate();
            System.out.println("Ingredient batch successfully deleted");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ingredient batch could no be deleted");
            throw new DatabaseException();
        }
    }

    public ArrayList<IngredientBatchDTO> getAllIngredientBatch() {
        ArrayList<IngredientBatchDTO> ingredientBatchList = new ArrayList<>();
        try {
            CallableStatement stmt = database.callableStatement("{call GetAllIngredientBatch}");
            ResultSet rs = stmt.executeQuery();
            IngredientBatchDTO ingredientBatchDTO;

            while (rs.next()) {
                ingredientBatchDTO = new IngredientBatchDTO();
                getIngredientBatchInfo(rs, ingredientBatchDTO);
                ingredientBatchList.add(ingredientBatchDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
        return ingredientBatchList;
    }

    public void getIngredientBatchInfo(ResultSet rs, IngredientBatchDTO ingredientBatchDTO) {
        try {
            ingredientBatchDTO.setIngredientBatchId(rs.getInt(1));
            ingredientBatchDTO.setIngredientId(rs.getInt(2));
            ingredientBatchDTO.setAmount(rs.getString(3));
            ingredientBatchDTO.setSupplier(rs.getString(4));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    public IngredientBatchDTO getIngredientBatch(int ID) {
        IngredientBatchDTO ingredientBatch = null;
        try {
            CallableStatement stmt = database.callableStatement("{call GetIngredientBatch(?)}");
            stmt.setInt(1, ID);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                ingredientBatch = new IngredientBatchDTO();
                getIngredientBatchInfo(resultSet, ingredientBatch);
            }

            if (ingredientBatch == null) {
                throw new NotFoundException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
        return ingredientBatch;
    }

    public IngredientBatchDTO getIngredientBatch2(int ID) {
        IngredientBatchDTO ingredientBatch;
        try {
            CallableStatement stmt = database.callableStatement("{call GetIngredientBatch(?)}");
            stmt.setInt(1, ID);
            ResultSet resultSet = stmt.executeQuery();
            ingredientBatch = new IngredientBatchDTO();

            while (resultSet.next()) {
                getIngredientBatchInfo(resultSet, ingredientBatch);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
        return ingredientBatch;
    }

    public ArrayList<IngredientBatchDTO> getIngredientBatchByIngredientID(int ID) {
        ArrayList<IngredientBatchDTO> list = null;
        try {
            statement = database.callableStatement("{call GetBatchByIngredient(?)}");
            statement.setInt(1, ID);
            IngredientBatchDTO batch;
            list = new ArrayList<>();
            ResultSet rs = statement.executeQuery();


            while (rs.next()) {
                batch = new IngredientBatchDTO();
                getIngredientBatchInfo(rs, batch);
                list.add(batch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }

        return list;
    }

    public void subtractFromIngredientAmount(int Id, Double netto) {
        try {
            statement = database.callableStatement("{call SubtractFromIngredientAmount(?,?)}");
            statement.setInt(1, Id);
            statement.setDouble(2, netto);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }

    }
}
