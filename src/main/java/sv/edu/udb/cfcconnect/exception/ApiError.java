package sv.edu.udb.cfcconnect.exception;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Estructura estandar de error que devuelve la API ante cualquier fallo,
 * para que el cliente (frontend, Postman, etc.) siempre reciba el mismo
 * formato de respuesta de error.
 */
public class ApiError {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private Map<String, String> validationErrors;

    public ApiError() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(int status, String error, String message, String path) {
        this();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public static ApiError of(int status, String error, String message, String path) {
        return new ApiError(status, error, message, path);
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public String getError() { return error; }
    public String getMessage() { return message; }
    public String getPath() { return path; }
    public Map<String, String> getValidationErrors() { return validationErrors; }
    public void setValidationErrors(Map<String, String> validationErrors) { this.validationErrors = validationErrors; }
}
