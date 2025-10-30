package pe.com.scotibank.bff.digital.factory.challenge.student.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.exceptions.StudentExistIdException;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.models.requests.StudentRequest;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.models.responses.StudentResponse;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.ports.metrics.StudentMetricsPort;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.ports.repositories.StudentRepositoryPort;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreateStudentUseCase implements ICreateStudentUseCase {

    /* studentRepositoryPort. */
    private final StudentRepositoryPort studentRepositoryPort;
    /** studentMetricsPort. */
    private final StudentMetricsPort studentMetricsPort;



    /**
     * @inheritDoc
     */
    public Mono<StudentResponse> handle(StudentRequest studentRequest) {
        return studentRepositoryPort.findById(studentRequest.id())
                .flatMap(existingStudent -> {
                    if(existingStudent != null){
                        studentMetricsPort.incrementIdExists();
                        return Mono.error(new StudentExistIdException("Student with id %s already exist".formatted(studentRequest.id())));
                    }
                    return Mono.empty();
                })
                .switchIfEmpty(
                        Mono.defer(() -> studentRepositoryPort.save(studentRequest))
                                .doOnSuccess(saved -> studentMetricsPort.incrementIdNotExists())
                ).cast(StudentResponse.class);
    }

}
