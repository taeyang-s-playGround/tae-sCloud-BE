package project.taes_cloud.domain.tus.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TusResponse {

    private final String id;

    private final String fileName;

    private final String fileUrl;
}
