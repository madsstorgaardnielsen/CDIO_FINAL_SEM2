package api;


import controllers.RecipeController;
import dto.RecipeDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("recipecomponent")
public class RecipeComponentAPI {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRecipeComponents() throws Exception{
        return RecipeController.getInstance().getAllRecipes();
    }

    @Path("/{recipeComponentId}/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecipeComponent(@PathParam("recipeComponentId") int recipeComponentId) throws Exception{
        return RecipeController.getInstance().getRecipe(recipeComponentId);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRecipeComponent(@QueryParam("recipeID") int recipeID,
                                 @DefaultValue("null") @QueryParam("recipeName") String recipeName) throws Exception {
        return RecipeController.getInstance().updateRecipe(recipeID, recipeName);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRecipeComponent(RecipeDTO recipeDTO) throws Exception{
        return RecipeController.getInstance().addRecipe(recipeDTO);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteRecipeComponent(int ID) throws Exception{
        return RecipeController.getInstance().deleteRecipe(ID);
    }
}
