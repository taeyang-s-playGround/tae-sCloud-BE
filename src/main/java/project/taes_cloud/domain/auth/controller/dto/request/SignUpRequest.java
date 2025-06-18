package project.taes_cloud.domain.auth.controller.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequest {

    @NotNull(message = "accountId는 필수 입력값입니다")
    private String accountId;

    @NotNull(message = "name는 필수 입력값입니다")
    private String name;

    @NotNull(message = "password는 필수 입력값입니다")
    private String password;

    @NotNull(message = "deviceToken는 필수 입력값입니다")
    private String deviceToken;
}
