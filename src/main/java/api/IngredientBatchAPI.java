package api;

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
    public Response getIngredientBatch(@PathParam("id") int id) throws Exception {
        return Response.ok(new IngredientBatchDAO().getIngredientBatch(id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllIngredientBatch() throws Exception {
        return Response.ok(new IngredientBatchDAO().getAllIngredientBatch()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addIngredientBatch(IngredientBatchDTO ingredientBatchDTO) throws Exception {
        IngredientBatchDAO.getInstance().addIngredientBatch(ingredientBatchDTO);
        return Response.ok().build();
    }


    @POST
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteIngredientBatch(@PathParam("id") int id) throws Exception {
        IngredientBatchDAO.getInstance().deleteIngredientBatch(id);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateIngredientBatch(IngredientBatchDTO ingredientBatchDTO) throws Exception {
        IngredientBatchDAO.getInstance().updateIngredientBatch(ingredientBatchDTO);
        return Response.ok().build();
    }
}
