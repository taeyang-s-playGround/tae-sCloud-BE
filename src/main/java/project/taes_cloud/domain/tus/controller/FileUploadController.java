package project.taes_cloud.domain.tus.controller;

import com.google.api.client.util.Value;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.taes_cloud.domain.tus.service.FileUploadService;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @RequestMapping(value = {"/api/tus/file/upload", "/api/tus/file/upload/**"})
    public ResponseEntity uploadWithTus(HttpServletRequest request, HttpServletResponse response) {
        // Process a tus upload request
        fileUploadService.process(request, response);

        // Generate HTTP Response Headers
        return httpOkStatus();
    }

    private static ResponseEntity<Object> httpOkStatus() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");

        return ResponseEntity.status(HttpStatus.OK)
            .headers(headers)
            .build();
    }

    private String uploadPath = "C:/taes";

    @PostConstruct
    public void init() {
        log.info("파일 업로드 경로 설정됨: {}", uploadPath);
        if (uploadPath == null) {
            throw new IllegalStateException("파일 업로드 경로가 주입되지 않았습니다.");
        }
    }

    @GetMapping("/files")
    public List<String> listUploadedFiles() {
        File folder = new File(uploadPath);
        File[] files = folder.listFiles();

        if (files == null) return List.of();
        return Arrays.stream(files)
            .filter(File::isFile)
            .map(File::getName)
            .toList();
    }
}