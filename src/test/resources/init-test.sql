drop table if exists t_student cascade;
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
