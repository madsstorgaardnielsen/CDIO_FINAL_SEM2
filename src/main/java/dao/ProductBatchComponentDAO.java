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

/**
 * The DAO Class for "Produkt batch komponenter" used to access the database and do CRUD operations in the database.
 */
public class ProductBatchComponentDAO {
    private static final ProductBatchComponentDAO instance;

    static {
        instance = new ProductBatchComponentDAO();
    }

    PreparedStatement statement;
    private DBConnection database;

    public ProductBatchComponentDAO() {
        database = new DBConnection();
    }

    public static ProductBatchComponentDAO getInstance() {
        return instance;
    }

    public void addComponentsByRecipe(RecipeDTO recipe, int batchID) {
        // making string for statement
        String statementString = "INSERT INTO ProductBatchComponents (ProductBatchID, IngredientID, amount, tolerance) VALUES ";
        //adding value set for each component
        for (RecipeComponentDTO comp : recipe.getRecipeCompList()) {
            statementString += "(" + batchID + ", " + comp.getIngredientID() + ", " + comp.getNonNetto() + ", " + comp.getTolerance() + "),";
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
        ArrayList<ProductBatchComponentDTO> list;
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
            componentDTO.setProductBatchID(rs.getInt(1));
            componentDTO.setId(rs.getInt(2));
            componentDTO.setIngredientID(rs.getInt(3));
            componentDTO.setAmount(rs.getDouble(5));
            componentDTO.setTolerance(rs.getDouble(6));
            componentDTO.setLaborantID(rs.getInt(8));
            componentDTO.setIngredientName(rs.getString(9));
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
            comp.setTara(rs.getString(6));
            comp.setNetto(rs.getString(7));
            comp.setTerminal(rs.getInt(8));
            comp.setAmount(rs.getDouble(9));
            comp.setTolerance(rs.getDouble(10));
            comp.setIngredientName(rs.getString(11));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
        return comp;
    }

    //sends data for updated productbatchcomponent to database to be saved
    public void updateProductBatchComponent(ProductBatchComponentDTO batchComponentDTO) {
        //format string for mysql command
        String formString = "{call UpdateProductBatchComponent(?,?,?,?,?,?)}";
        try {
            //use format string to make callable statement
            CallableStatement stmt = database.callableStatement(formString);

            //insert new data into statement
            stmt.setInt(1, batchComponentDTO.getId());
            stmt.setInt(2, batchComponentDTO.getIngredientBatchID());
            stmt.setInt(3, batchComponentDTO.getLaborantID());
            stmt.setString(4, batchComponentDTO.getTara());
            stmt.setString(5, batchComponentDTO.getNetto());
            stmt.setDouble(6, batchComponentDTO.getTerminal());

            //call command on database
            stmt.executeUpdate();
        } catch (SQLException e) { //thrown when database intercepts problems
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    public ProductBatchComponentDTO getProductBatchComponentByID(int batchID) {
        ProductBatchComponentDTO batch = new ProductBatchComponentDTO();
        try {
            CallableStatement stmt = database.callableStatement("{call GetProductBatchComponentByID(?)}");
            stmt.setInt(1, batchID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                getComponentInfos(rs, batch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
        return batch;
    }

    private void getComponentInfos(ResultSet rs, ProductBatchComponentDTO comp) {
        try {
            comp.setId(rs.getInt(1));
            comp.setProductBatchID(rs.getInt(2));
            comp.setIngredientID(rs.getInt(3));
            comp.setIngredientBatchID(rs.getInt(4));
            comp.setLaborantID(rs.getInt(5));
            comp.setTara(rs.getString(6));
            comp.setNetto(rs.getString(7));
            comp.setTerminal(rs.getInt(8));
            comp.setAmount(rs.getDouble(9));
            comp.setTolerance(rs.getDouble(10));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }
}
