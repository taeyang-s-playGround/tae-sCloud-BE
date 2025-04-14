package project.taes_cloud.global.security.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import project.software.global.config.JwtProperties;
import project.software.global.security.security.jwt.exception.ExpiredJwtException;
import project.software.global.security.security.jwt.exception.InvalidJwtException;
import project.software.global.security.security.principle.AuthDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final AuthDetailsService authDetailsService;

    private static final String ACCESS_KEY = "access_token";

    public String createAccessToken(String accountId) {
        return createToken(accountId, ACCESS_KEY, jwtProperties.getAccessExp());
    }

    private String createToken(String accountId, String type, Long time) {
        Date now = new Date();
        return Jwts.builder().signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
            .setSubject(accountId)
            .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey()) //수정함
            .setHeaderParam("typ", type)
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + time))
            .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        return parseToken(bearer);
    }

    public String parseToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.replace("Bearer ", "");
        }
        return null;
    }

    public UsernamePasswordAuthenticationToken authorization(String token) {
        UserDetails userDetails = authDetailsService.loadUserByUsername(getTokenSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getTokenSubject(String subject) {
        return getTokenBody(subject).getSubject();
    }

    private Claims getTokenBody(String token) {
        try {
            return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw ExpiredJwtException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidJwtException.EXCEPTION;
        }
    }
}
