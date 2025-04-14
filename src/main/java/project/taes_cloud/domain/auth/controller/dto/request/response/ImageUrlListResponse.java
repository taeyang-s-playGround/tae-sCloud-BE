package project.taes_cloud.domain.auth.controller.dto.request.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ImageUrlListResponse {

    private List<String> imageUrl;
}
