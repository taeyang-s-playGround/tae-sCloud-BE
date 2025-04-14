package project.taes_cloud.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.software.domain.auth.controller.dto.request.SignInRequest;
import project.software.domain.auth.controller.dto.request.response.TokenResponse;
import project.software.domain.auth.exception.PasswordMisMatchException;
import project.software.domain.user.domain.User;
import project.software.domain.user.domain.repository.UserRepository;
import project.software.domain.user.exception.UserNotFoundException;
import project.software.global.security.security.jwt.JwtTokenProvider;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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
