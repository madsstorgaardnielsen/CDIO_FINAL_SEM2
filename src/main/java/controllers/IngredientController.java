package controllers;

import controllers.icontrollers.IIngredientController;
import dao.IngredientDAO;
import dto.IngredientDTO;
import resources.InputValidation;

import javax.ws.rs.core.Response;

/**
 * The controller class for "Raavare", it takes input from the API class,
 * validates input and passes the data to the corresponding DAO class, when the DAO class saved/deleted/updated the data,
 * the controller class returns a Response to the corresponding API.
 */
public class IngredientController implements IIngredientController {
    private static final IngredientController instance;

    static {
        instance = new IngredientController();
    }

    private final InputValidation validation;
    private final IngredientDAO ingredientDAO;
    private IngredientDTO ingredientDTO;

    private IngredientController() {
        this.ingredientDAO = new IngredientDAO();
        this.ingredientDTO = new IngredientDTO();
        this.validation = new InputValidation();
    }

    public static IngredientController getInstance() {
        return instance;
    }

    public Response deleteIngredient(int id) {
        try {
            ingredientDAO.deleteIngredient(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    public Response addIngredient(IngredientDTO ingredientDTO) {
        if (validation.ingredientInputValidation(ingredientDTO))
            try {
                ingredientDAO.addIngredient(ingredientDTO);
                return Response.ok().build();
            } catch (Exception e) {
                return Response.status(418, "Det indtastede id: " + ingredientDTO.getIngredientID() + " eksisterer i databasen, prøv igen.").build();
            }
        else {
            if (!validation.idValidation(ingredientDTO.getIngredientID())) {
                return Response.status(418, "Forkert input<br> Indtastet Råvare id: " + ingredientDTO.getIngredientID() + "<br> Id skal ligge i intervallet 1-99999999").build();
            } else
                return Response.status(418, "Forkert input<br> Indtastet råvare navn: " + ingredientDTO.getIngredientName() + "<br> Indtast venligst kun bogstaver").build();
        }
    }

    public Response updateIngredient(int id, String name) {
        ingredientDTO = new IngredientDTO(id, name);
        if (validation.ingredientInputValidation(ingredientDTO))
            try {
                IngredientDTO ingredient = ingredientDAO.updateIngredient(ingredientDTO);
                return Response.ok(ingredient).build();
            } catch (Exception e) {
                return Response.serverError().build();
            }
        else {
            return Response.status(418, "Bad input").build();
        }
    }

    public Response getAllIngredients() {
        try {
            return Response.ok(ingredientDAO.getAllIngredients()).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    public Response getIngredient(int id) {
        return Response.ok(ingredientDAO.getIngredient(id)).build();
    }

    public Response getIngredient2(int id) {
        return Response.ok(ingredientDAO.getIngredient2(id)).build();
    }
}
