package org.fmagarot.entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "t_student")
@Data
@NamedQueries({@NamedQuery(name = "Student.findAll", query = "SELECT s FROM StudentEntity s")})
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_student_seq")
    @SequenceGenerator(name = "t_student_seq", sequenceName = "t_student_seq", allocationSize = 1)
    public Long id;

    public String name;
    public String email;
    public String phone;
}
