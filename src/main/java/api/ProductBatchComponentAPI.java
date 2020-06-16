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
    public Response updateBatchComponent(ProductBatchComponentDTO batchComponent){
        return ProductBatchComponentController.getInstance().updateProductBatchComponent(batchComponent);
    }

    @Path("/{ID}/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductBatchComponent(@PathParam("ID") int ID) {
        return Response.ok().build(); //ProductBatchComponentController.getInstance().getProductBatchComponent(ID);
    }

    @Path("/{ID}/{tara}/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response validateTara(@PathParam("ID") int ID, @PathParam("tara") double tara) {
        return Response.ok().build(); //ProductBatchComponentController.getInstance().validateTara(ID, tara);
    }

    @Path("/validateBatch/{ID}/{ingredientBatchID}/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response validateIngredientBatch(@PathParam("ID") int ID, @PathParam("ingredientBatchID") int batchID) {
        return Response.ok().build(); //ProductBatchComponent.Controller.getInstance().validateIngredientBatch(ID, batchID);
    }

    @Path("/afvejning/getproductbatchcomponent/{batchID}/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComponentFromBatchID(@PathParam("batchID") int batchId) throws Exception{
        return ProductBatchComponentController.getInstance().getNextComponent(batchId);
    }
}
