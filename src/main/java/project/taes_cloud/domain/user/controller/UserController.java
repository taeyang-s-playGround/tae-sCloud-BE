package project.taes_cloud.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import project.software.domain.user.controller.dto.response.GetMyPageResponse;
import project.software.domain.user.service.GetMyPageService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final GetMyPageService getMyPageService;

    @GetMapping("/my-page")
    @ResponseStatus(HttpStatus.OK)
    public GetMyPageResponse getMyPage() {
        return getMyPageService.execute();
    }
}
