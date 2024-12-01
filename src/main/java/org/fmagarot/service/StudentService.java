package org.fmagarot.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.fmagarot.entities.StudentEntity;
import org.fmagarot.exception.StudentNotFoundException;
import org.fmagarot.mapper.StudentMapper;
import org.fmagarot.resources.StudentResource;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class StudentService {
    private final StudentMapper studentMapper;
    private final EntityManager em;

    public List<StudentEntity> getAllStudents() {
        return em.createNamedQuery("Student.findAll", StudentEntity.class).getResultList();
    }

    @Transactional
    public StudentEntity createStudent(StudentResource student) {
        StudentEntity studentEntity = studentMapper.toEntity(student);
        em.persist(studentEntity);
        return studentEntity;
    }

    @Transactional
    public void deleteStudent(long id) {
        StudentEntity studentEntity = em.find(StudentEntity.class, id);
        checkStudentExistence(id, studentEntity);
        em.remove(studentEntity);
    }

    public StudentEntity getStudent(long id) {
        StudentEntity student = em.find(StudentEntity.class, id);
        checkStudentExistence(id, student);
        return student;
    }

    @Transactional
    public StudentEntity updateStudent(long id, StudentResource student) {
        StudentEntity studentEntity = em.find(StudentEntity.class, id);
        checkStudentExistence(id, studentEntity);
        studentEntity.setEmail(student.getEmail());
        studentEntity.setName(student.getName());
        studentEntity.setPhone(student.getPhone());
        return em.merge(studentEntity);
    }

    private void checkStudentExistence(long id, StudentEntity studentEntity) {
        if (studentEntity == null) {
            throw new StudentNotFoundException(String.format("Student %s not found", id));
        }
    }
}
