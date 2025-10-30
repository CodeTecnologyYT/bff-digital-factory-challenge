package pe.com.scotibank.bff.digital.factory.challenge.student.application;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import pe.com.scotibank.bff.digital.factory.challenge.shared.enums.StateEnum;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.ports.repositories.StudentRepositoryPort;
import pe.com.scotibank.bff.digital.factory.challenge.student.fixtures.StudentFixture;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class GetStudentWithStateActiveUseCaseTest {

    @Mock
    private StudentRepositoryPort studentRepositoryPort;
    @InjectMocks
    private GetStudentWithStateActiveUseCase getStudentWithStateActiveUseCase;

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
    void handle_shouldReturnPagedStudents() {
        // given
        final var pageable = PageRequest.of(1, 2); // página 1, tamaño 2
        final var expectedList = StudentFixture.getStudentListActive();
        final var totalCount = 5L;

        // when return students
        Mockito.when(studentRepositoryPort.findAllByState(Mockito.eq(StateEnum.ACTIVE), Mockito.eq(pageable)))
                .thenReturn(Flux.fromIterable(expectedList));
        Mockito.when(studentRepositoryPort.countByState(Mockito.eq(StateEnum.ACTIVE)))
                .thenReturn(Mono.just(totalCount));

        // then
        final var result = getStudentWithStateActiveUseCase.handle(pageable);

        StepVerifier.create(result)
                .assertNext(page -> {
                    Assertions.assertThat(page.content()).hasSize(2);
                    Assertions.assertThat(page.content()).containsExactlyElementsOf(expectedList);
                    Assertions.assertThat(page.page()).isEqualTo(1);
                    Assertions.assertThat(page.size()).isEqualTo(2);
                    Assertions.assertThat(page.total()).isEqualTo(5);
                })
                .verifyComplete();

        // Verify that methods were called
        Mockito.verify(studentRepositoryPort).findAllByState(StateEnum.ACTIVE, pageable);
        Mockito.verify(studentRepositoryPort).countByState(StateEnum.ACTIVE);
    }

}