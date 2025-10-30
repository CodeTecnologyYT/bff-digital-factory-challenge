package pe.com.scotibank.bff.digital.factory.challenge.student.domain.models.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import pe.com.scotibank.bff.digital.factory.challenge.shared.enums.StateEnum;

import java.util.UUID;

public record StudentRequest(
        /* id. */
        @NotNull(message = "Id is required")
        UUID id,
        /* name. */
        @NotNull(message = "Name is required")
        String name,
        /* lastName. */
        @NotNull(message = "Lastname is required")
        String lastName,
        /* state. */
        @NotNull(message = "State is required")
        StateEnum state,
        /* age. */
        @NotNull(message = "Age is required")
        @Min(value = 1, message = "The age must be greater than or equal to 1")
        @Max(value = 120, message = "The age cannot be greater than 120")
        Integer age) {
}
