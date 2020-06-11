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

    void addRecipe(RecipeDTO recipe) throws IOException, SQLException;

    void updateRecipe(RecipeDTO recipe) throws IOException, SQLException;

    void deleteRecipe(int ID) throws IOException, SQLException;

    ArrayList<RecipeDTO> getAllRecipes() throws Exception;

    void getRecipeInfo(ResultSet rs, RecipeDTO recipeDTO) throws SQLException;

    RecipeDTO getRecipe(int ID) throws Exception;
}
