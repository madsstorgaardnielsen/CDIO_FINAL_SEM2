package dao.idao;

import dao.RecipeDAO;
import db.DBConnection;
import dto.RecipeDTO;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IRecipeDAO {

    static RecipeDAO getInstance() {
        return null;
    }

    void addRecipe(RecipeDTO recipe);

    RecipeDTO updateRecipe(RecipeDTO recipe);

    void deleteRecipe(int ID);

    ArrayList<RecipeDTO> getAllRecipes();

    void getRecipeInfo(ResultSet rs, RecipeDTO recipeDTO);

    RecipeDTO getRecipe(int ID);
}
