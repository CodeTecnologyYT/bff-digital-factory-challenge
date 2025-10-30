package pe.com.scotibank.bff.digital.factory.challenge.student.infrastructure.h2.adapters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.r2dbc.core.DatabaseClient;
import pe.com.scotibank.bff.digital.factory.challenge.shared.enums.StateEnum;
import pe.com.scotibank.bff.digital.factory.challenge.student.fixtures.StudentFixture;
import pe.com.scotibank.bff.digital.factory.challenge.student.infrastructure.h2.repositories.StudentH2Repository;
import pe.com.scotibank.bff.digital.factory.challenge.student.infrastructure.mappers.StudentMapperImpl;
import reactor.test.StepVerifier;

import java.util.UUID;

@DataR2dbcTest
@Import({StudentRepositoryAdapter.class, StudentMapperImpl.class})
class StudentRepositoryAdapterTest {

    /* useCase. */
    @Autowired
    private StudentRepositoryAdapter adapter;
    /* repository. */
    @Autowired
    private StudentH2Repository repository;
    /* client. */
    @Autowired
    private DatabaseClient client;

    /**
     * Test.
     */
    @BeforeEach
    void cleanDatabase() {
        // Clean Database
        client.sql("DELETE FROM student").fetch().rowsUpdated().block();
        // Insert Data Initially
        repository.saveAll(StudentFixture.getStudentListEntity()).blockLast();
    }

    /**
     * Test.
     */
    @Test
    void shouldInsertAndFindStudent() {
        // given
        final var id = UUID.randomUUID();
        final var request = StudentFixture.getStudentInsertRequest(id);

        // then
        StepVerifier.create(adapter.save(request))
                .expectNextMatches(resp -> resp.id().equals(id)
                        && resp.name().equals("Bryan")
                        && resp.state() == StateEnum.ACTIVE)
                .verifyComplete();

        StepVerifier.create(repository.findById(id))
                .expectNextMatches(student -> student.name().equals("Bryan"))
                .verifyComplete();
    }

    /**
     * Test.
     */
    @Test
    void findAllByStateShouldReturnActiveStudents() {
        final var pageable = PageRequest.of(0, 10);

        StepVerifier.create(repository.findAllByState(StateEnum.ACTIVE, pageable))
                .expectNextCount(2)
                .verifyComplete();
    }

    /**
     * Test.
     */
    @Test
    void countByStateShouldReturnCorrectCount() {
        StepVerifier.create(repository.countByState(StateEnum.ACTIVE))
                .expectNext(2L)
                .verifyComplete();

        StepVerifier.create(repository.countByState(StateEnum.INACTIVE))
                .expectNext(1L)
                .verifyComplete();
    }

}