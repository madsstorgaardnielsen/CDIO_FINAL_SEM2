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
        ingredientBatchDAO.deleteIngredientBatch(id);
        return Response.ok().build();
    }

    public Response addIngredientBatch(IngredientBatchDTO ingredientBatchDTO) {
        if (validation.ingredientBatchInputValidation(ingredientBatchDTO)) {
            ingredientBatchDAO.addIngredientBatch(ingredientBatchDTO);
            return Response.ok().build();
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

    public Response getIngredientBatchByIngredientID(int ID) {
        ArrayList<IngredientBatchDTO> list = IngredientBatchDAO.getInstance().getIngredientBatchByIngredientID(ID);
        return Response.ok(list).build();
    }
}
