package dao;

import db.DBConnection;
import dto.RecipeComponentDTO;

import java.sql.SQLException;

public class RecipeComponentDAO {
    private static RecipeComponentDAO instance;

    static {
        try {
            instance = new RecipeComponentDAO();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private DBConnection database;

    public RecipeComponentDAO() throws SQLException {
        database = new DBConnection();
    }

    public static RecipeComponentDAO getInstance() {
        return instance;
    }
}
