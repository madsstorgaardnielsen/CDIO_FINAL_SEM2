package controllers.icontrollers;

import dto.IngredientDTO;

import javax.ws.rs.core.Response;

public interface IIngredientController {

    Response deleteIngredient(int id);

    Response addIngredient(IngredientDTO ingredientDTO);

    Response updateIngredient(int id, String name);

    Response getAllIngredients() throws Exception;

    Response getIngredient(int id) throws Exception;

}
