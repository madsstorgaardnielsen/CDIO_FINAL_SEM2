package api;


import controllers.RecipeController;
import dto.RecipeDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("recipe")
public class RecipeAPI {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRecipes() throws Exception {
        return RecipeController.getInstance().getAllRecipes();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRecipe(@QueryParam("recipeID") int recipeID,
                                 @DefaultValue("null") @QueryParam("recipeName") String recipeName) throws Exception {
        return RecipeController.getInstance().updateRecipe(recipeID, recipeName);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRecipe(RecipeDTO recipeDTO) throws Exception {
        return RecipeController.getInstance().addRecipeOnly(recipeDTO);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteRecipe(int ID) throws Exception {
        return RecipeController.getInstance().deleteRecipe(ID);
    }


    @Path("/{recipeId}/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecipe(@PathParam("recipeId") int recipeId) throws Exception {
        return RecipeController.getInstance().getRecipe(recipeId);
    }
}
