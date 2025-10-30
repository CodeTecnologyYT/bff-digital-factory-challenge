package pe.com.scotibank.bff.digital.factory.challenge.student.infrastructure.mappers;

import org.mapstruct.Mapper;
import pe.com.scotibank.bff.digital.factory.challenge.shared.enums.StateEnum;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.models.responses.StudentResponse;
import pe.com.scotibank.bff.digital.factory.challenge.student.infrastructure.h2.entities.StudentEntity;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class, StateEnum.class})
public interface StudentMapper {

    /**
     * Transform an object of type StudentEntity a StudentResponse.
     *
     * @param studentEntity {@link StudentEntity}
     * @return {@link StudentResponse}
     */
    StudentResponse toResponse(StudentEntity studentEntity);

}
