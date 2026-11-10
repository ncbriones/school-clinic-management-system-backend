package com.scms.api.exception;

import com.scms.api.dto.response.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handles validation errors (e.g., @NotBlank, @Email, @Size)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
                fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage())
        );
        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed",
                fieldErrors
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    // Handles business logic errors
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        ErrorResponse response = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }

    // Handles expired jwt in request
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponse> handleExpiredJwt(ExpiredJwtException ex) {
        ErrorResponse response = new ErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "Unauthorized access. Token is expired."
        );

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }

    // Handles denied authorization
    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAuthorizationDenied(AuthorizationDeniedException ex) {
        ErrorResponse response = new ErrorResponse(
                HttpStatus.FORBIDDEN.value(),
                "You do not have permission to perform this action"
        );

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(response);
    }

    @ExceptionHandler({BadCredentialsException.class, UsernameNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleInvalidLogin(Exception ex)  {
        ErrorResponse response = new ErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }


    // Handles unfound entities (e.g., Patient, Consultation, Medicine, Illness, User)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundEntities(EntityNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    // Handles any other unhandled exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse response = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage() != null ? ex.getMessage() : "An unexpected error occurred"
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}
