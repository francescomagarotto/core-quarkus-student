package org.fmagarot.mapper;

import org.fmagarot.entities.StudentEntity;
import org.fmagarot.resources.StudentResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface StudentMapper {

    StudentEntity toEntity(StudentResource studentResource);

}
