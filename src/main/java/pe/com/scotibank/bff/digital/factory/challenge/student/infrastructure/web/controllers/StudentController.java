package pe.com.scotibank.bff.digital.factory.challenge.student.infrastructure.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.com.scotibank.bff.digital.factory.challenge.shared.models.PageResponse;
import pe.com.scotibank.bff.digital.factory.challenge.student.application.ICreateStudentUseCase;
import pe.com.scotibank.bff.digital.factory.challenge.student.application.IGetStudentWithStateActiveUseCase;
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
    /* getStudentWithStateActiveUseCase. */
    private final IGetStudentWithStateActiveUseCase getStudentWithStateActiveUseCase;

    /**
     * Create Student.
     *
     * @param studentRequest {@link StudentRequest}
     * @return {@link StudentResponse}
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create students", description = "Register a student with id different.")
    @ApiResponse(responseCode = "201", description = "Success in creating a student.")
    @ApiResponse(responseCode = "204", description = "Conflict with id student.")
    @ApiResponse(responseCode = "400", description = "Error in the information to be sent.",
            content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "500", description = "Unexpected error.",
            content = @Content(schema = @Schema(hidden = true)))
    public Mono<StudentResponse> createStudent(@Valid @RequestBody Mono<StudentRequest> studentRequest) {
        return studentRequest.flatMap(createStudentUseCase::handle);
    }

    /**
     * Students with State Active.
     *
     * @param page {@link Integer}
     * @param size {@link Integer}
     * @return mono of page {@link StudentResponse}
     */
    @GetMapping
    @Operation(summary = "Get all active students", description = "Retrieve a paginated list of students with state ACTIVE.")
    @ApiResponse(responseCode = "200", description = "Success get page of students with state active.")
    @ApiResponse(responseCode = "500", description = "Unexpected error.",
            content = @Content(schema = @Schema(hidden = true)))
    public Mono<PageResponse<StudentResponse>> getStudents(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        return getStudentWithStateActiveUseCase.handle(PageRequest.of(page, size));
    }

}
