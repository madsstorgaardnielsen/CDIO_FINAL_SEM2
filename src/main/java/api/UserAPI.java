package api;

import controllers.UserController;
import dto.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("user")
public class UserAPI {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers(@Context UriInfo uriInfo) throws Exception{
        return UserController.getInstance().getAllUsers(uriInfo);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@Context UriInfo uriInfo) throws Exception {
        return UserController.getInstance().updateUser(uriInfo);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(UserDTO userDTO) throws Exception{
        return UserController.getInstance().addUser(userDTO);
    }
}
