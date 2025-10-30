package pe.com.scotibank.bff.digital.factory.challenge.student.application;

import pe.com.scotibank.bff.digital.factory.challenge.student.domain.models.requests.StudentRequest;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.models.responses.StudentResponse;
import reactor.core.publisher.Mono;

public interface ICreateStudentUseCase {

    /**
     * Create Student.
     *
     * @param studentRequest {@link StudentRequest}
     * @return {@link StudentResponse}
     */
    Mono<StudentResponse> handle(StudentRequest studentRequest);

}
