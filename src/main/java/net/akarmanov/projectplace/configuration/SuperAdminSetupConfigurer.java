package net.akarmanov.projectplace.configuration;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.akarmanov.projectplace.models.UserRole;
import net.akarmanov.projectplace.persistence.entities.User;
import net.akarmanov.projectplace.services.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class SuperAdminSetupConfigurer {
    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Value("${app.superadmin.username}")
    private String superAdminUsername;

    @Value("${app.superadmin.password}")
    private String superAdminPassword;

    @PostConstruct
    public void setupSuperAdmin() {
        if (!userService.existsByUsername(superAdminUsername)) {
            var superAdmin = User.builder()
                    .username(superAdminUsername)
                    .firstName("Super")
                    .telegramId("superAdminUsername")
                    .password(passwordEncoder.encode(superAdminPassword))
                    .role(UserRole.SUPER_ADMIN)
                    .phoneNumber("+70000000000")
                    .enabled(true)
                    .build();
            userService.createUser(superAdmin);
        }
    }
}
