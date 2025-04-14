package project.taes_cloud.domain.auth.controller.dto.request;

import lombok.Getter;

@Getter
public class ReissueRefreshTokenRequest {
    private String token;
}
