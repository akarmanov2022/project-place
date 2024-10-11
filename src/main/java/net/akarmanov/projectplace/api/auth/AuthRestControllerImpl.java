package net.akarmanov.projectplace.api.auth;

import lombok.RequiredArgsConstructor;
import net.akarmanov.projectplace.api.dto.SingInRequest;
import net.akarmanov.projectplace.api.dto.SingUpRequest;
import net.akarmanov.projectplace.services.auth.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class AuthRestControllerImpl implements AuthRestController {
    private final AuthenticationService authenticationService;

    @Override
    public ResponseEntity<JwtAuthenticationResponse> singUp(SingUpRequest singUpRequest) {
        var response = authenticationService.singUp(singUpRequest);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<JwtAuthenticationResponse> singIn(SingInRequest singInRequest) {
        var response = authenticationService.singIn(singInRequest);
        return ResponseEntity.ok(response);
    }
}
