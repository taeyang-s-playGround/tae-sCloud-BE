package project.taes_cloud.domain.auth.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class SignInRequest {

    @NotBlank
    private String accountId;

    @NotBlank
    private String password;

    @NotBlank
    private String deviceToken;

}
