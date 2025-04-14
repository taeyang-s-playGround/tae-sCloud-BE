package project.taes_cloud.global.security.security.principle;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import project.software.domain.user.domain.repository.UserRepository;
import project.software.domain.user.exception.UserNotFoundException;

@RequiredArgsConstructor
@Service
public class AuthDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String accountId) {
        return new AuthDetails(userRepository.findByAccountId(accountId)
            .orElseThrow(() -> UserNotFoundException.EXCEPTION));
    }
}
