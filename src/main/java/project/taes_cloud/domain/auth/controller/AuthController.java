package project.taes_cloud.domain.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import project.software.domain.auth.controller.dto.request.response.ImageUrlListResponse;
import project.software.domain.auth.controller.dto.request.response.ImageUrlResponse;
import project.software.domain.auth.controller.dto.request.SignInRequest;
import project.software.domain.auth.controller.dto.request.SignUpRequest;
import project.software.domain.auth.controller.dto.request.response.TokenResponse;
import project.software.domain.auth.service.ImageUploadService;
import project.software.domain.auth.service.ImagesUploadService;
import project.software.domain.auth.service.SignInService;
import project.software.domain.auth.service.SignUpService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final SignUpService signUpService;
    private final SignInService signInService;
    private final ImageUploadService imageUploadService;
    private final ImagesUploadService imagesUploadService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        return signUpService.execute(signUpRequest);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponse login(@RequestBody @Valid SignInRequest signInRequest) {
        return signInService.execute(signInRequest);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/image")
    public ImageUrlResponse imageUpload(@RequestPart(required = false, value = "image") MultipartFile multipartFile) {
        return imageUploadService.execute(multipartFile);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/images")
    public ImageUrlListResponse imagesUpload(@RequestPart(value = "images", required = false) List<MultipartFile> multipartFiles) {
        return imagesUploadService.execute(multipartFiles);
    }
}
