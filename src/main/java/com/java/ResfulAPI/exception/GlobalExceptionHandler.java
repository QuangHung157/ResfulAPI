package com.java.ResfulAPI.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ApiError> handleResourceNotFound(
                        ResourceNotFoundException ex,
                        HttpServletRequest request) {
                ApiError error = new ApiError(
                                LocalDateTime.now(),
                                HttpStatus.NOT_FOUND.value(),
                                HttpStatus.NOT_FOUND.getReasonPhrase(),
                                ex.getMessage(),
                                request.getRequestURI());

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        @ExceptionHandler(BadCredentialsException.class)
        public ResponseEntity<ApiError> handleBadCredentials(
                        BadCredentialsException ex,
                        HttpServletRequest request) {
                ApiError error = new ApiError(
                                LocalDateTime.now(),
                                HttpStatus.UNAUTHORIZED.value(),
                                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                                "Sai username hoặc password",
                                request.getRequestURI());

                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }

        @ExceptionHandler(MethodArgumentTypeMismatchException.class)
        public ResponseEntity<ApiError> handleTypeMismatch(
                        MethodArgumentTypeMismatchException ex,
                        HttpServletRequest request) {
                String message = "Giá trị '" + ex.getValue() + "' không hợp lệ cho tham số '"
                                + ex.getName() + "'. Kiểu đúng phải là "
                                + (ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName()
                                                : "đúng định dạng");

                ApiError error = new ApiError(
                                LocalDateTime.now(),
                                HttpStatus.BAD_REQUEST.value(),
                                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                                message,
                                request.getRequestURI());

                return ResponseEntity.badRequest().body(error);
        }

        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<ApiError> handleInvalidJson(
                        HttpMessageNotReadableException ex,
                        HttpServletRequest request) {
                ApiError error = new ApiError(
                                LocalDateTime.now(),
                                HttpStatus.BAD_REQUEST.value(),
                                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                                "Request body không hợp lệ hoặc sai định dạng JSON",
                                request.getRequestURI());

                return ResponseEntity.badRequest().body(error);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, Object>> handleValidationErrors(
                        MethodArgumentNotValidException ex,
                        HttpServletRequest request) {
                Map<String, String> fieldErrors = new HashMap<>();
                ex.getBindingResult().getFieldErrors()
                                .forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));

                Map<String, Object> response = new HashMap<>();
                response.put("timestamp", LocalDateTime.now());
                response.put("status", HttpStatus.BAD_REQUEST.value());
                response.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
                response.put("message", "Dữ liệu đầu vào không hợp lệ");
                response.put("path", request.getRequestURI());
                response.put("fieldErrors", fieldErrors);

                return ResponseEntity.badRequest().body(response);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ApiError> handleGenericException(
                        Exception ex,
                        HttpServletRequest request) {
                ex.printStackTrace(); // In lỗi ra terminal chạy Spring Boot
                ApiError error = new ApiError(
                                LocalDateTime.now(),
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                                "Đã xảy ra lỗi nội bộ hệ thống. Chi tiết: " + ex.getMessage(),
                                request.getRequestURI());

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
}