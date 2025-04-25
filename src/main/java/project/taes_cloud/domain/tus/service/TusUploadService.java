package project.taes_cloud.domain.tus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.taes_cloud.domain.tus.controller.dto.TusResponse;
import project.taes_cloud.domain.tus.domain.Tus;

@RequiredArgsConstructor
@Service
public class TusUploadService {

    public TusResponse execute(MultipartFile file) {


        return new TusResponse();
    }
}
