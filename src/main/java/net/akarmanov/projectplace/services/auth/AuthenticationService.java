package net.akarmanov.projectplace.services.auth;

import net.akarmanov.projectplace.api.auth.JwtAuthenticationResponse;
import net.akarmanov.projectplace.api.auth.SingInRequest;
import net.akarmanov.projectplace.api.auth.SingUpRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse singUp(SingUpRequest singUpRequest);

    JwtAuthenticationResponse singIn(SingInRequest request);
}
