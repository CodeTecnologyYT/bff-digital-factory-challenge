package pe.com.scotibank.bff.digital.factory.challenge.student.infrastructure.h2.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pe.com.scotibank.bff.digital.factory.challenge.shared.enums.StateEnum;
import pe.com.scotibank.bff.digital.factory.challenge.student.infrastructure.h2.entities.StudentEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface StudentH2Repository extends ReactiveCrudRepository<StudentEntity, UUID> {

    /**
     * Find All Students by State.
     *
     * @param state {@link StateEnum}
     * @return {@link Flux}
     */
    Flux<StudentEntity> findAllByState(StateEnum state, Pageable pageable);

    /**
     * Find Count Students by State.
     *
     * @param state {@link StateEnum}
     * @return {@link Mono}
     */
    Mono<Long> countByState(StateEnum state);

}
