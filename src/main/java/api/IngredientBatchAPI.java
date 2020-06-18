package api;

import controllers.IngredientBatchController;
import dao.IngredientBatchDAO;
import dao.IngredientDAO;
import dto.IngredientBatchDTO;
import dto.IngredientDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateIngredientBatch(@QueryParam("ingredientId") int batchId,
                                          @QueryParam("ingredientName") int ingredientId,
                                          @QueryParam("ingredientName") String ingredientAmount,
                                          @QueryParam("ingredientName") String ingredientSupplier) {
        return IngredientBatchController.getInstance().updateIngredientBatch(batchId, ingredientId, ingredientAmount, ingredientSupplier);
    }

    @Path("/byIngredient/{ingredientID}/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIngredientBatchByIngredient(@PathParam("ingredientID") int ID) throws Exception {
        return IngredientBatchController.getInstance().getIngredientBatchByIngredientID(ID);
    }
}
