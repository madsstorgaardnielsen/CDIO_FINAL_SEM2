package controllers;

import dao.IngredientDAO;
import dao.UserDAO;
import dto.IngredientDTO;
import validation.InputValidation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientController {
    private static IngredientController instance;

    static {
        try {
            instance = new IngredientController();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private final InputValidation validation;
    private final IngredientDAO ingredientDAO;
    private IngredientDTO ingredientDTO;

    private IngredientController() throws SQLException {
        this.ingredientDAO = new IngredientDAO();
        this.ingredientDTO = new IngredientDTO();
        this.validation = new InputValidation();
    }

    public static IngredientController getInstance() {
        return instance;
    }

    public void deleteIngredient(int id) {
        try {
            ingredientDAO.deleteIngredient(id);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void addIngredient(int id, String name, String supplier) {
        ingredientDTO = new IngredientDTO(id, name, supplier);
        try {
            ingredientDAO.addIngredient(ingredientDTO);
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateIngredient(int id, String name, String supplier) {
        ingredientDTO = new IngredientDTO(id, name, supplier);

        try {
            ingredientDAO.updateIngredient(ingredientDTO);
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<IngredientDTO> getAllIngredients() throws Exception {
        return ingredientDAO.getAllIngredients();
    }

    public IngredientDTO getIngredient(int id) throws Exception {
        return ingredientDAO.getIngredient(id);
    }


}
