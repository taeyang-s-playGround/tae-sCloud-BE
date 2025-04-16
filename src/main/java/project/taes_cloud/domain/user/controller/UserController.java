package project.taes_cloud.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import project.taes_cloud.domain.user.controller.dto.response.GetMyPageResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

}
