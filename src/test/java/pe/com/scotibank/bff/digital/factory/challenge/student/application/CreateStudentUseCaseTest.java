package pe.com.scotibank.bff.digital.factory.challenge.student.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import pe.com.scotibank.bff.digital.factory.challenge.shared.enums.StateEnum;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.exceptions.StudentExistIdException;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.models.requests.StudentRequest;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.ports.repositories.StudentRepositoryPort;
import pe.com.scotibank.bff.digital.factory.challenge.student.fixtures.StudentFixture;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Objects;
import java.util.UUID;

class CreateStudentUseCaseTest {

    /* repository. */
    @Mock
    private StudentRepositoryPort repository;
    /* useCase. */
    @InjectMocks
    private CreateStudentUseCase useCase;

    /**
     * Set Up.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test.
     */
    @Test
    void shouldThrowExceptionWhenStudentAlreadyExists() {
        // given
        final var id = UUID.randomUUID();
        final var request = StudentFixture.getStudentCreateRequest(id);

        // when a student already exists
        Mockito.when(repository.findById(id)).thenReturn(Mono.just(StudentFixture.getStudentCreateAlreadyExists(id)));
        Mockito.when(repository.save(ArgumentMatchers.any(StudentRequest.class))).thenReturn(Mono.empty());

        // then
        StepVerifier.create(useCase.handle(request))
                .expectErrorMatches(ex -> ex instanceof StudentExistIdException &&
                        ex.getMessage().contains("already exist"))
                .verify();
    }

    /**
     * Test.
     */
    @Test
    void shouldInsertStudentWhenNotExists() {
        // given
        final var id = UUID.randomUUID();
        final var request = StudentFixture.getStudentCreateRequest(id);
        final var expectedResponse = StudentFixture.getStudentCreateResponse(id);

        // when a student does not exist
        Mockito.when(repository.findById(id)).thenReturn(Mono.empty());
        Mockito.when(repository.save(request)).thenReturn(Mono.just(expectedResponse));

        // then
        StepVerifier.create(useCase.handle(request))
                .expectNext(Objects.requireNonNull(expectedResponse))
                .verifyComplete();
    }

}
