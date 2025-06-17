package project.taes_cloud.infra.extractor;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@Slf4j
public class DurationExtractor {

    public static double extract(File source) throws IOException {
        try {
            FrameGrab frameGrab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(source));
            double durationInSeconds = frameGrab.getVideoTrack().getMeta().getTotalDuration();
            log.info("Video length: {} seconds", durationInSeconds);
            return durationInSeconds;
        } catch (Exception e) {
            log.warn("Duration extract failed", e);
        }

        return 0;
    }
}