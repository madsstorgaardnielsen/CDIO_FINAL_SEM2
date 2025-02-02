package api;

import controllers.IngredientController;
import dto.IngredientDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

/**
 * The API for "Raavarer" used as an accesspoint from front to backend, makes sure the correct parameters are passed to the backend and returns the response from the backend.
 */
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
    @Produces(MediaType.APPLICATION_JSON)
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
