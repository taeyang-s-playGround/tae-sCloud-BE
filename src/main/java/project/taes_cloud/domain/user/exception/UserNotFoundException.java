package project.taes_cloud.domain.user.exception;

import project.taes_cloud.global.exception.error.exception.CustomException;
import project.taes_cloud.global.exception.error.exception.ErrorCode;

public class UserNotFoundException extends CustomException {
    public static final CustomException EXCEPTION = new UserNotFoundException();

    private UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
