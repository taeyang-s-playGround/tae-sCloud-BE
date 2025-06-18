package project.taes_cloud.global.exception.error.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String code;
    private String message;

    public ErrorResponse(String message, HttpStatus status) {
        this.status = status.value();
        this.code = status.name();
        this.message = message;
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return ErrorResponse.builder()
            .status(errorCode.getStatus().value())
            .code(errorCode.name())
            .message(errorCode.getMessage())
            .build();
    }
}