package project.software.global.security.config.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 회원가입 관련 오류
    ACCOUNT_ID_ALREADY_EXISTS(HttpStatus.CONFLICT, "accountId already exists"),
    NICKNAME_ALREADY_EXISTS(HttpStatus.CONFLICT, "nickname already exists"),
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "User already exists"),

    // 사용자 인증 관련 오류
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User Not Found"),
    USER_MISMATCH(HttpStatus.FORBIDDEN, "User Mismatch"),
    ADDRESS_NOT_FOUND(HttpStatus.NOT_FOUND, "Lens Not Found"),
    PASSWORD_MISMATCH(HttpStatus.UNAUTHORIZED, "Password Mismatch"),
    EXPIRED_TOKEN(HttpStatus.FORBIDDEN, "Expired token"),
    INVALID_TOKEN(HttpStatus.FORBIDDEN, "Invalid token"),
    JwtSigningException(HttpStatus.FORBIDDEN, "JWT Signing Exception"),

    // 게시글 관련 오류
    CART_NOT_FOUND(HttpStatus.NOT_FOUND, "Cart Not Found"),
    SHOP_NOT_FOUND(HttpStatus.NOT_FOUND, "Shop Not Found"),
    LENS_NOT_FOUND(HttpStatus.NOT_FOUND, "Lens Not Found"),
    CANNOT_MODIFY_POST(HttpStatus.FORBIDDEN, "Cannot Modify Post"),
    ALARM_NOT_FOUND(HttpStatus.NOT_FOUND, "Alarm Not Found"),

    // 파일 업로드 관련 오류
    EMPTY_FILE_EXCEPTION(HttpStatus.BAD_REQUEST, "Empty File"),
    IMAGE_NOT_FOUND_EXCEPTION(HttpStatus.BAD_REQUEST, "Not File"),
    INVALID_FILE_EXTENTION(HttpStatus.BAD_REQUEST, "Invalid File Extension"),
    WRONG_IMAGE(HttpStatus.BAD_REQUEST, "Wrong Image"),

    // S3 업로드/삭제 관련 오류
    IO_EXCEPTION_ON_IMAGE_UPLOAD(HttpStatus.INTERNAL_SERVER_ERROR, "IO Exception on Image Upload"),
    PUT_OBJECT_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to Upload Object to S3"),
    IO_EXCEPTION_ON_IMAGE_DELETE(HttpStatus.INTERNAL_SERVER_ERROR, "IO Exception on Image Delete"),

    // 이미지 목록 조회 관련 오류
    EXCEPTION_ON_LIST_IMAGES(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to Retrieve Image List"),

    // HTTP 요청 관련 오류
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Bad request"),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "Validation Error"),
    NOT_SUPPORTED_METHOD_ERROR(HttpStatus.METHOD_NOT_ALLOWED, "Not Supported Method"),
    NOT_SUPPORTED_URI_ERROR(HttpStatus.METHOD_NOT_ALLOWED, "Not Supported URI"),

    // 서버 오류
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),
    UNEXPECTED_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected Server Error");

    private final HttpStatus status;
    private final String message;
}
