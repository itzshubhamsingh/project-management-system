package com.assignment.sirma.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// Global exception handler for the Spring MVC controllers.
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    // Handle generic exceptions.
    // Returns ResponseEntity with an error message and HTTP status code 500 (Internal Server Error).
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred: " + ex.getMessage());
    }


    // Handle EntityNotFoundException.
    // Returns ResponseEntity with an error message and HTTP status code 404 (Not Found).
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Entity not found: " + ex.getMessage());
    }

    // Handle RuntimeExceptions.
    // Returns ResponseEntity with an error message and HTTP status code 400 (Bad Request).
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Bad request: " + ex.getMessage());
    }
}
