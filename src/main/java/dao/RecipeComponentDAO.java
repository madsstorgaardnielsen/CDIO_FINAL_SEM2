package dao;

import dao.exceptions.DatabaseException;
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
    private static final RecipeComponentDAO instance;

    static {
        instance = new RecipeComponentDAO();
    }

    private DBConnection database;

    public RecipeComponentDAO() {
        database = new DBConnection();
    }

    public static RecipeComponentDAO getInstance() {
        return instance;
    }

    public void addRecipeComponent(RecipeComponentDTO recipeComponent) {
        String addRecipeComponent = "{call AddRecipeComponent(?,?,?,?)}";
        getRecipeInfo(recipeComponent, addRecipeComponent);
        System.out.println("RecipeComponent successfully added to database");
    }

    public RecipeComponentDTO updateRecipeComponent(RecipeComponentDTO recipe) {
        RecipeComponentDTO recipe2;
        try {
            String updateRecipeComponent = "{call UpdateRecipeComponent(?,?,?,?)}";
            getRecipeInfo(recipe, updateRecipeComponent);
            System.out.println("RecipeComponent successfully updated");

            //TODO
            //det her er muligvis noget rigtig bæ, men kan ikke få ingredient name med tilbage på en anden måde
            recipe2 = getRecipeComponent(recipe.getRecipeID(), recipe.getIngredientID());

        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
        return recipe2;
    }

    private void getRecipeInfo(RecipeComponentDTO recipe, String updateRecipeComponent) {
        try {
            PreparedStatement statement = database.callableStatement(updateRecipeComponent);
            statement.setInt(1, recipe.getRecipeID());
            statement.setInt(2, recipe.getIngredientID());
            statement.setDouble(3, recipe.getNonNetto());
            statement.setDouble(4, recipe.getTolerance());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    public void deleteRecipeComponent(int recipeID, int ingredientID) {
        try {
            String deleteRecipeComponent = "{call DeleteRecipeComponent(?,?)}";
            PreparedStatement statement = database.callableStatement(deleteRecipeComponent);
            statement.setInt(1, recipeID);
            statement.setInt(2, ingredientID);

            statement.executeUpdate();
            System.out.println("RecipeComponent successfully deleted");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    public ArrayList<RecipeComponentDTO> getAllRecipeComponents() {
        ArrayList<RecipeComponentDTO> recipeComponentList;
        try {
            recipeComponentList = new ArrayList<>();
            CallableStatement stmt = database.callableStatement("{call GetAllRecipeComponents}");
            ResultSet rs = stmt.executeQuery();
            RecipeComponentDTO recipeComponentDTO;

            while (rs.next()) {
                recipeComponentDTO = new RecipeComponentDTO();
                getRecipeComponentInfo(rs, recipeComponentDTO);
                recipeComponentList.add(recipeComponentDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
        return recipeComponentList;
    }

    public ArrayList<RecipeComponentDTO> getAllRecipeComponentsFromID(int recipeID) {
        ArrayList<RecipeComponentDTO> recipeComponentList;
        try {
            recipeComponentList = new ArrayList<>();
            CallableStatement stmt = database.callableStatement("{call GetAllRecipeComponentsFromID(?)}");
            stmt.setInt(1, recipeID);
            ResultSet rs = stmt.executeQuery();
            RecipeComponentDTO recipeComponentDTO;

            while (rs.next()) {
                recipeComponentDTO = new RecipeComponentDTO();
                getRecipeComponentInfo(rs, recipeComponentDTO);
                recipeComponentList.add(recipeComponentDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
        return recipeComponentList;
    }

    public void getRecipeComponentInfo(ResultSet rs, RecipeComponentDTO recipeComponentDTO) {
        try {
            recipeComponentDTO.setRecipeID(rs.getInt(1));
            recipeComponentDTO.setIngredientID(rs.getInt(2));
            recipeComponentDTO.setNonNetto(rs.getDouble(3));
            recipeComponentDTO.setTolerance(rs.getDouble(4));
            recipeComponentDTO.setIngredientName(rs.getString(5));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    public RecipeComponentDTO getRecipeComponent(int recipeID, int ingredientID) {
        RecipeComponentDTO recipe;
        try {
            CallableStatement stmt = database.callableStatement("{call GetRecipeComponent(?,?)}");
            stmt.setInt(1, recipeID);
            stmt.setInt(2, ingredientID);
            recipe = new RecipeComponentDTO();
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                getRecipeComponentInfo(resultSet, recipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
        return recipe;
    }
}

