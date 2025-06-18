package project.taes_cloud.domain.tus.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.desair.tus.server.TusFileUploadService;
import me.desair.tus.server.exception.TusException;
import me.desair.tus.server.upload.UploadInfo;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
@Slf4j
@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final TusFileUploadService tusFileUploadService;

    public void process(HttpServletRequest request, HttpServletResponse response) {
        try {
            // tus 업로드 요청 처리
            tusFileUploadService.process(request, response);

            // PATCH 요청일 경우에만 저장 처리 시도
            if ("PATCH".equalsIgnoreCase(request.getMethod())) {
                UploadInfo uploadInfo = tusFileUploadService.getUploadInfo(request.getRequestURI());
                log.info("uploadInfo: {}", uploadInfo);

                if (uploadInfo != null && !uploadInfo.isUploadInProgress()) {
                    log.info("업로드 완료됨: {}", uploadInfo.getFileName());

                    // 파일 생성
                    createFile(
                        tusFileUploadService.getUploadedBytes(request.getRequestURI()),
                        uploadInfo.getFileName()
                    );

                    // 업로드 정보 정리
                    tusFileUploadService.deleteUpload(request.getRequestURI());
                } else {
                    log.info("업로드 진행 중이거나 uploadInfo가 없음");
                }
            }

        } catch (IOException | TusException e) {
            log.error("예외 발생: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    private void createFile(InputStream is, String filename) throws IOException {
        File file = new File("C:/taes", filename); // 저장 경로 수정

        FileUtils.copyInputStreamToFile(is, file);
        log.info("파일 저장 완료: {}", file.getAbsolutePath());
    }
}
