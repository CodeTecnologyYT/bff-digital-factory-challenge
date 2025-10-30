package pe.com.scotibank.bff.digital.factory.challenge.shared.config;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import pe.com.scotibank.bff.digital.factory.challenge.shared.models.ErrorResponse;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.exceptions.StudentExistIdException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionConfig {

    /**
     * Exception Handler for StudentExistIdException
     *
     * @param ex {@link StudentExistIdException}
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(StudentExistIdException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleStudentIdFound(StudentExistIdException ex) {
        final var error = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                "Student Id already exist",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(error));
    }

    /**
     * Exception Handler for WebExchangeBindException
     *
     * @param ex {@link WebExchangeBindException}
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleValidation(WebExchangeBindException ex) {
        final var body = new LinkedHashMap<String, Object>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Validation Error");

        // Set error messages
        final var errors = ex.getFieldErrors()
                .stream()
                .map(field -> field.getField() + ": " + field.getDefaultMessage())
                .toList();

        body.put("messages", errors);
        body.put("timestamp", LocalDateTime.now());

        return Mono.just(ResponseEntity.badRequest().body(body));
    }

    /**
     * Exception Handler for InvalidFormatException
     *
     * @param ex {@link InvalidFormatException}
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(InvalidFormatException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleInvalidFormatException(InvalidFormatException ex) {
        String message = "Value incorrect for field: " + ex.getPath().get(0).getFieldName();

        // If is enum setter valid value
        if (ex.getTargetType().isEnum()) {
            String allowedValues = Arrays.stream(ex.getTargetType().getEnumConstants())
                    .map(Object::toString)
                    .collect(Collectors.joining(", "));
            message += ". Values allowed: " + allowedValues;
        }

        // If is UUID setter valid value
        if (ex.getTargetType().equals(UUID.class)) {
            message += ". (Format: xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx)";
        }

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Invalid Format",
                message,
                LocalDateTime.now()
        );

        return Mono.just(ResponseEntity.badRequest().body(errorResponse));
    }

}