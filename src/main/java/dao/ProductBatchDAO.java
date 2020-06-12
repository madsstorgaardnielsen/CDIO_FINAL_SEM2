package dao;


import db.DBConnection;
import dto.ProductBatchDTO;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductBatchDAO {
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
}
