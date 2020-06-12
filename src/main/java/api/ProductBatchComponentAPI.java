package api;

import dto.ProductBatchComponentDTO;
import controllers.ProductBatchComponentController;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("productBatchComponent")
public class ProductBatchComponentAPI {
    @Path("/{ID}/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductBatchComponent(@PathParam("ID") int ID) {
        return Response.ok().build(); //ProductBatchComponentController.getInstance().getProductBatchComponent(ID);
    }

    @Path("/{ID}/{tara}/")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response setTara(@PathParam("ID") int ID, @PathParam("tara") int tara) {
        return Response.ok().build(); //ProductBatchComponentController.getInstance().setTara(ID, tara);
    }

}
