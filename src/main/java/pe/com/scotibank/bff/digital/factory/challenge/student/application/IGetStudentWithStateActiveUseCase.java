package pe.com.scotibank.bff.digital.factory.challenge.student.application;

import org.springframework.data.domain.Pageable;
import pe.com.scotibank.bff.digital.factory.challenge.shared.models.PageResponse;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.models.responses.StudentResponse;
import reactor.core.publisher.Mono;

public interface IGetStudentWithStateActiveUseCase {

    /**
     * Page Students with State Active.
     *
     * @param pageable {@link Pageable}
     * @return {@link PageResponse}
     */
    Mono<PageResponse<StudentResponse>> handle(Pageable pageable);

}
