package pe.com.scotibank.bff.digital.factory.challenge.student.domain.ports.metrics;

public interface StudentMetricsPort {

    /**
     * Increment Student Id Not Exists.
     */
    void incrementIdNotExists();

    /**
     * Increment Student Id Exists.
     */
    void incrementIdExists();

}
