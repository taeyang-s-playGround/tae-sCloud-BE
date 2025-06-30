package project.taes_cloud.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.taes_cloud.domain.auth.controller.dto.request.SignInRequest;
import project.taes_cloud.domain.auth.controller.dto.request.response.TokenResponse;
import project.taes_cloud.domain.auth.exception.PasswordMisMatchException;
import project.taes_cloud.domain.user.domain.User;
import project.taes_cloud.domain.user.domain.repository.UserRepository;
import project.taes_cloud.domain.user.exception.UserNotFoundException;
import project.taes_cloud.global.security.security.jwt.JwtTokenProvider;

@Service
@RequiredArgsConstructor
@Transactional
public class SignInService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtProvider;

    public TokenResponse execute(SignInRequest request) {
        User user = userRepository.findByAccountId(request.getAccountId()) .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw PasswordMisMatchException.EXCEPTION;
        }

        String token = jwtProvider.createAccessToken(request.getAccountId());

        return new TokenResponse(token);
    }
}
