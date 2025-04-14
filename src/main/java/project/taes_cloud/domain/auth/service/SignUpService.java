package project.taes_cloud.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.software.domain.auth.controller.dto.request.SignUpRequest;
import project.software.domain.auth.controller.dto.request.response.TokenResponse;
import project.software.domain.user.domain.User;
import project.software.domain.user.domain.repository.UserRepository;
import project.software.domain.user.exception.UserAlreadyExistsException;
import project.software.global.security.security.jwt.JwtTokenProvider;

@RequiredArgsConstructor
@Service
@Transactional
public class SignUpService {

    private final JwtTokenProvider jwtProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public TokenResponse execute(SignUpRequest signUpRequest) {
        if (userRepository.existsByAccountId(signUpRequest.getAccountId())) {
            throw UserAlreadyExistsException.EXCEPTION;
        }

        userRepository.save(
            User.builder()
                .accountId(signUpRequest.getAccountId())
                .name(signUpRequest.getName())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .deviceToken(signUpRequest.getDeviceToken())
                .build()
        );

        String token = jwtProvider.createAccessToken(signUpRequest.getAccountId());

        return new TokenResponse(token);
    }
}
