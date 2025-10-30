package pe.com.scotibank.bff.digital.factory.challenge.student.infrastructure.web.controllers;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.com.scotibank.bff.digital.factory.challenge.student.application.ICreateStudentUseCase;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.models.requests.StudentRequest;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.models.responses.StudentResponse;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/challenge/students")
@RequiredArgsConstructor
@Tag(name = "Students", description = "API students")
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
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Success in creating a student")
    @ApiResponse(responseCode = "400", description = "Error in the information to be sent",
            content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "500", description = "Unexpected error",
            content = @Content(schema = @Schema(hidden = true)))
    public Mono<StudentResponse> createStudent(@Valid @RequestBody Mono<StudentRequest> studentRequest) {
        return studentRequest.flatMap(createStudentUseCase::handle);
    }

}
