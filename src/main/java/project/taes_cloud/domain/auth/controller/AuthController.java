package project.taes_cloud.domain.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import project.taes_cloud.domain.auth.controller.dto.request.SignInRequest;
import project.taes_cloud.domain.auth.controller.dto.request.SignUpRequest;
import project.taes_cloud.domain.auth.controller.dto.request.response.ImageUrlListResponse;
import project.taes_cloud.domain.auth.controller.dto.request.response.ImageUrlResponse;
import project.taes_cloud.domain.auth.controller.dto.request.response.TokenResponse;
import project.taes_cloud.domain.auth.service.ImageUploadService;
import project.taes_cloud.domain.auth.service.ImagesUploadService;
import project.taes_cloud.domain.auth.service.SignInService;
import project.taes_cloud.domain.auth.service.SignUpService;

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
        //return imageUploadService.execute(multipartFile);
        return null;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/images")
    public ImageUrlListResponse imagesUpload(@RequestPart(value = "images", required = false) List<MultipartFile> multipartFiles) {
        //return imagesUploadService.execute(multipartFiles);
        return null;
    }
}
