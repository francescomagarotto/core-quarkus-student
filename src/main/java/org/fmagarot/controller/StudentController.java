package org.fmagarot.controller;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fmagarot.entities.StudentEntity;
import org.fmagarot.resources.StudentResource;
import org.fmagarot.service.StudentService;

@Path("/students")
@RequiredArgsConstructor
public class StudentController {

  private final StudentService studentService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<StudentEntity> students() {
    return studentService.getAllStudents();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createStudent(@Valid StudentResource student) {
    StudentEntity student1 = studentService.createStudent(student);
    return Response.created(URI.create("/students/" + student1.getId())).entity(student1).build();
  }

  @DELETE
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response deleteStudent(@PathParam(value = "id") long id) {
    studentService.deleteStudent(id);
    return Response.ok().build();
  }

  @PUT
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateStudent(@PathParam("id") long id, @Valid StudentResource student) {
    StudentEntity updatedStudent = studentService.updateStudent(id, student);
    return Response.ok().entity(updatedStudent).build();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getStudent(@PathParam("id") long id) {
    StudentEntity student = studentService.getStudent(id);
    return Response.ok().entity(student).build();
  }
}
