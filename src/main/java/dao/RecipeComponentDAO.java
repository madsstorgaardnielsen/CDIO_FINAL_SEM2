package dao;

import db.DBConnection;
import dto.RecipeComponentDTO;
import dto.RecipeDTO;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public void addRecipeComponent(RecipeComponentDTO recipeComponent) throws IOException, SQLException {

        String addRecipeComponent = "{call AddRecipeComponent(?,?,?,?)}";

        PreparedStatement statement = database.callableStatement(addRecipeComponent);
        statement.setInt(1, recipeComponent.getRecipeID());
        statement.setInt(2, recipeComponent.getIngredientID());
        statement.setDouble(3, recipeComponent.getNonNetto());
        statement.setDouble(4, recipeComponent.getTolerance());

        try {
            statement.executeUpdate();
            System.out.println("RecipeComponent successfully added to database");
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Something went wrong with addRecipe()");
        }
    }

    public RecipeComponentDTO updateRecipeComponent(RecipeComponentDTO recipe) throws IOException, SQLException {

        String updateRecipeComponent = "{call UpdateRecipeComponent(?,?,?,?)}";
        PreparedStatement statement = database.callableStatement(updateRecipeComponent);
        statement.setInt(1, recipe.getIngredientID());
        statement.setInt(2, recipe.getIngredientID());
        statement.setDouble(3, recipe.getNonNetto());
        statement.setDouble(4, recipe.getTolerance());
        try {
            statement.executeUpdate();
            System.out.println("Recipe successfully updated");
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Recipe could no be updated");
        }
        return recipe;
    }

    public void deleteRecipeComponent(int recipeID, int ingredientID) throws IOException, SQLException {

        String deleteRecipeComponent = "{call DeleteRecipeComponent(?,?)}";
        PreparedStatement statement = database.callableStatement(deleteRecipeComponent);
        statement.setInt(1, recipeID);
        statement.setInt(2, ingredientID);
        try {
            statement.executeUpdate();
            System.out.println("Recipe successfully deleted");
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Recipe could no be deleted");
        }
    }

    public ArrayList<RecipeComponentDTO> getAllRecipeComponents() throws Exception {
        ArrayList<RecipeComponentDTO> recipeComponentList = new ArrayList<>();
        CallableStatement stmt = database.callableStatement("{call GetAllRecipeComponents}");
        ResultSet rs = stmt.executeQuery();
        RecipeComponentDTO recipeComponentDTO;
        try {
            while (rs.next()) {
                recipeComponentDTO = new RecipeComponentDTO();
                getRecipeComponentInfo(rs, recipeComponentDTO);
                recipeComponentList.add(recipeComponentDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeComponentList;
    }

    public ArrayList<RecipeComponentDTO> getAllRecipeComponentsFromID(int recipeID) throws Exception {
        ArrayList<RecipeComponentDTO> recipeComponentList = new ArrayList<>();
        CallableStatement stmt = database.callableStatement("{call GetAllRecipeComponentsFromID(?)}");
        stmt.setInt(1, recipeID);
        ResultSet rs = stmt.executeQuery();
        RecipeComponentDTO recipeComponentDTO;
        try {
            while (rs.next()) {
                recipeComponentDTO = new RecipeComponentDTO();
                getRecipeComponentInfo(rs, recipeComponentDTO);
                recipeComponentList.add(recipeComponentDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeComponentList;
    }

    public void getRecipeComponentInfo(ResultSet rs, RecipeComponentDTO recipeComponentDTO) throws SQLException {
        recipeComponentDTO.setRecipeID(rs.getInt(1));
        recipeComponentDTO.setIngredientID(rs.getInt(2));
        recipeComponentDTO.setTolerance(rs.getDouble(3));
        recipeComponentDTO.setNonNetto(rs.getDouble(4));
        recipeComponentDTO.setIngredientName(rs.getString(5));
    }

    public RecipeComponentDTO getRecipeComponent(int recipeID, int ingredientID) throws Exception {
        CallableStatement stmt = database.callableStatement("{call GetRecipeComponent(?,?)}");
        stmt.setInt(1, recipeID);
        stmt.setInt(2, ingredientID);
        RecipeComponentDTO recipe = new RecipeComponentDTO();
        ResultSet resultSet = stmt.executeQuery();
        try {
            while (resultSet.next()) {
                getRecipeComponentInfo(resultSet, recipe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;
    }
}

