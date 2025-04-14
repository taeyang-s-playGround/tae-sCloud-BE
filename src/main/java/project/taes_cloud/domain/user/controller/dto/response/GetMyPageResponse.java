package project.taes_cloud.domain.user.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.taes_cloud.domain.user.domain.User;

@Getter
@AllArgsConstructor
public class GetMyPageResponse {

    private Long userId;
    private String name;
    private String accountId;

    public GetMyPageResponse(User user) {
        userId = user.getId();
        name = user.getName();
        accountId = user.getAccountId();
    }
}
