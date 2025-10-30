package pe.com.scotibank.bff.digital.factory.challenge.student.infrastructure.h2.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import pe.com.scotibank.bff.digital.factory.challenge.shared.enums.StateEnum;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("student")
public record StudentEntity(
        /* id. */
        @Id
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
