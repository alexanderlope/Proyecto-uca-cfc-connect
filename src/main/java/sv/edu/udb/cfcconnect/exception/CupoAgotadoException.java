package sv.edu.udb.cfcconnect.exception;

/**
 * Se lanza al intentar inscribir un participante en un curso o diplomado
 * que ya alcanzo su cupo maximo, o que se encuentra inactivo.
 * GlobalExceptionHandler la traduce a HTTP 409 (Conflict).
 */
public class CupoAgotadoException extends RuntimeException {
    public CupoAgotadoException(String message) {
        super(message);
    }
}
