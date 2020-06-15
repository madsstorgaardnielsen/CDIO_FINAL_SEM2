package controllers.icontrollers;

import controllers.IngredientController;
import dao.IngredientDAO;
import dto.IngredientDTO;
import validation.InputValidation;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IIngredientController {




    public static IngredientController getInstance() {
        return null;
    }

    public Response deleteIngredient(int id);

    public Response addIngredient(IngredientDTO ingredientDTO);

    public Response updateIngredient(int id, String name);

    public Response getAllIngredients() throws Exception ;

    public Response getIngredient(int id) throws Exception ;

}
