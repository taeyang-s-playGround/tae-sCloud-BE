package project.taes_cloud.global.security.security.jwt.exception;

import project.software.global.security.config.error.exception.CustomException;
import project.software.global.security.config.error.exception.ErrorCode;

public class InvalidJwtException extends CustomException {

    public static final CustomException EXCEPTION = new InvalidJwtException();

    private InvalidJwtException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
