package dao;


import dao.exceptions.DatabaseException;
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
    private static final ProductBatchDAO instance;

    static {
        instance = new ProductBatchDAO();
    }

    PreparedStatement statement;
    private DBConnection database;

    public ProductBatchDAO() {
        database = new DBConnection();
    }

    public static ProductBatchDAO getInstance() {
        return instance;
    }

    public int addProductBatch(int recipeID, int userID) {
        String addProductBatchString = "{call AddProductBatch(?,?)}";
        try {
            statement = database.callableStatement(addProductBatchString);
            statement.setInt(1, recipeID);
            statement.setInt(2, userID);
            //adding batch while getting the new ID back
            ResultSet rs = statement.executeQuery();
            System.out.println("ProductBatch successfully added to database");
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    public void updateProductBatch(ProductBatchDTO productBatchDTO) {
        try {
            String updatetProductBatch = "{call UpdateProductBatch(?,?,?,?,?,?,?,?)}";
            PreparedStatement statement = database.callableStatement(updatetProductBatch);
            statement.setInt(1, productBatchDTO.getProductBatchId());
            statement.setInt(2, productBatchDTO.getRecipeId());
            statement.setInt(3, productBatchDTO.getStatus());
            statement.setInt(4, productBatchDTO.getUserId());
            statement.setString(5, productBatchDTO.getCreationDate());
            statement.setString(6, productBatchDTO.getFinishDate());
            statement.setString(7, productBatchDTO.getTaraSum());
            statement.setString(8, productBatchDTO.getNettoSum());
            statement.executeUpdate();
            System.out.println("Product Batch successfully updated");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    public void deleteProductBatch(int id) {
        try {
            String deleteIngredient = "{call DeleteProductBatch(?)}";
            PreparedStatement statement = database.callableStatement(deleteIngredient);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Product Batch successfully deleted");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    public ArrayList<ProductBatchDTO> getAllProductBatch() {
        ArrayList<ProductBatchDTO> productBatchList = new ArrayList<>();
        try {
            CallableStatement stmt = database.callableStatement("{call GetAllProductBatch}");
            ResultSet rs = stmt.executeQuery();
            ProductBatchDTO productBatchDTO;
            while (rs.next()) {
                productBatchDTO = new ProductBatchDTO();
                getBatchInfo(rs, productBatchDTO);
                productBatchList.add(productBatchDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
        return productBatchList;
    }

    private void getBatchInfo(ResultSet rs, ProductBatchDTO batch) {
        try {
            batch.setProductBatchId(rs.getInt(1));
            batch.setRecipeId(rs.getInt(2));
            batch.setStatus(rs.getInt(3));
            batch.setUserId(rs.getInt(4));
            batch.setCreationDate(rs.getDate(5).toString());
            try {
                batch.setFinishDate(rs.getDate(6).toString());
            } catch (NullPointerException ignored) {
            }
            if (batch.getFinishDate() == null) {
                batch.setFinishDate(" ");
            }
            batch.setTaraSum(rs.getString(7));
            batch.setNettoSum(rs.getString(8));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    public ProductBatchDTO getProductBatchFromRecipeIdUserId(int recipeId, int userId) {
        String getProductBatchString = "{call GetProductBatchFromRecipeIdUserIdProductBatchId(?,?)}";
        ProductBatchDTO product;
        try {
            statement = database.callableStatement(getProductBatchString);
            statement.setInt(1, recipeId);
            statement.setInt(2, userId);
            ResultSet rs = statement.executeQuery();
            product = new ProductBatchDTO();
            while (rs.next()) {
                getBatchInfo(rs, product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
        return product;
    }

    public void deleteProductBatchWithRecipeIdUserId(int recipeId, int userId) {
        try {
            String deleteIngredient = "{call DeleteProductBatchFromRecipeIdUserId(?,?)}";
            PreparedStatement statement = database.callableStatement(deleteIngredient);
            statement.setInt(1, recipeId);
            statement.setInt(2, userId);
            statement.executeUpdate();
            System.out.println("Product Batch successfully deleted");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    public ProductBatchDTO getProductBatch(int batchID) {
        String getProductBatchString = "{call GetProductBatch(?)}";
        ProductBatchDTO product;
        try {
            statement = database.callableStatement(getProductBatchString);
            statement.setInt(1, batchID);
            ResultSet rs = statement.executeQuery();
            System.out.println("ProductBatch successfully returned from database");
            product = new ProductBatchDTO();
            while (rs.next()) {
                getBatchInfo(rs, product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
        return product;
    }

    public void setStatusDone(ProductBatchDTO batch) {
        try {
            statement = database.callableStatement("{call SetStatusDone(?,?,?)}");
            statement.setInt(1, batch.getProductBatchId());
            statement.setString(2, batch.getTaraSum());
            statement.setString(3, batch.getNettoSum());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    public void setStatus(int batchID) {
        statement = database.callableStatement("{call SetStatus(?)}");
        try {
            statement.setInt(1, batchID);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }
}
