package project.taes_cloud.domain.tus.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.taes_cloud.domain.tus.controller.dto.TusResponse;
import project.taes_cloud.domain.tus.service.TusService;

@RestController
@RequestMapping("/tus")
@RequiredArgsConstructor
public class TusController {

    private final TusService tusService;
/*
    @PostMapping("/upload")
    public TusResponse uploadFile(@RequestParam("file") MultipartFile file) {
        return tusFileUploadService.execute(file);
    }

 */

    @PostMapping("/upload")
    public TusResponse uploadFile(HttpServletRequest request, HttpServletResponse response) {
        return tusService.execute(request, response);
    }
}
