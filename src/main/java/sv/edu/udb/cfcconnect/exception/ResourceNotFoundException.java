package sv.edu.udb.cfcconnect.exception;

/**
 * Se lanza cuando se busca un recurso (Cliente, Curso, Espacio, etc.) que no
 * existe en la base de datos. GlobalExceptionHandler la traduce a HTTP 404.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    /** Atajo para mensajes consistentes: "<Entidad> con id <id> no fue encontrado". */
    public static ResourceNotFoundException of(String entidad, Long id) {
        return new ResourceNotFoundException(entidad + " con id " + id + " no fue encontrado");
    }
}
