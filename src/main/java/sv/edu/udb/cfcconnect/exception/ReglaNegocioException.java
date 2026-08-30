package sv.edu.udb.cfcconnect.exception;

/**
 * Excepcion generica para violaciones de reglas de negocio que no encajan en
 * las excepciones especificas (por ejemplo: DUI/NIT o correo duplicado,
 * intentar aprobar una cotizacion ya rechazada, etc.).
 * GlobalExceptionHandler la traduce a HTTP 409 (Conflict).
 */
public class ReglaNegocioException extends RuntimeException {
    public ReglaNegocioException(String message) {
        super(message);
    }
}
