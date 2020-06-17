package dao;


import dao.idao.IProductBatchDAO;
import db.DBConnection;
import dto.IngredientDTO;
import dto.ProductBatchDTO;
import dto.UserDTO;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ArrayList;

public class ProductBatchDAO implements IProductBatchDAO {
    private static ProductBatchDAO instance;

    static {
        try {
            instance = new ProductBatchDAO();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    PreparedStatement statement;
    private DBConnection database;

    public ProductBatchDAO() throws SQLException {
        database = new DBConnection();
    }

    public static ProductBatchDAO getInstance() {
        return instance;
    }

    public int addProductBatch(int recipeID, int userID) throws SQLException, IOException {
        String addProductBatchString = "{call AddProductBatch(?,?)}";
        statement = database.callableStatement(addProductBatchString);

        statement.setInt(1, recipeID);
        statement.setInt(2, userID);

        try {
            //adding batch while getting the new ID back
            ResultSet rs = statement.executeQuery();
            System.out.println("ProductBatch successfully added to database");
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Something went wrong with addProductBatch()");
        }
    }

    public void updateProductBatch(ProductBatchDTO productBatchDTO) throws IOException, SQLException {

        String updatetProductBatch = "{call UpdateProductBatch(?,?,?,?,?,?,?,?)}";
        PreparedStatement statement = database.callableStatement(updatetProductBatch);

        statement.setInt(1, productBatchDTO.getProductBatchId());
        statement.setInt(2, productBatchDTO.getRecipeId());
        statement.setInt(3, productBatchDTO.getStatus());
        statement.setInt(4, productBatchDTO.getUserId());
        statement.setString(5, productBatchDTO.getCreationDate());
        statement.setString(6, productBatchDTO.getFinishDate());
        statement.setDouble(7, productBatchDTO.getTaraSum());
        statement.setDouble(8, productBatchDTO.getNettoSum());

        try {
            statement.executeUpdate();
            System.out.println("Product Batch successfully updated");
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Product Batch could no be updated");
        }
    }

    public void deleteProductBatch(int id) throws IOException, SQLException {

        String deleteIngredient = "{call DeleteProductBatch(?)}";
        PreparedStatement statement = database.callableStatement(deleteIngredient);
        statement.setInt(1, id);

        try {
            statement.executeUpdate();
            System.out.println("Product Batch successfully deleted");
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Product Batch could no be deleted");
        }
    }


    public ArrayList<ProductBatchDTO> getAllProductBatch() throws Exception {
        ArrayList<ProductBatchDTO> productBatchList = new ArrayList<>();
        CallableStatement stmt = database.callableStatement("{call GetAllProductBatch}");
        ResultSet rs = stmt.executeQuery();
        ProductBatchDTO productBatchDTO;
        try {
            while (rs.next()) {
                productBatchDTO = new ProductBatchDTO();
                getBatchInfo(rs, productBatchDTO);
                productBatchList.add(productBatchDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productBatchList;
    }


    private void getBatchInfo(ResultSet rs, ProductBatchDTO batch) throws Exception {
        batch.setProductBatchId(rs.getInt(1));
        batch.setRecipeId(rs.getInt(2));
        batch.setStatus(rs.getInt(3));
        batch.setUserId(rs.getInt(4));
        batch.setCreationDate(rs.getDate(5).toString());

        try {
            batch.setFinishDate(rs.getDate(6).toString());
        } catch (NullPointerException ignored) {

        }
        batch.setTaraSum(rs.getDouble(7));
        batch.setNettoSum(rs.getDouble(8));
    }

    public ProductBatchDTO getProductBatchFromRecipeIdUserId(int recipeId, int userId) throws SQLException, IOException {
        String getProductBatchString = "{call GetProductBatchFromRecipeIdUserIdProductBatchId(?,?)}";
        statement = database.callableStatement(getProductBatchString);
        statement.setInt(1, recipeId);
        statement.setInt(2, userId);



        try {
            ResultSet rs = statement.executeQuery();
            ProductBatchDTO product = new ProductBatchDTO();
            while (rs.next()) {
                getBatchInfo(rs, product);
            }

            return product;

        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Something went wrong retrieving batch from database");
        }
    }

    public void deleteProductBatchWithRecipeIdUserId(int recipeId, int userId) throws IOException, SQLException {

        String deleteIngredient = "{call DeleteProductBatchFromRecipeIdUserId(?,?)}";
        PreparedStatement statement = database.callableStatement(deleteIngredient);
        statement.setInt(1, recipeId);
        statement.setInt(2, userId);

        try {
            statement.executeUpdate();
            System.out.println("Product Batch successfully deleted");
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Product Batch could no be deleted");
        }
    }

    public ProductBatchDTO getProductBatch(int batchID) throws SQLException, IOException {
        String getProductBatchString = "{call GetProductBatch(?)}";
        statement = database.callableStatement(getProductBatchString);
        statement.setInt(1, batchID);

        try {
            ResultSet rs = statement.executeQuery();
            System.out.println("ProductBatch successfully returned from database");
            ProductBatchDTO product = new ProductBatchDTO();
            while (rs.next()) {
                getBatchInfo(rs, product);
            }

            return product;

        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Something went wrong with addProductBatch()");
        }
    }

    public void setStatusDone(ProductBatchDTO batch) throws SQLException {
        statement = database.callableStatement("{call SetStatusDone(?,?,?)}");
        statement.setString(1, String.valueOf(batch.getProductBatchId()));
        statement.setString(2, String.valueOf(batch.getTaraSum()));
        statement.setString(3, String.valueOf(batch.getNettoSum()));

        try {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setStatus(int batchID) throws SQLException {
        statement = database.callableStatement("{call SetStatus(?)}");
        statement.setString(1, String.valueOf(batchID));

        try {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
