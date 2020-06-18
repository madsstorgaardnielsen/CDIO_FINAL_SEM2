package api;

import controllers.UserController;
import dto.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
public class UserAPI {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() throws Exception {
        return UserController.getInstance().getAllUsers();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@QueryParam("userId") int userId,
                               @DefaultValue("null") @QueryParam("firstName") String firstName,
                               @DefaultValue("null") @QueryParam("lastName") String lastName,
                               @DefaultValue("null") @QueryParam("initials") String initials,
                               @DefaultValue("null") @QueryParam("role") String role,
                               @DefaultValue("null") @QueryParam("active") String active)
            throws Exception {
        return UserController.getInstance().updateUser(userId, firstName, lastName, initials, role, active);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(UserDTO userDTO) {
        return UserController.getInstance().addUser(userDTO);
    }

    @Path("/{userId}/{role}/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("userId") String userId, @PathParam("role") String role) {
        return UserController.getInstance().getUser(userId, role);
    }
}
