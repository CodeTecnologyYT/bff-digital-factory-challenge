package pe.com.scotibank.bff.digital.factory.challenge.student.application;

import lombok.RequiredArgsConstructor;
import org.h2.mvstore.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pe.com.scotibank.bff.digital.factory.challenge.shared.enums.StateEnum;
import pe.com.scotibank.bff.digital.factory.challenge.shared.models.PageResponse;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.models.responses.StudentResponse;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.ports.repositories.StudentRepositoryPort;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GetStudentWithStateActiveUseCase implements IGetStudentWithStateActiveUseCase {

    /* studentRepositoryPort. */
    private final StudentRepositoryPort studentRepositoryPort;

    /**
     * @inheritDoc
     */
    public Mono<PageResponse<StudentResponse>> handle(final Pageable pageable) {
        final var totalStudent = studentRepositoryPort.countByState(StateEnum.ACTIVE);
        final var responseStudent = studentRepositoryPort.findAllByState(StateEnum.ACTIVE, pageable);
        return responseStudent
                .collectList()
                .zipWith(totalStudent)
                .map(tuple -> new PageResponse<>(
                        tuple.getT1(),
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        tuple.getT2()));
    }

}
