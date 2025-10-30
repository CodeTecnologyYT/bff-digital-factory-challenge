package pe.com.scotibank.bff.digital.factory.challenge.student.infrastructure.web.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.scotibank.bff.digital.factory.challenge.student.application.ICreateStudentUseCase;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.models.requests.StudentRequest;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.models.responses.StudentResponse;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/challenge/students")
@RequiredArgsConstructor
public class StudentController {

    /* createStudentUseCase. */
    private final ICreateStudentUseCase createStudentUseCase;

    /**
     * Create Student.
     *
     * @param studentRequest {@link StudentRequest}
     * @return {@link StudentResponse}
     */
    @PostMapping
    public Mono<StudentResponse> createStudent(@Valid @RequestBody Mono<StudentRequest> studentRequest) {
        return studentRequest.flatMap(createStudentUseCase::handle);
    }

}
