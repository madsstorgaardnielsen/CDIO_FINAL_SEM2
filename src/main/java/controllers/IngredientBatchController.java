package controllers;

import controllers.icontrollers.IIngredientBatchController;
import dao.IngredientBatchDAO;
import dao.IngredientDAO;
import dto.IngredientBatchDTO;
import resources.InputValidation;
import dto.IngredientDTO;


import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * The controller class for "Raavare Batches", it takes input from the API class,
 * validates input and passes the data to the corresponding DAO class, when the DAO class saved/deleted/updated the data,
 * the controller class returns a Response to the corresponding API.
 */
public class IngredientBatchController implements IIngredientBatchController {

    private static final IngredientBatchController instance;

    static {
        instance = new IngredientBatchController();
    }

    private final InputValidation validation;
    private final IngredientBatchDAO ingredientBatchDAO;
    private IngredientBatchDTO ingredientBatchDTO;

    private IngredientBatchController() {
        this.ingredientBatchDAO = new IngredientBatchDAO();
        this.ingredientBatchDTO = new IngredientBatchDTO();
        this.validation = new InputValidation();
    }

    public static IngredientBatchController getInstance() {
        return instance;
    }

    public Response deleteIngredientBatch(int id) {
        ingredientBatchDAO.deleteIngredientBatch(id);
        return Response.ok().build();
    }

    public Response addIngredientBatch(IngredientBatchDTO ingredientBatchDTO) {
        IngredientBatchDTO ingredientBatch = ingredientBatchDAO.getIngredientBatch2(ingredientBatchDTO.getIngredientBatchId());
        IngredientDTO ingredient = IngredientDAO.getInstance().getIngredient2(ingredientBatchDTO.getIngredientId());
        if (validation.ingredientBatchInputValidation(ingredientBatchDTO)) {
            if (ingredientBatch.getIngredientBatchId() != 0) {
                return Response.status(418, "Råvare batch ID: " +
                        ingredientBatchDTO.getIngredientBatchId() + " Eksisterer, prøv venligst et andet ID").build();
            } else if (ingredient.getIngredientID() == 0) {
                return Response.status(418, "Råvare ID: " +
                        ingredientBatchDTO.getIngredientId() + " eksisterer ikke.").build();
            } else {
                ingredientBatchDAO.addIngredientBatch(ingredientBatchDTO);
                return Response.ok().build();
            }
        } else {
            if (!validation.idValidation(ingredientBatchDTO.getIngredientBatchId())) {
                return Response.status(418, "Forkert input<br> Indtastet batchid: "
                        + ingredientBatchDTO.getIngredientBatchId() + "<br> Id skal ligge i intervallet 1-99999999").build();
            } else if (!validation.idValidation(ingredientBatchDTO.getIngredientId())) {
                return Response.status(418, "Forkert input<br> Indtastet råvare id: "
                        + ingredientBatchDTO.getIngredientId() + "<br> Id skal ligge i intervallet 1-99999999").build();
            } else if (!validation.nameValidation(ingredientBatchDTO.getSupplier())) {
                return Response.status(418, "Forkert input <br>Indtastet leverandør: "
                        + ingredientBatchDTO.getSupplier() + "<br> Indtast venligst kun bogstaver").build();
            } else
                return Response.status(418, "Forkert input <br>Indtastet mængde: "
                        + ingredientBatchDTO.getAmount() + "<br> Mængden skal indskrives med 4 decimaler").build();
        }
    }

    public Response updateIngredientBatch(int batchId, int ingredientId, String amount, String supplier) {
        ingredientBatchDTO = new IngredientBatchDTO(batchId, ingredientId, amount, supplier);
        if (validation.ingredientBatchInputValidation(ingredientBatchDTO)) {
            ingredientBatchDAO.updateIngredientBatch(ingredientBatchDTO);
            return Response.ok().build();
        } else {
            return Response.status(418, "Bad input").build();
        }
    }

    public Response getAllIngredientBatch() {
        return Response.ok(ingredientBatchDAO.getAllIngredientBatch()).build();
    }

    public Response getIngredientBatch(int id) {
        return Response.ok(ingredientBatchDAO.getIngredientBatch(id)).build();
    }

    public Response getIngredientBatch2(int id) {
        return Response.ok(ingredientBatchDAO.getIngredientBatch2(id)).build();
    }

    public Response getIngredientBatchByIngredientID(int ID) {
        ArrayList<IngredientBatchDTO> list = IngredientBatchDAO.getInstance().getIngredientBatchByIngredientID(ID);
        return Response.ok(list).build();
    }
}
