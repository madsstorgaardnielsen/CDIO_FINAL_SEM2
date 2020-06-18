package dao.exceptions;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DatabaseException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 4545864587995944260L;

    public DatabaseException() {
        super("There was a problem adding the resource to the database.");
    }

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseException(Throwable cause) {
        super(cause);
    }
}
