package org.fmagarot.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import jakarta.ws.rs.core.Response;
import org.fmagarot.core.ErrorResponse;
import org.fmagarot.exception.StudentNotFoundException;
import org.junit.jupiter.api.Test;

class StudentNotFoundExceptionHandlerTest {


  @Test
  void testToResponseWithStudentNotFoundException() {
    final StudentNotFoundExceptionHandler handler = new StudentNotFoundExceptionHandler();
    StudentNotFoundException studentNotFoundException = new StudentNotFoundException(
        "Student 1 not found");
    Response response = handler.toResponse(studentNotFoundException);
    assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    Object entity = response.getEntity();
    assertNotNull(entity);
    ErrorResponse errorResponse = (ErrorResponse) entity;
    assertEquals(1, errorResponse.getReasons().size());
    assertEquals("Student 1 not found", errorResponse.getReasons().get(0));
  }

}