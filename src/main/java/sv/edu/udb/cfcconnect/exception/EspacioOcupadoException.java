package sv.edu.udb.cfcconnect.exception;

/**
 * Se lanza cuando ya existe una reserva (alquiler o catering) para el mismo
 * espacio, en la misma fecha y horario (cruce de agenda institucional).
 * GlobalExceptionHandler la traduce a HTTP 409 (Conflict).
 */
public class EspacioOcupadoException extends RuntimeException {
    public EspacioOcupadoException(String message) {
        super(message);
    }
}
