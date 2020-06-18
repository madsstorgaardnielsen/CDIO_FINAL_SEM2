package api;


import controllers.RecipeComponentController;
import controllers.RecipeController;
import dto.RecipeComponentDTO;
import dto.RecipeDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("recipecomponent")
public class RecipeComponentAPI {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRecipeComponents() {
        return RecipeComponentController.getInstance().getAllRecipeComponents();
    }

    @Path("/{recipeId}/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecipeComponentsFromID(@PathParam("recipeId") int recipeId) {
        return RecipeComponentController.getInstance().getAllRecipeComponentsFromID(recipeId);
    }

    /*    @Path("/{recipeComponentId}/")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getRecipeComponent(@PathParam("recipeComponentId") int recipeComponentId,
                                           @QueryParam("recipeComponentIngredientID") int recipeComponentIngredientID) throws Exception {
            return RecipeComponentController.getInstance().getRecipeComponent(recipeComponentId, recipeComponentIngredientID);
        }
    */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRecipeComponent(@QueryParam("recipeID") int recipeID,
                                          @QueryParam("ingredientId") int recipeCompIngredientID,
                                          @QueryParam("nonNetto") double recipeCompNonNetto,
                                          @QueryParam("tolerance") double recipeCompTolerance) {
        return RecipeComponentController.getInstance().updateRecipeComponent(recipeID, recipeCompIngredientID, recipeCompNonNetto, recipeCompTolerance);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRecipeComponent(RecipeComponentDTO recipeComponentDTO) {
        return RecipeComponentController.getInstance().addRecipeComponent(recipeComponentDTO);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteRecipeComponent(@QueryParam("recipeCompId") int recipeCompId,
                                          @QueryParam("recipeCompIngredientID") int recipeCompIngredientID) {
        return RecipeComponentController.getInstance().deleteRecipeComponent(recipeCompId, recipeCompIngredientID);
    }
}
