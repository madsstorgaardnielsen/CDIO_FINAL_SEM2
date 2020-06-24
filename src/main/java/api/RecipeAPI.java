package api;


import controllers.RecipeController;
import dto.RecipeDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * The API for "Recept" used as an accesspoint from front to backend, makes sure the correct parameters are passed to the backend and returns the response from the backend.
 */
@Path("recipe")
public class RecipeAPI {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRecipes() {
        return RecipeController.getInstance().getAllRecipesOnly();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRecipe(@QueryParam("recipeID") int recipeID,
                                 @DefaultValue("null") @QueryParam("recipeName") String recipeName) {
        return RecipeController.getInstance().updateRecipe(recipeID, recipeName);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRecipe(RecipeDTO recipeDTO) {
        return RecipeController.getInstance().addRecipeOnly(recipeDTO);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteRecipe(int ID) {
        return RecipeController.getInstance().deleteRecipe(ID);
    }

    @Path("/{recipeId}/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecipe(@PathParam("recipeId") int recipeId) {
        return RecipeController.getInstance().getRecipe(recipeId);
    }
}
