package dao.exceptions;

import java.io.Serializable;

/**
 * Custom exception class for Database connectivity exceptions.
 */
public class DatabaseConnectionException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 4545864587995944260L;

    public DatabaseConnectionException() {
        super("Error could not connect to the database.");
    }

    public DatabaseConnectionException(String message) {
        super(message);
    }

    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseConnectionException(Throwable cause) {
        super(cause);
    }
}
