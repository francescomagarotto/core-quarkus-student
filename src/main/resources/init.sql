drop table if exists t_student cascade ;
drop sequence if exists t_student_seq;
create sequence t_student_seq start with 4 increment by 1;
create table t_student
(
    id    bigint not null,
    email varchar(255),
    name  varchar(255),
    phone varchar(255),
    primary key (id)
);

insert into t_student(id, email, name, phone) values (1, 'student1@email.com', 'Student1', '1111111111');
insert into t_student(id, email, name, phone) values (2, 'student2@email.com', 'Student2', '2222222222');
insert into t_student(id, email, name, phone) values (3, 'student3@email.com', 'Student3', '3333333333');
