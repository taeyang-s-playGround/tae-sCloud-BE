package project.taes_cloud.domain.auth.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import project.taes_cloud.domain.user.domain.User;
import project.taes_cloud.domain.user.domain.repository.UserRepository;
import project.taes_cloud.domain.user.exception.UserNotFoundException;

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
