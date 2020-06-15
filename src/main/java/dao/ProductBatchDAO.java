package dao;


import dao.idao.IProductBatchDAO;
import db.DBConnection;
import dto.IngredientDTO;
import dto.ProductBatchDTO;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        PreparedStatement statement = database.callableStatement(addProductBatchString);

        statement.setInt(1,recipeID);
        statement.setInt(2,userID);

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

    public ProductBatchDTO getProductBatch(int batchID) {
        try {
            PreparedStatement statement = database.callableStatement("{call GetProductBatch(?)}");
            statement.setInt(1, batchID);
            ProductBatchDTO batch = new ProductBatchDTO();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                getBatchInfo(rs, batch);
            }
            return batch;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ProductBatchDTO();
    }

    public void updateProductBatch(ProductBatchDTO productBatchDTO) throws IOException, SQLException {

        String updatetProductBatch = "{call UpdateProductBatch(?,?,?,?,?,?,?,?,?)}";
        PreparedStatement statement = database.callableStatement(updatetProductBatch);

        statement.setInt(1, productBatchDTO.getProductBatchId());
        statement.setInt(2, productBatchDTO.getRecipeId());
        statement.setInt(3, productBatchDTO.getStatus());
        statement.setInt(4,productBatchDTO.getUserId());
        statement.setString(5,productBatchDTO.getCreationDate());
        statement.setString(6,productBatchDTO.getFinishDate());
        statement.setInt(7,productBatchDTO.getIngredientBatchId());
        statement.setDouble(8,productBatchDTO.getTaraSum());
        statement.setDouble(9,productBatchDTO.getNettoSum());

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
    }
}
