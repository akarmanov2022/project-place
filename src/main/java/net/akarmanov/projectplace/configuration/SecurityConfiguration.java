package net.akarmanov.projectplace.configuration;

import net.akarmanov.projectplace.models.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Map;

@Configuration
public class SecurityConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        var roleHierarchy = RoleHierarchyUtils.roleHierarchyFromMap(Map.of(
                UserRole.ADMIN.toString(), List.of(UserRole.TRACKER.toString()),
                UserRole.SUPER_ADMIN.toString(), List.of(UserRole.ADMIN.toString())));
        return RoleHierarchyImpl.fromHierarchy(roleHierarchy);
    }


}
