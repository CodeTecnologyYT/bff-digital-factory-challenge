package pe.com.scotibank.bff.digital.factory.challenge.student.infrastructure.h2.adapters;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import pe.com.scotibank.bff.digital.factory.challenge.shared.enums.StateEnum;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.models.requests.StudentRequest;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.models.responses.StudentResponse;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.ports.repositories.StudentRepositoryPort;
import pe.com.scotibank.bff.digital.factory.challenge.student.infrastructure.h2.entities.StudentEntity;
import pe.com.scotibank.bff.digital.factory.challenge.student.infrastructure.h2.repositories.StudentH2Repository;
import pe.com.scotibank.bff.digital.factory.challenge.student.infrastructure.mappers.StudentMapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class StudentRepositoryAdapter implements StudentRepositoryPort {

    /* studentH2Repository. */
    private final StudentH2Repository studentH2Repository;
    /* client. */
    private final DatabaseClient client;
    /* studentMapper. */
    private final StudentMapper studentMapper;
    /* INSERT_STUDENT_SCRIPT. */
    private final static String INSERT_STUDENT_QUERY = """
            INSERT INTO STUDENT (id, name, last_name, age,state, created_at)
            VALUES (:id, :name, :last_name, :age,:state, :created_at)""";

    /**
     * @inheritDoc
     */
    public Mono<StudentEntity> findById(final UUID id) {
        return studentH2Repository.findById(id);
    }

    /**
     * @inheritDoc
     */
    public Flux<StudentResponse> findAllByState(final StateEnum state, final Pageable pageable) {
        return studentH2Repository.findAllByState(state, pageable)
                .map(studentMapper::toResponse);
    }

    /**
     * @inheritDoc
     */
    public Mono<Long> countByState(final StateEnum state) {
        return studentH2Repository.countByState(state);
    }

    /**
     * @inheritDoc
     */
    public Mono<StudentResponse> save(final StudentRequest studentRequest) {
        return client.sql(INSERT_STUDENT_QUERY)
                .bind("id", studentRequest.id())
                .bind("name", studentRequest.name())
                .bind("last_name", studentRequest.lastName())
                .bind("state", studentRequest.state().name())
                .bind("age", studentRequest.age())
                .bind("created_at", LocalDateTime.now())
                .fetch()
                .rowsUpdated()
                .flatMap(rows -> studentH2Repository.findById(studentRequest.id()))
                .map(studentMapper::toResponse);
    }

}
