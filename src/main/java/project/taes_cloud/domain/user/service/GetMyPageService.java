package project.taes_cloud.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.taes_cloud.domain.auth.facade.UserFacade;
import project.taes_cloud.domain.user.controller.dto.response.GetMyPageResponse;
import project.taes_cloud.domain.user.domain.User;

@Service
@RequiredArgsConstructor
public class GetMyPageService {

    private final UserFacade userFacade;

    public GetMyPageResponse execute() {
        User user = userFacade.GetCurrentUser();
        return new GetMyPageResponse(user);
    }
}
