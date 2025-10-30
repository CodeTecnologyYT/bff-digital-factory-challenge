package pe.com.scotibank.bff.digital.factory.challenge.student.fixtures;

import pe.com.scotibank.bff.digital.factory.challenge.shared.enums.StateEnum;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.models.requests.StudentRequest;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.models.responses.StudentResponse;
import pe.com.scotibank.bff.digital.factory.challenge.student.infrastructure.h2.entities.StudentEntity;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

public final class StudentFixture {

    /**
     * Mock Student Entity.
     *
     * @return {@link StudentEntity}
     */
    public static StudentEntity getStudentCreateAlreadyExists(final UUID id) {
        return new StudentEntity(id, "Bryan", "Rosas", 25, StateEnum.ACTIVE, LocalDateTime.now(), null);
    }

    /**
     * Mock Student Request.
     *
     * @return {@link StudentRequest}
     */
    public static StudentRequest getStudentCreateRequest(final UUID id) {
        return new StudentRequest(id, "Bryan", "Rosas", StateEnum.ACTIVE, 25);
    }

    /**
     * Mock Student Response.
     *
     * @return {@link StudentResponse}
     */
    public static StudentResponse getStudentCreateResponse(final UUID id) {
        return new StudentResponse(id, "Bryan", "Rosas", 25, StateEnum.ACTIVE, LocalDateTime.now(), null);
    }

    /**
     * Mock Student Request.
     *
     * @return {@link StudentRequest}
     */
    public static StudentRequest getStudentInsertRequest(final UUID id) {
        return new StudentRequest(id, "Bryan", "Rosas", StateEnum.ACTIVE, 25);
    }

    /**
     * Mock Student Request with invalid data.
     *
     * @return {@link StudentRequest}
     */
    public static StudentRequest getStudentInvalidDataNameNull() {
        return new StudentRequest(UUID.randomUUID(), null, "Rosas", StateEnum.ACTIVE, 25);
    }

}
