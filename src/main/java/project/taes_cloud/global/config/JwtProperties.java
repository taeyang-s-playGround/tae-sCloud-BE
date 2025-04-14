package project.taes_cloud.global.config;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class JwtProperties {

    public static final String header = "Authorization";
    public static final String prefix = "Bearer";
    public static final String secretKey = "123asdfghwerthcvbneoihhgf";
    public static final long accessExp = 10000L;
    public static final long refreshExp = 604800;

    public String getHeader() {
        return header;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public Long getAccessExp() {
        return accessExp;
    }

    public Long getRefreshExp() {
        return refreshExp;
    }

}
