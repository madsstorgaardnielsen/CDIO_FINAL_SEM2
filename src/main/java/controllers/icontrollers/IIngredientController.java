package controllers.icontrollers;

import controllers.IngredientController;
import dao.IngredientDAO;
import dto.IngredientDTO;
import validation.InputValidation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IIngredientController {




    public static IngredientController getInstance() {
        return null;
    }

    public void deleteIngredient(int id);

    public void addIngredient(int id, String name, String supplier);

    public void updateIngredient(int id, String name, String supplier);

    public ArrayList<IngredientDTO> getAllIngredients() throws Exception ;

    public IngredientDTO getIngredient(int id) throws Exception ;

}
