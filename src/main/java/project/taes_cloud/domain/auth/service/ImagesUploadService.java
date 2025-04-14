package project.taes_cloud.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.software.domain.auth.controller.dto.request.response.ImageUrlListResponse;
import project.software.infra.service.s3.S3Util;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImagesUploadService {
    private final S3Util s3Facade;

    @Transactional
    public ImageUrlListResponse execute(List<MultipartFile> multipartFiles) {

        List<String> imagesUrl = s3Facade.uploadImages(multipartFiles);

        return new ImageUrlListResponse(imagesUrl);
    }
}
