package pe.com.scotibank.bff.digital.factory.challenge.student.domain.models.responses;

import pe.com.scotibank.bff.digital.factory.challenge.shared.enums.StateEnum;

import java.time.LocalDateTime;
import java.util.UUID;

public record StudentResponse(
        /* id. */
        UUID id,
        /* name. */
        String name,
        /* lastName. */
        String lastName,
        /* age. */
        Integer age,
        /* state. */
        StateEnum state,
        /* createdAt. */
        LocalDateTime createdAt,
        /* updatedAt. */
        LocalDateTime updatedAt
) {
}
