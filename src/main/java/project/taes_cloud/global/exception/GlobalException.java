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
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.INVALID_TOKEN.getMessage(), ErrorCode.INVALID_TOKEN.getStatus());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        return new ResponseEntity<>(new ErrorResponse(errorCode.getMessage(), errorCode.getStatus()), errorCode.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(fieldError -> fieldError.getDefaultMessage())
            .findFirst()
            .orElse("Validation error");

        return new ResponseEntity<>(new ErrorResponse(ErrorCode.BAD_REQUEST.getMessage(), ErrorCode.BAD_REQUEST.getStatus()), ErrorCode.BAD_REQUEST.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        log.error("Unexpected error occurred", exception);
        String message = exception.getMessage() != null ? exception.getMessage() : "Unexpected server error";
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR.getMessage(), ErrorCode.INTERNAL_SERVER_ERROR.getStatus()), ErrorCode.INTERNAL_SERVER_ERROR.getStatus());
    }
}
