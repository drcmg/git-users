package pl.grunwald.users.web.exception;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ExceptionFormat> responseStatusException(ResponseStatusException exception) {
        log.error("Service unavailable", exception);

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(
                        new ExceptionFormat(
                                "Service is unavailable.",
                                "Error during fetch data from server."
                        )
                );
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionFormat> entityNotFoundException(NotFoundException exception){
        log.error("User not found", exception);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ExceptionFormat(
                                "User not found.",
                                "User with given login was not found."
                        )
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionFormat> exception(Exception exception) {
        log.error("Unexpected error", exception);

        if (exception instanceof MissingServletRequestParameterException) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        new ExceptionFormat(
                                "Internal Server Error",
                                "Something went wrong."
                        )
                );
    }
}
