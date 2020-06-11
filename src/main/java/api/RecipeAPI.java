package api;


import controllers.RecipeController;
import controllers.UserController;
import dto.RecipeDTO;
import dto.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path ("recipe")
public class RecipeAPI {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRecipes() throws Exception{
        return RecipeController.getInstance().getAllRecipes();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRecipe(@QueryParam("ID") int ID,
                               @DefaultValue("0") @QueryParam("recipeID") int recipeID,
                               @DefaultValue("null") @QueryParam("recipeName") String recipeName,
                               @DefaultValue("0") @QueryParam("ingredientID") int ingredientID,
                               @DefaultValue("0") @QueryParam("nonNetto") double nonNetto,
                               @DefaultValue("0") @QueryParam("tolerance") double tolerance) throws Exception {
        return RecipeController.getInstance().updateRecipe(recipeID, recipeName, ingredientID, nonNetto, tolerance);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRecipe(RecipeDTO recipeDTO) throws Exception{
        return RecipeController.getInstance().addRecipe(recipeDTO);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteRecipe(int ID) throws Exception{
        return RecipeController.getInstance().deleteRecipe(ID);
    }


    @Path("/{recipeId}/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecipe(@PathParam("recipeId") int recipeId) throws Exception{
        return RecipeController.getInstance().getRecipe(recipeId);
    }



}
