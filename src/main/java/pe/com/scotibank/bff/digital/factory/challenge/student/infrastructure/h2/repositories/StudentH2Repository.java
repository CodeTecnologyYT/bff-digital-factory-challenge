package pe.com.scotibank.bff.digital.factory.challenge.student.infrastructure.h2.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pe.com.scotibank.bff.digital.factory.challenge.student.infrastructure.h2.entities.StudentEntity;

import java.util.UUID;

public interface StudentH2Repository extends ReactiveCrudRepository<StudentEntity, UUID> {
}
