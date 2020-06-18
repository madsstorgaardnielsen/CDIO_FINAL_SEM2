package dao.exceptionmappers;

import dao.exceptions.DatabaseConnectionException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DatabaseConnectionExceptionMapper implements ExceptionMapper<DatabaseConnectionException> {
    @Override
    public Response toResponse(DatabaseConnectionException e) {
        return Response.status(Response.Status.NOT_FOUND).entity("Database connection error: "+e.getMessage()+
                "Cause: "+e.getCause()).build();
    }
}
