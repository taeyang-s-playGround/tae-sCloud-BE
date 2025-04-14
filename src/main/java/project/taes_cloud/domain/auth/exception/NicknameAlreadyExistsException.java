package project.taes_cloud.domain.auth.exception;

import project.software.global.security.config.error.exception.CustomException;
import project.software.global.security.config.error.exception.ErrorCode;

public class NicknameAlreadyExistsException extends CustomException {
    public static final CustomException EXCEPTION = new NicknameAlreadyExistsException();

    public NicknameAlreadyExistsException() {
        super(ErrorCode.NICKNAME_ALREADY_EXISTS);
    }
}
