package api;

import controllers.IngredientController;
import dao.IngredientDAO;
import dto.IngredientDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;

@Path("ingredient")
public class IngredientAPI {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIngredient(@PathParam("id") int id) {
        return IngredientController.getInstance().getIngredient(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllIngredients() {
        return IngredientController.getInstance().getAllIngredients();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateIngredient(@QueryParam("ingredientId") int ingredientId,
                                     @DefaultValue("null") @QueryParam("ingredientName") String ingredientName) {
        return IngredientController.getInstance().updateIngredient(ingredientId, ingredientName);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addIngredient(IngredientDTO ingredientDTO) {
        return IngredientController.getInstance().addIngredient(ingredientDTO);
    }


    @DELETE
    @Path("{id}")
    public Response deleteIngredient(@PathParam("id") int id) {
        return IngredientController.getInstance().deleteIngredient(id);
    }


}
