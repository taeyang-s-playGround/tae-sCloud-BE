package project.taes_cloud.domain.tus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import project.taes_cloud.domain.tus.controller.dto.TusResponse;
import project.taes_cloud.domain.tus.service.TusUploadService;

@RestController
@RequestMapping("/tus")
@RequiredArgsConstructor
public class TusController {

    private final TusUploadService tusUploadService;

     @PostMapping("/upload")
     public TusResponse uploadFile(@RequestParam("file") MultipartFile file) {
         return tusUploadService.execute(file);
}
