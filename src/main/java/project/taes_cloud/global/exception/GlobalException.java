package project.taes_cloud.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.taes_cloud.global.exception.error.exception.CustomException;
import project.taes_cloud.global.exception.error.exception.ErrorCode;
import project.taes_cloud.global.exception.error.exception.ErrorResponse;
import project.taes_cloud.global.security.security.jwt.exception.InvalidJwtException;

@Slf4j
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(InvalidJwtException.class)
    public ResponseEntity<ErrorResponse> handleInvalidJwtException(InvalidJwtException ex) {
        ErrorCode errorCode = ErrorCode.INVALID_TOKEN;
        ErrorResponse errorResponse = ErrorResponse.builder()
            .status(errorCode.getStatus().value())
            .code("INVALID_TOKEN")
            .message(errorCode.getMessage())
            .build();
        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.builder()
            .status(errorCode.getStatus().value())
            .code(errorCode.name())
            .message(errorCode.getMessage())
            .build();
        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(fieldError -> fieldError.getDefaultMessage())
            .findFirst()
            .orElse("Validation error");

        ErrorResponse errorResponse = ErrorResponse.builder()
            .status(HttpStatus.BAD_REQUEST.value())
            .code("INVALID_INPUT")
            .message(errorMessage)
            .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        log.error("Unexpected error occurred", exception);
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = ErrorResponse.builder()
            .status(errorCode.getStatus().value())
            .code("INTERNAL_SERVER_ERROR")
            .message(errorCode.getMessage())
            .build();
        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);
    }
}
