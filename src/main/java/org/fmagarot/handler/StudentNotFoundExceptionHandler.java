package org.fmagarot.handler;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.Collections;
import java.util.Locale;
import org.fmagarot.core.ErrorResponse;
import org.fmagarot.exception.StudentNotFoundException;

@Provider
public class StudentNotFoundExceptionHandler implements ExceptionMapper<StudentNotFoundException> {

  @Override
  public Response toResponse(StudentNotFoundException exception) {
    String exceptionLocalizedMessage = exception.getLocalizedMessage();
    return Response.status(Response.Status.NOT_FOUND.getStatusCode(), exceptionLocalizedMessage)
        .entity(
            ErrorResponse.builder().reasons(Collections.singletonList(exceptionLocalizedMessage))
                .locale(Locale.ENGLISH).build()).build();
  }
}
