package api;

import controllers.IngredientBatchController;
import dao.IngredientBatchDAO;
import dao.IngredientDAO;
import dto.IngredientBatchDTO;
import dto.IngredientDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * The API for "Råvarer Batches" used as an accesspoint from front to backend, makes sure the correct parameters are passed to the backend and returns the response from the backend.
 */
@Path("ingredientbatch")
public class IngredientBatchAPI {
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIngredientBatch(@PathParam("id") int id) {
        return IngredientBatchController.getInstance().getIngredientBatch(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllIngredientBatch() {
        return IngredientBatchController.getInstance().getAllIngredientBatch();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addIngredientBatch(IngredientBatchDTO ingredientBatchDTO) {
        return IngredientBatchController.getInstance().addIngredientBatch(ingredientBatchDTO);
    }

    @DELETE
    @Path("{id}")
    public Response deleteIngredientBatch(@PathParam("id") int id) {
        return IngredientBatchController.getInstance().deleteIngredientBatch(id);
    }

    @PUT
    public Response updateIngredientBatch(@QueryParam("batchId") int batchId,
                                          @QueryParam("ingredientId") int ingredientId,
                                          @QueryParam("ingredientAmount") String ingredientAmount,
                                          @QueryParam("ingredientSupplier") String ingredientSupplier) {
        return IngredientBatchController.getInstance().updateIngredientBatch(batchId, ingredientId, ingredientAmount, ingredientSupplier);
    }

    @Path("/byIngredient/{ingredientID}/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIngredientBatchByIngredient(@PathParam("ingredientID") int ID) {
        return IngredientBatchController.getInstance().getIngredientBatchByIngredientID(ID);
    }
}
