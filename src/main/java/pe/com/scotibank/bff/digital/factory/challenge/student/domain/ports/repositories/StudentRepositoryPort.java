package pe.com.scotibank.bff.digital.factory.challenge.student.domain.ports.repositories;

import pe.com.scotibank.bff.digital.factory.challenge.student.domain.models.requests.StudentRequest;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.models.responses.StudentResponse;
import pe.com.scotibank.bff.digital.factory.challenge.student.infrastructure.h2.entities.StudentEntity;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface StudentRepositoryPort {

    /**
     * Find Student by Id.
     *
     * @param id {@link UUID}
     * @return mono {@link StudentEntity}
     */
    Mono<StudentEntity> findById(UUID id);

    /**
     * Save Student.
     *
     * @param studentRequest {@link StudentRequest}
     * @return mono {@link StudentResponse}
     */
    Mono<StudentResponse> save(StudentRequest studentRequest);

}
