package api;

import dao.IngredientDAO;
import dto.IngredientDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("ingredient")
public class IngredientAPI {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIngredient(@PathParam("id") int id) throws Exception {
        return Response.ok(new IngredientDAO().getIngredient(id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllIngredients() throws Exception {
        return Response.ok(new IngredientDAO().getAllIngredients()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addIngredient(IngredientDTO ingredientDTO) throws Exception {
        IngredientDAO.getInstance().addIngredient(ingredientDTO);
        return Response.ok().build();
    }


    @POST
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteIngredient(@PathParam("id") int id) throws Exception {
        IngredientDAO.getInstance().deleteIngredient(id);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateIngredient(IngredientDTO ingredientDTO) throws Exception {
        IngredientDAO.getInstance().updateIngredient(ingredientDTO);
        return Response.ok().build();
    }
}
