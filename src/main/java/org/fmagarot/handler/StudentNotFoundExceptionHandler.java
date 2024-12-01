package org.fmagarot.handler;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.fmagarot.exception.StudentNotFoundException;

@Provider
public class StudentNotFoundExceptionHandler implements ExceptionMapper<StudentNotFoundException> {

    @Override
    public Response toResponse(StudentNotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND.getStatusCode(), exception.getLocalizedMessage()).build();
    }
}
