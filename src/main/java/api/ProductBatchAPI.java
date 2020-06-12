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

    @Path("/{batchID}/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductBatch(@PathParam("batchID") int batchID) throws Exception {
        return ProductBatchController.getInstance().getProductBatch(batchID);
    }

    @Path("/{recipeID}/{userID}/")
    @POST
    public Response addBatch(@PathParam("recipeID") int recipeID, @PathParam("userID") int userID) throws Exception {
        return ProductBatchController.getInstance().addProductBatch(recipeID,userID);
    }

    @Path("/afvejning/{batchID}/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBatch(@PathParam("batchID") int batchId){
        return Response.ok().build(); ////ProductBatchController.getInstance().getProductBatch(batchId)
    }

    @Path("/afvejning/{batchID}/")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setStatus(@PathParam("batchID") int batchId) {
        return Response.ok().build(); //ProductBatchController.getInstance().setStatus();
    }

    @Path("/afvejning/getproductbatchcomponent/{batchID}/")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getComponentFromBatchID(@PathParam("batchID") int batchId) {
        return Response.ok().build(); //ProductBatchController.getInstance().getNextComponent(batchId);
    }
}
