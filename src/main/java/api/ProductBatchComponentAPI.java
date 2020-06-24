package api;

import controllers.icontrollers.IProductBatchComponentController;
import dto.ProductBatchComponentDTO;
import controllers.ProductBatchComponentController;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("productBatchComponent")
public class ProductBatchComponentAPI {

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBatchComponent(ProductBatchComponentDTO batchComponent) {
        return ProductBatchComponentController.getInstance().updateProductBatchComponent(batchComponent);
    }

    @Path("/{ID}/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductBatchComponent(@PathParam("ID") int ID) {
        return Response.ok().build();
    }

    @Path("/validateBatch/{ID}/{ingredientBatchID}/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response validateIngredientBatch(@PathParam("ID") int ID, @PathParam("ingredientBatchID") int batchID) {
        return ProductBatchComponentController.getInstance().validateIngredientBatch(ID, batchID);
    }

    @Path("/afvejning/getproductbatchcomponent/{batchID}/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComponentFromBatchID(@PathParam("batchID") int batchId) {
        return ProductBatchComponentController.getInstance().getNextComponent(batchId);
    }
}
