package project.taes_cloud.domain.auth.exception;

import project.taes_cloud.global.exception.error.exception.CustomException;
import project.taes_cloud.global.exception.error.exception.ErrorCode;

public class PasswordMisMatchException extends CustomException {
    public static final CustomException EXCEPTION = new PasswordMisMatchException();

    public PasswordMisMatchException() {
        super(
            ErrorCode.PASSWORD_MISMATCH);
    }
}
