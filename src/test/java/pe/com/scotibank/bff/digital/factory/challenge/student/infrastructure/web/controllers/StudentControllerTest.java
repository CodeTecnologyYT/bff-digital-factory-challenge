package pe.com.scotibank.bff.digital.factory.challenge.student.infrastructure.web.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import pe.com.scotibank.bff.digital.factory.challenge.shared.enums.StateEnum;
import pe.com.scotibank.bff.digital.factory.challenge.student.application.ICreateStudentUseCase;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.models.requests.StudentRequest;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.models.responses.StudentResponse;
import pe.com.scotibank.bff.digital.factory.challenge.student.fixtures.StudentFixture;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@WebFluxTest(controllers = StudentController.class)
class StudentControllerTest {

    /* webTestClient. */
    @Autowired
    private WebTestClient webTestClient;
    /* createStudentUseCase. */
    @MockitoBean
    private ICreateStudentUseCase createStudentUseCase;

    /**
     * Test.
     */
    @Test
    void shouldCreateStudentSuccessfully() {
        // given
        final var id = UUID.randomUUID();
        final var request = StudentFixture.getStudentCreateRequest(id);
        final var response = StudentFixture.getStudentCreateResponse(id);

        // when response success
        Mockito.when(createStudentUseCase.handle(ArgumentMatchers.any(StudentRequest.class)))
                .thenReturn(Mono.just(response));

        // then
        webTestClient.post()
                .uri("/challenge/students")
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(StudentResponse.class)
                .value(resp -> {
                    assert resp.id().equals(id);
                    assert resp.name().equals("Bryan");
                    assert resp.state() == StateEnum.ACTIVE;
                });
    }

    /**
     * Test.
     */
    @Test
    void shouldReturnBadRequestWhenValidationFails() {
        // given
        StudentRequest invalid = StudentFixture.getStudentInvalidDataNameNull();

        // then
        webTestClient.post()
                .uri("/challenge/students")
                .bodyValue(invalid)
                .exchange()
                .expectStatus().isBadRequest();
    }

}