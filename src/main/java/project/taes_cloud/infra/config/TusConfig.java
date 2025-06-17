package project.taes_cloud.infra.config;

import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.taes_cloud.domain.tus.service.TusService;

@Configuration
public class TusConfig {

    @Value("${tus.data.path}")
    private String tusDataPath;

    @Value("${tus.data.expiration}")
    Long tusDataExpiration;

    @Bean
    public TusService tus() {
        return new TusService()
            .withStoragePath(tusDataPath)
            .withDownloadFeature()
            .withUploadExpirationPeriod(tusDataExpiration)
            .withThreadLocalCache(true)
            .withUploadURI("/tus/upload");
    }
}