package org.fmagarot.handler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Provider
public class ConstraintViolationHandler implements ExceptionMapper<ConstraintViolationException> {

  @Override
  public Response toResponse(ConstraintViolationException e) {
    Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
    List<String> errors = constraintViolations.stream().map(ConstraintViolation::getMessage)
        .collect(Collectors.toList());
    return Response
        .status(Response.Status.BAD_REQUEST)
        .entity(errors)
        .build();
  }
}
