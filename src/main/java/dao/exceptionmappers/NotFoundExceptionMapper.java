package dao.exceptionmappers;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Arrays;

/**
 * Exception mapper for the NotFoundException, which is standard in java 8
 * Exception mappers are used to generate a HTML Response from the thrown Exception.
 */
@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    @Override
    public Response toResponse(NotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND).entity("Error: "+e.getMessage() +
                "\n The requested resource is: "+ e.getCause()+
                "\n Resource could not be found.").build();
    }
}
