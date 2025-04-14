package project.taes_cloud.domain.auth.controller.dto.request;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GuestSignUpRequest {

    private String deviceToken;
}
