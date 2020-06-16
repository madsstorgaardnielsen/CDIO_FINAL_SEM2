package dao;

import dao.idao.IRecipeDAO;
import db.DBConnection;
import dto.RecipeComponentDTO;
import dto.RecipeDTO;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecipeDAO implements IRecipeDAO {
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

        String addRecipe = "{call AddRecipe(?,?)}";
        String addRecipeComponent = "{call AddRecipeComponent(?,?,?,?)}";

        PreparedStatement statement1 = database.callableStatement(addRecipe);
        statement1.setInt(1, recipe.getRecipeID());
        statement1.setString(2, recipe.getRecipeName());

        try {
            statement1.executeUpdate();
            System.out.println("Recipe successfully added to database");
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Something went wrong with addRecipe()");
        }

        if (recipe.getRecipeCompList().size() != 0) {
            for (int i = 0; i < recipe.getRecipeCompList().size(); i++) {
                PreparedStatement statement2 = database.callableStatement(addRecipeComponent);
                statement2.setInt(1, recipe.getRecipeID());
                statement2.setInt(2, recipe.getRecipeCompList().get(i).getIngredientID());
                statement2.setDouble(3, recipe.getRecipeCompList().get(i).getNonNetto());
                statement2.setDouble(4, recipe.getRecipeCompList().get(i).getTolerance());

                try {
                    statement2.executeUpdate();
                    System.out.println("RecipeComponent successfully added to database");
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new IOException("Something went wrong with addRecipe()");
                }
            }
        }
    }

    public void addRecipeOnly(RecipeDTO recipe) throws IOException, SQLException {

        String addRecipe = "{call AddRecipe(?,?)}";

        PreparedStatement statement1 = database.callableStatement(addRecipe);
        statement1.setInt(1, recipe.getRecipeID());
        statement1.setString(2, recipe.getRecipeName());

        try {
            statement1.executeUpdate();
            System.out.println("Recipe successfully added to database");
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Something went wrong with addRecipe()");
        }
    }

    public RecipeDTO updateRecipe(RecipeDTO recipe) throws IOException, SQLException {

        String updateRecipe = "{call UpdateRecipe(?,?)}";
        for (int i = 0; i < recipe.getRecipeCompList().size(); i++) {
            PreparedStatement statement = database.callableStatement(updateRecipe);
            statement.setInt(1, recipe.getRecipeID());
            statement.setString(2, recipe.getRecipeName());
            try {
                statement.executeUpdate();
                System.out.println("Recipe successfully updated");
            } catch (Exception e) {
                e.printStackTrace();
                throw new IOException("Recipe could no be updated");
            }
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

    public ArrayList<RecipeDTO> getAllRecipesOnly() throws Exception {
        ArrayList<RecipeDTO> recipeList = new ArrayList<>();
        CallableStatement stmt = database.callableStatement("{call GetAllRecipesOnly}");
        ResultSet rs = stmt.executeQuery();
        RecipeDTO recipeDTO;
        try {
            while (rs.next()) {
                recipeDTO = new RecipeDTO();
                getRecipeOnlyInfo(rs, recipeDTO);
                recipeList.add(recipeDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;
    }

    public void getRecipeInfo(ResultSet rs, RecipeDTO recipeDTO) throws SQLException {
        recipeDTO.setRecipeID(rs.getInt(1));
        recipeDTO.setRecipeName(rs.getString(2));

        RecipeComponentDTO component = new RecipeComponentDTO(rs.getInt(1), rs.getInt(3), rs.getDouble(4), rs.getDouble(5));
        recipeDTO.addToRecipeCompList(component);
    }

    public void getRecipeOnlyInfo(ResultSet rs, RecipeDTO recipeDTO) throws SQLException {
        recipeDTO.setRecipeID(rs.getInt(1));
        recipeDTO.setRecipeName(rs.getString(2));
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

    public RecipeComponentDTO getRecipeComponent(int recipeID, int ingredientID) throws Exception {
        CallableStatement stmt = database.callableStatement("{call GetRecipeComponent}");
        stmt.setString(1,String.valueOf(recipeID));
        stmt.setString(2, String.valueOf(ingredientID));

        RecipeComponentDTO componentDTO = new RecipeComponentDTO();
        ResultSet rs = stmt.executeQuery();
        try {
            while (rs.next()) {
                getRecipeComponentInfo(rs, componentDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return componentDTO;
    }

    public RecipeDTO getRecipeOnly(int ID) throws Exception {
        CallableStatement stmt = database.callableStatement("{call GetRecipeOnly(?)}");
        stmt.setInt(1, ID);
        RecipeDTO recipe = new RecipeDTO();
        ResultSet resultSet = stmt.executeQuery();
        try {
            while (resultSet.next()) {
                getRecipeOnlyInfo(resultSet, recipe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;
    }

    private void getRecipeComponentInfo(ResultSet rs, RecipeComponentDTO recipe) throws SQLException {
        recipe.setRecipeID(rs.getInt(1));
        recipe.setIngredientID(rs.getInt(2));
        recipe.setNonNetto(rs.getDouble(3));
        recipe.setTolerance(rs.getDouble(4));
    }
}