package project.taes_cloud.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.software.domain.auth.facade.UserFacade;
import project.software.domain.user.controller.dto.response.GetMyPageResponse;
import project.software.domain.user.domain.User;

@Service
@RequiredArgsConstructor
public class GetMyPageService {

    private final UserFacade userFacade;

    public GetMyPageResponse execute() {
        User user = userFacade.GetCurrentUser();
        return new GetMyPageResponse(user);
    }
}
