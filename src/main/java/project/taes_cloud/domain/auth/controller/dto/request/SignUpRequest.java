package project.taes_cloud.domain.auth.controller.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequest {

    private String accountId;

    private String name;

    private String password;

    private String deviceToken;
}
