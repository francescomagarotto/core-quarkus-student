package org.fmagarot;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.fmagarot.resources.StudentResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class StudentControllerTest {

    @Inject
    EntityManager em;

    @BeforeEach
    @Transactional
    void setUp() throws IOException {
        ClassLoader context = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = context.getResourceAsStream("insert-statement.sql");
        String statements = new String(inputStream.readAllBytes());
        em.createNativeQuery(statements).executeUpdate();
    }

    @Test
    void testGetAllStudents() {
        given()
                .when().get("/students")
                .then()
                .statusCode(200)
                .body("$", hasSize(3))
                .body("[0].id", equalTo(1))
                .body("[0].name", equalTo("Student1"))
                .body("[0].email", equalTo("student1@email.com"))
                .body("[0].phone", equalTo("1111111111"))
                .body("[1].id", equalTo(2))
                .body("[1].name", equalTo("Student2"))
                .body("[1].email", equalTo("student2@email.com"))
                .body("[1].phone", equalTo("2222222222"))
                .body("[2].id", equalTo(3))
                .body("[2].name", equalTo("Student3"))
                .body("[2].email", equalTo("student3@email.com"))
                .body("[2].phone", equalTo("3333333333"));
    }

    @Test
    void testCreateStudent() {
        var student = new StudentResource();
        student.setName("Student4");
        student.setEmail("student4@email.com");
        student.setPhone("4444444444");

        given()
                .body(student)
                .header("Content-Type", "application/json")
                .when()
                .post("/students")
                .then()
                .log().body()
                .statusCode(201)
                .body("id", notNullValue())
                .body("id", instanceOf(Integer.class));
    }

    @Test
    void testDeleteStudent() {
        given()
                .header("Content-Type", "application/json")
                .pathParam("id", 1)
                .when()
                .delete("/students/{id}")
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    void testGetStudent() {
        given()
                .header("Content-Type", "application/json")
                .pathParam("id", 2)
                .when()
                .delete("/students/{id}")
                .then()
                .log()
                .body()
                .statusCode(200);
    }

}