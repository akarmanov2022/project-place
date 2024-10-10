package net.akarmanov.projectplace.services.auth;

import lombok.RequiredArgsConstructor;
import net.akarmanov.projectplace.api.auth.JwtAuthenticationResponse;
import net.akarmanov.projectplace.api.auth.SingInRequest;
import net.akarmanov.projectplace.api.auth.SingUpRequest;
import net.akarmanov.projectplace.models.UserRole;
import net.akarmanov.projectplace.persistence.entities.User;
import net.akarmanov.projectplace.services.jwt.JwtService;
import net.akarmanov.projectplace.services.user.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse singUp(SingUpRequest singUpRequest) {
        var user = User.builder()
                .username(singUpRequest.getUsername())
                .firstName(singUpRequest.getFirstName())
                .lastName(singUpRequest.getLastName())
                .middleName(singUpRequest.getMiddleName())
                .telegramId(singUpRequest.getTelegramId())
                .phoneNumber(singUpRequest.getPhoneNumber())
                .role(UserRole.valueOf(singUpRequest.getRole().toString()))
                .password(passwordEncoder.encode(singUpRequest.getPassword()))
                .build();

        userService.createUser(user);
        var token = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(token);
    }

    @Override
    public JwtAuthenticationResponse singIn(SingInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword())
        );
        var user = userService.getDetailsService()
                .loadUserByUsername(request.getUsername());

        var token = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(token);
    }
}
