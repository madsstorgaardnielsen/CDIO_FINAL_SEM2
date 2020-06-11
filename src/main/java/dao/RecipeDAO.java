package dao;

import db.DBConnection;
import dto.RecipeDTO;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecipeDAO {
    private static RecipeDAO instance;

    static {
        try {
            instance = new RecipeDAO();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private DBConnection database;

    public RecipeDAO() throws SQLException {
        database = new DBConnection();
    }

    public static RecipeDAO getInstance() {
        return instance;
    }

    public void addRecipe(RecipeDTO recipe) throws IOException, SQLException {

        String addRecipe = "{call AddRecipe(?,?,?,?,?)}";
        PreparedStatement statement = database.callableStatement(addRecipe);
        statement.setInt(1, recipe.getRecipeID());
        statement.setString(2, recipe.getRecipeName());
        statement.setInt(3, recipe.getIngredientID());
        statement.setDouble(4, recipe.getNonNetto());
        statement.setDouble(5, recipe.getTolerance());

        try {
            statement.executeUpdate();
            System.out.println("Recipe successfully added to database");
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Something went wrong with createRecipe()");
        }
    }

    public RecipeDTO updateRecipe(RecipeDTO recipe) throws IOException, SQLException {

        String updateRecipe = "{call UpdateRecipe(?,?,?,?,?)}";
        PreparedStatement statement = database.callableStatement(updateRecipe);

        statement.setInt(1, recipe.getRecipeID());
        statement.setString(2, recipe.getRecipeName());
        statement.setInt(3, recipe.getIngredientID());
        statement.setDouble(4, recipe.getNonNetto());
        statement.setDouble(5, recipe.getTolerance());

        try {
            statement.executeUpdate();
            System.out.println("Recipe successfully updated");
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Recipe could no be updated");
        }
        return recipe;
    }

    public void deleteRecipe(int ID) throws IOException, SQLException {

        String deleteRecipe = "{call DeleteRecipe(?)}";
        PreparedStatement statement = database.callableStatement(deleteRecipe);
        statement.setInt(1, ID);
        try {
            statement.executeUpdate();
            System.out.println("Recipe successfully deleted");
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Recipe could no be deleted");
        }
    }

    public ArrayList<RecipeDTO> getAllRecipes() throws Exception {
        ArrayList<RecipeDTO> recipeList = new ArrayList<>();
        CallableStatement stmt = database.callableStatement("{call GetAllRecipes}");
        ResultSet rs = stmt.executeQuery();
        RecipeDTO recipeDTO;
        try {
            while (rs.next()) {
                recipeDTO = new RecipeDTO();
                getRecipeInfo(rs, recipeDTO);
                recipeList.add(recipeDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;
    }

    private void getRecipeInfo(ResultSet rs, RecipeDTO recipeDTO) throws SQLException {
        recipeDTO.setRecipeID(rs.getInt(1));
        recipeDTO.setRecipeName(rs.getString(2));
        recipeDTO.setIngredientID(rs.getInt(3));
        recipeDTO.setNonNetto(rs.getDouble(4));
        recipeDTO.setTolerance(rs.getDouble(5));
    }

    public RecipeDTO getRecipe(int ID) throws Exception {
        CallableStatement stmt = database.callableStatement("{call GetRecipe(?)}");
        stmt.setInt(1, ID);
        RecipeDTO recipe = new RecipeDTO();
        ResultSet resultSet = stmt.executeQuery();
        try {
            while (resultSet.next()) {
                getRecipeInfo(resultSet, recipe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;
    }





}

