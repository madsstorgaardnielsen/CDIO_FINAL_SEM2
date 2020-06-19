package controllers;

import controllers.icontrollers.IIngredientBatchController;
import dao.IngredientBatchDAO;
import dto.IngredientBatchDTO;
import validation.InputValidation;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

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
            if (!validation.idValidation(ingredientBatchDTO.getIngredientBatchId())) {
                return Response.status(418, "Forkert input<br> Indtastet batchid: " + ingredientBatchDTO.getIngredientBatchId() + "<br> Id skal ligge i intervallet 1-99999999").build();
            } else if (!validation.idValidation(ingredientBatchDTO.getIngredientId())) {
                return Response.status(418, "Forkert input<br> Indtastet råvare id: " + ingredientBatchDTO.getIngredientId() + "<br> Id skal ligge i intervallet 1-99999999").build();
            } else if (!validation.nameValidation(ingredientBatchDTO.getSupplier())) {
                return Response.status(418, "Forkert input <br>Indtastet leverandør: " + ingredientBatchDTO.getAmount() + "<br> Indtast venligst kun bostaver").build();
            } else
                return Response.status(418, "Forkert input <br>Indtastet mængde: " + ingredientBatchDTO.getAmount() + "<br> Mængden skal indskrives med 4 decimaler").build();
        }
    }

    public Response updateIngredientBatch(int batchId, int ingredientId, String amount, String supplier) {
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
            return Response.ok(ingredientBatchDAO.getAllIngredientBatch()).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    public Response getIngredientBatch(int id) {
        try {
            return Response.ok(ingredientBatchDAO.getIngredientBatch(id)).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    public Response getIngredientBatchByIngredientID(int ID) throws Exception {
        try {
            ArrayList<IngredientBatchDTO> list = IngredientBatchDAO.getInstance().getIngredientBatchByIngredientID(ID);
            return Response.ok(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
