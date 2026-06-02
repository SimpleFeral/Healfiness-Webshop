package com.healfiness.backend.core.application.handler;

import com.healfiness.backend.core.domain.dto.exceptions.ResourceNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ProblemDetail handleAuthenticationException(AuthenticationException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.UNAUTHORIZED, ex.getMessage());
        problemDetail.setTitle("Unauthorized");
        problemDetail.setType(URI.create("https://example.com/problems/unauthorized"));
        return problemDetail;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException ex) {
        ProblemDetail problemDetail
                = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Resource Not Found");
        problemDetail.setType(URI.create("https://example.com/problems/resource-not-found"));
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleInvalidMethodArgumentException(MethodArgumentNotValidException ex) {
        Map<String, Object> properties = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
                fieldError -> properties.put(fieldError.getField(), fieldError.getDefaultMessage()
                )
        );

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, "Validation failed for one or more fields");
        problemDetail.setTitle("Bad Request");
        problemDetail.setType(URI.create("https://example.com/problems/validation-error"));
        problemDetail.setProperties(properties);
        return problemDetail;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, Object> properties = new HashMap<>();
        ex.getConstraintViolations().forEach(
                violation ->
                        properties.put(violation.getPropertyPath().toString()
                                , violation.getMessage()
                        )
        );

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, "Constraint violation occurred");
        problemDetail.setTitle("Constraint Violation");
        problemDetail.setType(URI.create("https://example.com/problems/constraint-violation"));
        problemDetail.setProperties(properties);
        return problemDetail;
    }
}
