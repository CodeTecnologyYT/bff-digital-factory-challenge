package pe.com.scotibank.bff.digital.factory.challenge.student.domain.exceptions;

public class StudentExistIdException extends RuntimeException {

    /**
     * Constructor.
     */
    public StudentExistIdException(String message) {
        super(message);
    }

    /**
     * Constructor.
     */
    public StudentExistIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
