package project.taes_cloud.domain.auth.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import project.software.domain.user.domain.User;
import project.software.domain.user.domain.repository.UserRepository;
import project.software.domain.user.exception.UserNotFoundException;

@Component
@RequiredArgsConstructor
public class UserFacade {
    public final UserRepository userRepository;

    public User GetCurrentUser() {
        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}
