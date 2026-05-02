package com.decodex.br.adapters.in.web.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.decodex.br.domain.exception.CredenciaisInvalidasException;
import com.decodex.br.domain.exception.RefreshTokenInvalidoException;
import com.decodex.br.domain.exception.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            ResourceNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(
                HttpStatus.NOT_FOUND.value(), "Not Found", ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(
            IllegalArgumentException ex, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(CredenciaisInvalidasException.class)
    public ResponseEntity<ErrorResponse> handleCredenciais(
            CredenciaisInvalidasException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(
                HttpStatus.UNAUTHORIZED.value(), "Unauthorized", ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(), "Validation Error", "Erro de validação", request.getRequestURI());
        response.setFields(errors);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInvalidEnum(
            HttpMessageNotReadableException ex, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(), "Invalid Request",
                "Valor inválido para enum (ex: MALE, FEMALE)", request.getRequestURI()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrity(
            DataIntegrityViolationException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(
                HttpStatus.CONFLICT.value(), "Conflict",
                "Violação de integridade: possível dado duplicado.", request.getRequestURI()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(
            Exception ex, HttpServletRequest request) {
  
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error",
                "Erro inesperado", request.getRequestURI()));
    }
    
    @ExceptionHandler(RefreshTokenInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleRefreshToken(
            RefreshTokenInvalidoException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(
                HttpStatus.UNAUTHORIZED.value(), "Unauthorized", ex.getMessage(), request.getRequestURI()));
    }
}