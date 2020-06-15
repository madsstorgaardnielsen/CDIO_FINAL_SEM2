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

    Response deleteIngredient(int id);

    Response addIngredient(IngredientDTO ingredientDTO);

    Response updateIngredient(int id, String name);

    Response getAllIngredients() throws Exception;

    Response getIngredient(int id) throws Exception;

}
