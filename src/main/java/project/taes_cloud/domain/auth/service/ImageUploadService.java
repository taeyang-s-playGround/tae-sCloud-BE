package project.taes_cloud.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.software.domain.auth.controller.dto.request.response.ImageUrlResponse;
import project.software.infra.service.s3.S3Util;

@Service
@RequiredArgsConstructor
public class ImageUploadService {

    private final S3Util s3Facade;

    @Transactional
    public ImageUrlResponse execute(MultipartFile multipartFile) {

        String imageUrl = s3Facade.uploadImage(multipartFile);

        return new ImageUrlResponse(imageUrl);
    }
}
