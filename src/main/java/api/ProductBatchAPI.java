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
    @Path("/afvejning/{batchID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBatch(@PathParam("batchID") int batchId){
        return Response.ok().build(); ////ProductBatchController.getInstance().getProductBatch(batchId)
    }

    @Path("/afvejning/{batchID}/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecipeName(@PathParam("batchID") int batchId) {
        return Response.ok(batchId).build(); //ProductBatchController.getInstance().getRecipeName(batchId);
    }

    @Path("/afvejning/{batchID}/")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setStatus(@PathParam("batchID") int batchId) {
        return Response.ok().build(); //ProductBatchController.getInstance().setStatus();
    }
}
