package api;

import dto.ProductBatchDTO;
import controllers.ProductBatchController;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("productBatch")
public class ProductBatchAPI {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProductBatches() {
        return ProductBatchController.getInstance().getAllProductBatch();
    }

    @Path("/{batchID}/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductBatch(@PathParam("batchID") int batchID) {
        return ProductBatchController.getInstance().getProductBatch(batchID);
    }

    @Path("/{recipeID}/{userID}/")
    @POST
    public Response addBatch(@PathParam("recipeID") int recipeID, @PathParam("userID") int userID) {
        return ProductBatchController.getInstance().addProductBatch(recipeID, userID);
    }

    @Path("/afvejning/{batchID}/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBatch(@PathParam("batchID") int batchId) {
        return ProductBatchController.getInstance().getProductBatch(batchId);
    }

    @Path("/afvejning/{batchID}/")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setStatus(@PathParam("batchID") int batchId) {
        return Response.ok().build(); //ProductBatchController.getInstance().setStatus();
    }
}
