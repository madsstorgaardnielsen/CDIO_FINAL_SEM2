package controllers;

import dao.RecipeDAO;
import dto.RecipeDTO;
import validation.InputValidation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecipeController {
    private static RecipeController instance;

    static {
        try {
            instance = new RecipeController();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private final InputValidation validation;
    private final RecipeDAO RecipeDAO;
    private RecipeDTO RecipeDTO;

    private RecipeController() throws SQLException {
        this.RecipeDAO = new RecipeDAO();
        this.RecipeDTO = new RecipeDTO();
        this.validation = new InputValidation();
    }

    public static RecipeController getInstance() {
        return instance;
    }

    public void deleteRecipe(int ID) {
        try {
            RecipeDAO.deleteRecipe(ID);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void addRecipe(int recipeID, String recipeName, int ingredientID, double nonNetto, double tolerance) {
        RecipeDTO = new RecipeDTO(recipeID,recipeName,ingredientID,nonNetto,tolerance);
        try {
            RecipeDAO.addRecipe(RecipeDTO);
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateRecipe(int recipeID, String recipeName, int ingredientID, double nonNetto, double tolerance) {
        RecipeDTO = new RecipeDTO(recipeID, recipeName, ingredientID, nonNetto, tolerance);

        try {
            RecipeDAO.updateRecipe(RecipeDTO);
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<RecipeDTO> getAllRecipes() throws Exception {
        return RecipeDAO.getAllRecipes();
    }

    public RecipeDTO getRecipe(int ID) throws Exception {
        return RecipeDAO.getRecipe(ID);
    }

}
