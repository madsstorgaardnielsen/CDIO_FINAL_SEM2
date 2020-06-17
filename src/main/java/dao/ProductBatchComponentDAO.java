package dao;

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
        String statementString = "INSERT INTO ProductBatchComponents (ProductBatchID, IngredientID, amount, tolerance) VALUES "; //TODO der skal laves procedure i DB hvis vi har tid
        //adding value set for each component
        for (RecipeComponentDTO comp : recipe.getRecipeCompList()) {
            statementString += "(" + batchID + ", " + comp.getIngredientID()+ ", " + comp.getNonNetto()+", " + comp.getTolerance()+ "),";
        }
        statementString = statementString.substring(0, statementString.length() - 1);
        System.out.println(statementString); //testing
        statement = database.prepareStatement(statementString);
        try {
            statement.executeUpdate();
            System.out.println("ProductBatchComponents successfully added to database");
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Something went wrong with addComponentsByRecipe()");
        }
    }

    public ArrayList<ProductBatchComponentDTO> getProductBatchComponent(int batchID) throws Exception {
        CallableStatement stmt = database.callableStatement("{call GetBatchInformation(?)}");
        stmt.setString(1, String.valueOf(batchID));

        ArrayList<ProductBatchComponentDTO> list = new ArrayList<>();
        ProductBatchComponentDTO componentDTO;
        ResultSet resultSet = stmt.executeQuery();
        try {
            while (resultSet.next()) {
                componentDTO = new ProductBatchComponentDTO();
                getProductBatchComponentInfos(resultSet, componentDTO);
                list.add(componentDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void getProductBatchComponentInfos(ResultSet rs, ProductBatchComponentDTO componentDTO) throws SQLException {
        componentDTO.setProductBatchID(rs.getInt(1));
        componentDTO.setId(rs.getInt(2));
        componentDTO.setIngredientID(rs.getInt(3));
        componentDTO.setAmount(rs.getDouble(5));
        componentDTO.setTolerance(rs.getDouble(6));
        componentDTO.setLaborantID(rs.getInt(8));
        componentDTO.setIngredientName(rs.getString(9));
    }

    public ArrayList<ProductBatchComponentDTO> getCompByBatch(int batchID) throws SQLException, IOException {
        String statementString = "{call GetCompByBatch(?)}";
        statement = database.callableStatement(statementString);
        statement.setInt(1, batchID);
        ArrayList<ProductBatchComponentDTO> components = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                components.add(getComponentInfo(rs));
            }
            return components;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Something went wrong with getCompByBatch()");
        }

    }

    private ProductBatchComponentDTO getComponentInfo(ResultSet rs) throws SQLException {
        ProductBatchComponentDTO comp = new ProductBatchComponentDTO();

        comp.setProductBatchID(rs.getInt(3));
        comp.setId(rs.getInt(2));
        comp.setIngredientID(rs.getInt(1));
        comp.setIngredientBatchID(rs.getInt(4));
        comp.setLaborantID(rs.getInt(5));
        comp.setTara(rs.getDouble(6));
        comp.setNetto(rs.getDouble(7));
        comp.setTerminal(rs.getInt(8));
        comp.setAmount(rs.getDouble(9));
        comp.setTolerance(rs.getDouble(10));
        comp.setIngredientName(rs.getString(11));
        return comp;
    }

    public void updateProductBatchComponent(ProductBatchComponentDTO batchComponentDTO) throws SQLException {
        CallableStatement stmt = database.callableStatement("{call UpdateProductBatchComponent(?,?,?,?,?,?)}");
        stmt.setInt(1, batchComponentDTO.getId());
        stmt.setInt(2, batchComponentDTO.getIngredientBatchID());
        stmt.setInt(3, batchComponentDTO.getLaborantID());
        stmt.setDouble(4, batchComponentDTO.getTara());
        stmt.setDouble(5, batchComponentDTO.getNetto());
        stmt.setDouble(6, batchComponentDTO.getTerminal());

        try {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ProductBatchComponentDTO getProductBatchComponentByID(int batchID) throws SQLException {
        CallableStatement stmt = database.callableStatement("{call GetProductBatchComponentByID(?)}");

        stmt.setString(1, String.valueOf(batchID));
        ProductBatchComponentDTO batch = new ProductBatchComponentDTO();
        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                batch = getComponentInfos(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return batch;
    }

    private ProductBatchComponentDTO getComponentInfos(ResultSet rs) throws SQLException {
        ProductBatchComponentDTO comp = new ProductBatchComponentDTO();

        comp.setProductBatchID(rs.getInt(2));
        comp.setId(rs.getInt(1));
        comp.setIngredientID(rs.getInt(3));
        comp.setIngredientBatchID(rs.getInt(4));
        comp.setLaborantID(rs.getInt(5));
        comp.setTara(rs.getDouble(6));
        comp.setNetto(rs.getDouble(7));
        comp.setTerminal(rs.getInt(8));
        comp.setAmount(rs.getDouble(9));
        comp.setTolerance(rs.getDouble(10));
        return comp;
    }
}
