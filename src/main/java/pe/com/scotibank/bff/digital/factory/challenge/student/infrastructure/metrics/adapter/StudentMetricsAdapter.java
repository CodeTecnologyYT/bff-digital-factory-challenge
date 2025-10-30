package pe.com.scotibank.bff.digital.factory.challenge.student.infrastructure.metrics.adapter;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.com.scotibank.bff.digital.factory.challenge.student.domain.ports.metrics.StudentMetricsPort;

@Component
@RequiredArgsConstructor
public class StudentMetricsAdapter implements StudentMetricsPort {

    /* registry. */
    private final MeterRegistry registry;

    /**
     * @inheritDoc
     */
    @Override
    public void incrementIdNotExists() {
        Counter.builder("student_create_total")
                .description("Attempts to create a student grouped by the existence of an ID")
                .tag("status", "id_not_exists")
                .register(registry)
                .increment();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void incrementIdExists() {
        Counter.builder("student_create_total")
                .description("Attempts to create a student grouped by the existence of an ID")
                .tag("status", "id_exists")
                .register(registry)
                .increment();
    }

}
