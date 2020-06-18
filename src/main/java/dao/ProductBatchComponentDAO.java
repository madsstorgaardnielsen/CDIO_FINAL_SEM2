package dao;

import dao.exceptions.DatabaseException;
import db.DBConnection;
import dto.ProductBatchComponentDTO;
import dto.ProductBatchComponentDTO;
import dto.ProductBatchDTO;
import dto.RecipeComponentDTO;
import dto.RecipeDTO;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductBatchComponentDAO {
    private static final ProductBatchComponentDAO instance;

    static {
        instance = new ProductBatchComponentDAO();
    }

    PreparedStatement statement;
    private DBConnection database;

    public ProductBatchComponentDAO()  {
        database = new DBConnection();
    }

    public static ProductBatchComponentDAO getInstance() {
        return instance;
    }

    public void addComponentsByRecipe(RecipeDTO recipe, int batchID) {
        // making string for statement
        String statementString = "INSERT INTO ProductBatchComponents (ProductBatchID, IngredientID, amount) VALUES "; //TODO der skal laves procedure i DB hvis vi har tid
        //adding value set for each component
        for (RecipeComponentDTO comp : recipe.getRecipeCompList()) {
            statementString += "(" + batchID + ", " + comp.getIngredientID() + ", " + comp.getNonNetto() + "),";
        }
        statementString = statementString.substring(0, statementString.length() - 1);
        //System.out.println(statementString); //testing
        try {
            statement = database.prepareStatement(statementString);
            statement.executeUpdate();
            System.out.println("ProductBatchComponents successfully added to database");
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    public ArrayList<ProductBatchComponentDTO> getProductBatchComponent(int batchID) {
        ArrayList<ProductBatchComponentDTO> list = null;
        try {
            CallableStatement stmt = database.callableStatement("{call GetBatchInformation(?)}");
            stmt.setString(1, String.valueOf(batchID));

            list = new ArrayList<>();
            ProductBatchComponentDTO componentDTO;
            ResultSet resultSet = stmt.executeQuery();


            while (resultSet.next()) {
                componentDTO = new ProductBatchComponentDTO();
                getProductBatchComponentInfo(resultSet, componentDTO);
                list.add(componentDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }

        return list;
    }

    private void getProductBatchComponentInfo(ResultSet rs, ProductBatchComponentDTO componentDTO) {
        try {
            componentDTO.setId(rs.getInt(2));
            componentDTO.setProductBatchID(rs.getInt(1));
            componentDTO.setIngredientName(rs.getString(3));
            componentDTO.setAmount(rs.getDouble(4));
            componentDTO.setTolerance(rs.getDouble(5));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    public ArrayList<ProductBatchComponentDTO> getCompByBatch(int batchID) {
        ArrayList<ProductBatchComponentDTO> components = null;
        try {
            String statementString = "{call GetCompByBatch(?)}";
            statement = database.callableStatement(statementString);
            statement.setInt(1, batchID);
            components = new ArrayList<>();

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                components.add(getComponentInfo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }

        return components;


    }

    private ProductBatchComponentDTO getComponentInfo(ResultSet rs) {
        ProductBatchComponentDTO comp = new ProductBatchComponentDTO();
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
        return comp;
    }

    public void updateProductBatchComponent(ProductBatchComponentDTO batchComponentDTO) {
        try {
            CallableStatement stmt = database.callableStatement("{call UpdateProductBatchComponent(?,?,?,?,?,?)}");
            stmt.setInt(1, batchComponentDTO.getId());
            stmt.setInt(2, batchComponentDTO.getIngredientBatchID());
            stmt.setInt(3, batchComponentDTO.getLaborantID());
            stmt.setDouble(4, batchComponentDTO.getTara());
            stmt.setDouble(5, batchComponentDTO.getNetto());
            stmt.setDouble(6, batchComponentDTO.getTerminal());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }

    }

    public ProductBatchComponentDTO getProductBatchComponentByID(int batchID) {
        ProductBatchComponentDTO batch = null;
        try {
            CallableStatement stmt = database.callableStatement("{call GetProductBatchComponentByID(?)}");
            stmt.setInt(1, batchID);
            batch = new ProductBatchComponentDTO();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                batch = getComponentInfo(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }

        return batch;
    }
}
