package dao.exceptionmappers;

import dao.exceptions.DatabaseException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Exception mapper for the Custom exception class "DatabaseException".
 * Exception mappers are used to generate a HTML Response from the thrown Exception.
 */
@Provider
public class DatabaseExceptionMapper implements ExceptionMapper<DatabaseException> {
    @Override
    public Response toResponse(DatabaseException e) {
        return Response.status(Response.Status.NOT_FOUND).entity("Database error: "+e.getMessage()+
                "\n The requested resource is: "+ e.getCause()).build();
    }
}
