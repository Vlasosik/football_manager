package org.example.football_manager.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.example.football_manager.command.exception.NotCommandExistById;
import org.example.football_manager.player.exception.NotPlayerExistById;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        String errorMessage = String.join(", ", fieldErrors);
        ErrorResponse errorResponse = buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                errorMessage,
                request.getRequestURI());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(NotCommandExistById.class)
    public ResponseEntity<ErrorResponse> handleNotCommandExistById(
            NotCommandExistById ex, HttpServletRequest request) {
        ErrorResponse errorResponse = buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                Objects.requireNonNull(ex.getMessage()),
                request.getRequestURI());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(NotPlayerExistById.class)
    public ResponseEntity<ErrorResponse> handleNotPlayerExistById(
            NotPlayerExistById ex, HttpServletRequest request) {
        ErrorResponse errorResponse = buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                Objects.requireNonNull(ex.getMessage()),
                request.getRequestURI());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    private ErrorResponse buildErrorResponse(HttpStatus status, String message, String requestURI) {
        return new ErrorResponse(LocalDateTime.now(), status.value(), message, requestURI);
    }
}
