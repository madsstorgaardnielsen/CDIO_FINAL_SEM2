package dao;

import dao.exceptions.DatabaseException;
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
    private static final RecipeDAO instance;

    static {
        instance = new RecipeDAO();
    }

    private DBConnection database;

    public RecipeDAO() {
        database = new DBConnection();
    }

    public static RecipeDAO getInstance() {
        return instance;
    }

    public void addRecipe(RecipeDTO recipe) {

        String addRecipe = "{call AddRecipe(?,?)}";
        String addRecipeComponent = "{call AddRecipeComponent(?,?,?,?)}";

        try {
            PreparedStatement statement1 = database.callableStatement(addRecipe);
            statement1.setInt(1, recipe.getRecipeID());
            statement1.setString(2, recipe.getRecipeName());

            statement1.executeUpdate();
            System.out.println("Recipe successfully added to database");

            if (recipe.getRecipeCompList().size() != 0) {
                for (int i = 0; i < recipe.getRecipeCompList().size(); i++) {
                    PreparedStatement statement2 = database.callableStatement(addRecipeComponent);
                    statement2.setInt(1, recipe.getRecipeID());
                    statement2.setInt(2, recipe.getRecipeCompList().get(i).getIngredientID());
                    statement2.setDouble(3, recipe.getRecipeCompList().get(i).getNonNetto());
                    statement2.setDouble(4, recipe.getRecipeCompList().get(i).getTolerance());

                    statement2.executeUpdate();
                    System.out.println("RecipeComponent successfully added to database");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    public void addRecipeOnly(RecipeDTO recipe) {
        String addRecipe = "{call AddRecipe(?,?)}";
        try {
            PreparedStatement statement1 = database.callableStatement(addRecipe);
            statement1.setInt(1, recipe.getRecipeID());
            statement1.setString(2, recipe.getRecipeName());

            statement1.executeUpdate();
            System.out.println("Recipe successfully added to database");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    public RecipeDTO updateRecipe(RecipeDTO recipe) {

        try {
            String updateRecipe = "{call UpdateRecipe(?,?)}";
            for (int i = 0; i < recipe.getRecipeCompList().size(); i++) {
                PreparedStatement statement = database.callableStatement(updateRecipe);
                statement.setInt(1, recipe.getRecipeID());
                statement.setString(2, recipe.getRecipeName());
                statement.executeUpdate();
                System.out.println("Recipe successfully updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
        return recipe;
    }

    public void deleteRecipe(int ID) {

        try {
            String deleteRecipe = "{call DeleteRecipe(?)}";
            PreparedStatement statement = database.callableStatement(deleteRecipe);
            statement.setInt(1, ID);

            statement.executeUpdate();
            System.out.println("Recipe successfully deleted");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    public ArrayList<RecipeDTO> getAllRecipes() {
        ArrayList<RecipeDTO> recipeList = new ArrayList<>();
        try {
            CallableStatement stmt = database.callableStatement("{call GetAllRecipes}");
            ResultSet rs = stmt.executeQuery();
            RecipeDTO recipeDTO;

            while (rs.next()) {
                recipeDTO = new RecipeDTO();
                getRecipeInfo(rs, recipeDTO);
                recipeList.add(recipeDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
        return recipeList;
    }

    public ArrayList<RecipeDTO> getAllRecipesOnly() {
        ArrayList<RecipeDTO> recipeList = new ArrayList<>();
        try {
            CallableStatement stmt = database.callableStatement("{call GetAllRecipesOnly}");
            ResultSet rs = stmt.executeQuery();
            RecipeDTO recipeDTO;

            while (rs.next()) {
                recipeDTO = new RecipeDTO();
                getRecipeOnlyInfo(rs, recipeDTO);
                recipeList.add(recipeDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
        return recipeList;
    }

    public void getRecipeInfo(ResultSet rs, RecipeDTO recipeDTO) {
        try {
            recipeDTO.setRecipeID(rs.getInt(1));
            recipeDTO.setRecipeName(rs.getString(2));

            RecipeComponentDTO component = new RecipeComponentDTO(rs.getInt(1), rs.getInt(3), rs.getDouble(4), rs.getDouble(5));
            recipeDTO.addToRecipeCompList(component);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    public void getRecipeOnlyInfo(ResultSet rs, RecipeDTO recipeDTO) {
        try {
            recipeDTO.setRecipeID(rs.getInt(1));
            recipeDTO.setRecipeName(rs.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    public RecipeDTO getRecipe(int ID) {
        RecipeDTO recipe;
        try {
            CallableStatement stmt = database.callableStatement("{call GetRecipe(?)}");
            stmt.setInt(1, ID);
            recipe = new RecipeDTO();
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                getRecipeInfo(resultSet, recipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
        return recipe;
    }

    public RecipeComponentDTO getRecipeComponent(int recipeID, int ingredientID) {
        RecipeComponentDTO componentDTO;
        try {
            CallableStatement stmt = database.callableStatement("{call GetRecipeComponent(?,?)}");
            stmt.setInt(1, recipeID);
            stmt.setInt(2, ingredientID);

            componentDTO = new RecipeComponentDTO();
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                getRecipeComponentInfo(rs, componentDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }

        return componentDTO;
    }

    public RecipeDTO getRecipeOnly(int ID) {
        RecipeDTO recipe = null;
        try {
            CallableStatement stmt = database.callableStatement("{call GetRecipeOnly(?)}");
            stmt.setInt(1, ID);
            recipe = new RecipeDTO();
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                getRecipeOnlyInfo(resultSet, recipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipe;
    }

    private void getRecipeComponentInfo(ResultSet rs, RecipeComponentDTO recipe) {
        try {
            recipe.setRecipeID(rs.getInt(1));
            recipe.setIngredientID(rs.getInt(2));
            recipe.setNonNetto(rs.getDouble(3));
            recipe.setTolerance(rs.getDouble(4));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }
}