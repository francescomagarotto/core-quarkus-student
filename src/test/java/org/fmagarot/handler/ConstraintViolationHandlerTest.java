package org.fmagarot.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class ConstraintViolationHandlerTest {

  private final ConstraintViolationHandler handler = new ConstraintViolationHandler();

  @Test
  void testToResponseWithViolations() {
    ConstraintViolation<?> violation1 = mock(ConstraintViolation.class);
    ConstraintViolation<?> violation2 = mock(ConstraintViolation.class);
    when(violation1.getMessage()).thenReturn("Student name must be present");
    when(violation2.getMessage()).thenReturn("Email must be well formed");

    Set<ConstraintViolation<?>> violations = Set.of(violation1, violation2);
    ConstraintViolationException exception = new ConstraintViolationException(violations);

    Response response = handler.toResponse(exception);

    assertNotNull(response);
    assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    assertTrue(response.hasEntity());

    List<String> errorMessages = (List<String>) response.getEntity();
    assertNotNull(errorMessages);
    assertEquals(2, errorMessages.size());
    assertTrue(errorMessages.contains("Student name must be present"));
    assertTrue(errorMessages.contains("Email must be well formed"));
  }

  @Test
  void testToResponseWithEmptyViolations() {
    Set<ConstraintViolation<?>> violations = Collections.emptySet();
    ConstraintViolationException exception = new ConstraintViolationException(violations);

    Response response = handler.toResponse(exception);

    assertNotNull(response);
    assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    assertTrue(response.hasEntity());

    List<String> errorMessages = (List<String>) response.getEntity();
    assertNotNull(errorMessages);
    assertTrue(errorMessages.isEmpty());
  }
}
