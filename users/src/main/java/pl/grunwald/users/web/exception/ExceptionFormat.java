package pl.grunwald.users.web.exception;

public class ExceptionFormat {

    private final String message;
    private final String description;

    ExceptionFormat(String message, String description) {
        this.message = message;
        this.description = description;
    }

    public String getMessage() {
        return this.message;
    }

    public String getDescription() {
        return this.description;
    }
}
