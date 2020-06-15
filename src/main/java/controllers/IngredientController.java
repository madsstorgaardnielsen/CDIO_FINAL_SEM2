package controllers;

import controllers.icontrollers.IIngredientController;
import dao.IngredientDAO;
import dto.IngredientDTO;
import jdk.nashorn.internal.ir.ReturnNode;
import validation.InputValidation;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientController implements IIngredientController {
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
                return Response.serverError().build();
            }
        else {
            return Response.status(418, "Bad input").build();
        }
    }

    public Response updateIngredient(int id, String name) {
        ingredientDTO = new IngredientDTO(id, name);
        if (validation.ingredientInputValidation(ingredientDTO))
            try {
                ingredientDAO.updateIngredient(ingredientDTO);
                return Response.ok().build();
            } catch (Exception e) {
                return Response.serverError().build();
            }
        else {
            return Response.status(418, "Bad input").build();
        }
    }

    public Response getAllIngredients() {
        try {
            ingredientDAO.getAllIngredients();
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    public Response getIngredient(int id) {
        try {
            ingredientDAO.getIngredient(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

}
