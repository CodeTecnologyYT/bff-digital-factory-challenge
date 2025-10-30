package pe.com.scotibank.bff.digital.factory.challenge.student.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.exceptions.StudentExistIdException;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.models.requests.StudentRequest;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.models.responses.StudentResponse;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.ports.repositories.StudentRepositoryPort;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreateStudentUseCase implements ICreateStudentUseCase {

    /* studentRepositoryPort. */
    private final StudentRepositoryPort studentRepositoryPort;

    /**
     * @inheritDoc
     */
    public Mono<StudentResponse> handle(StudentRequest studentRequest) {
        return studentRepositoryPort.findById(studentRequest.id())
                .flatMap(existingStudent -> Mono.error(() ->
                        new StudentExistIdException("Student with id %s already exist".formatted(studentRequest.id()))))
                .switchIfEmpty(
                        Mono.defer(() -> studentRepositoryPort.save(studentRequest))
                ).cast(StudentResponse.class);
    }

}
