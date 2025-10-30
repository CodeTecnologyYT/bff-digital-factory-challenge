package pe.com.scotibank.bff.digital.factory.challenge.student.domain.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import pe.com.scotibank.bff.digital.factory.challenge.shared.enums.StateEnum;

import java.util.UUID;

@Schema(name = "Student Request", description = "Request for student registration",
        example = """
                        {
                            "id": "1883b8ef-f0ab-4831-aeff-20221da17b27",
                            "name": "bryan",
                            "lastName": "rosas",
                            "age": 31,
                            "state": "ACTIVE"
                        }
                """)
public record StudentRequest(
        /* id. */
        @NotNull(message = "Id is required")
        @Schema(description = "Id student", example = "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx", requiredMode = Schema.RequiredMode.REQUIRED)
        UUID id,
        /* name. */
        @NotNull(message = "Name is required")
        @Schema(description = "Name student",requiredMode = Schema.RequiredMode.REQUIRED)
        String name,
        /* lastName. */
        @NotNull(message = "Lastname is required")
        @Schema(description = "Lastname student",requiredMode = Schema.RequiredMode.REQUIRED)
        String lastName,
        /* state. */
        @NotNull(message = "State is required")
        @Schema(description = "State student",example = "ACTIVE or INACTIVE",requiredMode = Schema.RequiredMode.REQUIRED)
        StateEnum state,
        /* age. */
        @NotNull(message = "Age is required")
        @Min(value = 1, message = "The age must be greater than or equal to 1")
        @Max(value = 120, message = "The age cannot be greater than 120")
        @Schema(description = "Age student",example = "between 1 and 120", requiredMode = Schema.RequiredMode.REQUIRED)
        Integer age) {
}
