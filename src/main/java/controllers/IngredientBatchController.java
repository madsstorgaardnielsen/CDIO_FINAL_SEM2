package controllers;

import controllers.icontrollers.IIngredientBatchController;
import dao.IngredientBatchDAO;
import dto.IngredientBatchDTO;
import dto.IngredientDTO;
import validation.InputValidation;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientBatchController implements IIngredientBatchController {

    private static final IngredientBatchController instance;

    static {
        instance = new IngredientBatchController();
    }

    private final InputValidation validation;
    private final IngredientBatchDAO ingredientBatchDAO;
    private IngredientBatchDTO ingredientBatchDTO;

    private IngredientBatchController()  {
        this.ingredientBatchDAO = new IngredientBatchDAO();
        this.ingredientBatchDTO = new IngredientBatchDTO();
        this.validation = new InputValidation();
    }

    public static IngredientBatchController getInstance() {
        return instance;
    }

    public Response deleteIngredientBatch(int id) {
        try {
            ingredientBatchDAO.deleteIngredientBatch(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    public Response addIngredientBatch(IngredientBatchDTO ingredientBatchDTO) {
        if (validation.ingredientBatchInputValidation(ingredientBatchDTO)) {
            try {
                ingredientBatchDAO.addIngredientBatch(ingredientBatchDTO);
                return Response.ok().build();
            } catch (Exception e) {
                return Response.serverError().build();
            }
        } else {
            return Response.status(418, "Bad input").build();
        }
    }

    public Response updateIngredientBatch(int batchId, int ingredientId, double amount, String supplier) {
        ingredientBatchDTO = new IngredientBatchDTO(batchId, ingredientId, amount, supplier);
        if (validation.ingredientBatchInputValidation(ingredientBatchDTO)) {
            try {
                ingredientBatchDAO.updateIngredientBatch(ingredientBatchDTO);
                return Response.ok().build();
            } catch (Exception e) {
                return Response.serverError().build();
            }
        } else {
            return Response.status(418, "Bad input").build();
        }
    }

    public Response getAllIngredientBatch() {
        try {
            ingredientBatchDAO.getAllIngredientBatch();
            return Response.ok(ingredientBatchDAO).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    public Response getIngredientBatch(int id) {
        try {
            ingredientBatchDAO.getIngredientBatch(id);
            return Response.ok(ingredientBatchDAO).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}
