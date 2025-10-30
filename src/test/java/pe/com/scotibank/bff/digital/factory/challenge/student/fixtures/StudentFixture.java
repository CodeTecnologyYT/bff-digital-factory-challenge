package pe.com.scotibank.bff.digital.factory.challenge.student.fixtures;

import pe.com.scotibank.bff.digital.factory.challenge.shared.enums.StateEnum;
import pe.com.scotibank.bff.digital.factory.challenge.shared.models.PageResponse;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.models.requests.StudentRequest;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.models.responses.StudentResponse;
import pe.com.scotibank.bff.digital.factory.challenge.student.infrastructure.h2.entities.StudentEntity;

import java.time.LocalDateTime;
import java.util.List;
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

    /**
     * Mock Student Response List.
     *
     * @return list {@link StudentResponse}
     */
    public static List<StudentResponse> getStudentListActive() {
        final var student1 = new StudentResponse(UUID.randomUUID(), "Bryan", "Rosas", 25, StateEnum.ACTIVE, LocalDateTime.now(), null);
        final var student2 = new StudentResponse(UUID.randomUUID(), "Ana", "Lopez", 23, StateEnum.ACTIVE, LocalDateTime.now(), null);

        return List.of(student1, student2);
    }

    /**
     * Mock Student Entity List.
     *
     * @return list {@link StudentEntity}
     */
    public static List<StudentEntity> getStudentListEntity() {
        final var student1 = new StudentEntity(null, "Bryan", "Rosas", 25, StateEnum.ACTIVE, LocalDateTime.now(), null);
        final var student2 = new StudentEntity(null, "Ana", "Lopez", 23, StateEnum.ACTIVE, LocalDateTime.now(), null);
        final var student3 = new StudentEntity(null, "Carlos", "Perez", 30, StateEnum.INACTIVE, LocalDateTime.now(), null);

        return List.of(student1, student2, student3);
    }

    /**
     * Mock Student Response Page.
     *
     * @return page {@link StudentResponse}
     */
    public static PageResponse<StudentResponse> getStudentPageResponse() {
        return new PageResponse<>(StudentFixture.getStudentListActive(), 0, 10, 2L);
    }

}
