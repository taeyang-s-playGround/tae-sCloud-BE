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
import project.taes_cloud.infra.extractor.DurationExtractor;
import project.taes_cloud.infra.extractor.ThumbnailExtractor;
import project.taes_cloud.infra.tus.FFmpegManager;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TusService {

    private final TusFileUploadService tusFileUploadService;
    private final FFmpegManager ffmpegManager;

    @org.springframework.beans.factory.annotation.Value("${tus.save.path}")
    private String savePath;

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            tusFileUploadService.process(request, response);

            UploadInfo uploadInfo = tusFileUploadService.getUploadInfo(request.getRequestURI());

            if (uploadInfo != null && !uploadInfo.isUploadInProgress()) {
                createFileV3(tusFileUploadService.getUploadedBytes(request.getRequestURI()), uploadInfo.getFileName());

                tusFileUploadService.deleteUpload(request.getRequestURI());

                return "success";
            }

            return null;
        } catch (IOException | TusException e) {
            log.error("exception was occurred. message={}", e.getMessage(), e);

            throw new RuntimeException(e);
        }
    }

    private void createFile(InputStream is, String filename) throws IOException {
        LocalDate today = LocalDate.now();

        String uploadedPath = savePath + "/" + today;

        String vodName = getVodName(filename);

        File file = new File(uploadedPath, vodName);

        FileUtils.copyInputStreamToFile(is, file);

//        ThumbnailExtractor.extract(file);
//        DurationExtractor.extract(file);

        ffmpegManager.getThumbnail(file.getAbsolutePath());
        ffmpegManager.getDuration(file.getAbsolutePath());
    }

    private void createFileV2(InputStream is, String filename) throws IOException {
        LocalDate today = LocalDate.now();

        String uploadedPath = savePath + "/" + today;

        String vodName = getVodName(filename);

        File file = new File(uploadedPath, vodName);

        FileUtils.copyInputStreamToFile(is, file);

        double duration = DurationExtractor.extract(file);
        ThumbnailExtractor.extract(file);

        for (long i = 0; i < duration; i++) {
            ThumbnailExtractor.extract(file, i, (long) duration);
        }
    }

    private void createFileV3(InputStream is, String filename) throws IOException {
        LocalDate today = LocalDate.now();

        String uploadedPath = savePath + "/" + today;

        String vodName = getVodName(filename);

        File file = new File(uploadedPath, vodName);

        FileUtils.copyInputStreamToFile(is, file);

        double duration = DurationExtractor.extract(file);
        ThumbnailExtractor.extract(file);

        for (long i = 0; i < duration; i++) {
            ThumbnailExtractor.extract(file, i, (long) duration);
        }
    }

    public void downloadFile(String fileId, HttpServletResponse response) throws IOException {
        try {
            UploadInfo uploadInfo = tusFileUploadService.getUploadInfo(fileId);
            if (uploadInfo != null) {
                InputStream inputStream = tusFileUploadService.getUploadedBytes(fileId);
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + uploadInfo.getFileName() + "\"");
                org.apache.commons.io.IOUtils.copy(inputStream, response.getOutputStream());
                response.getOutputStream().flush();
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
            }
        } catch (TusException e) {
            log.error("Exception occurred during file download. message={}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    private String getVodName(String filename) {
        String[] split = filename.split("\\.");
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid + "." + split[split.length - 1];
    }
}
