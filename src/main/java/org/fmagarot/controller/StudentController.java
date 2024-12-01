package org.fmagarot.controller;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.fmagarot.entities.StudentEntity;
import org.fmagarot.resources.StudentResource;
import org.fmagarot.service.StudentService;

import java.net.URI;
import java.util.List;

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
