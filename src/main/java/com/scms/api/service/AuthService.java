package com.scms.api.service;

import com.scms.api.dto.request.LoginRequest;
import com.scms.api.dto.response.LoginResponse;
import com.scms.api.repository.UserRepository;
import com.scms.api.security.CustomUserDetails;
import com.scms.api.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthService(
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            UserRepository userRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    public LoginResponse login(LoginRequest request) {
        Authentication unauthenticatedToken = new UsernamePasswordAuthenticationToken(
            request.email(),
            request.password()
        );

        Authentication authenticatedToken = authenticationManager.authenticate(unauthenticatedToken);

        CustomUserDetails userDetails = (CustomUserDetails) authenticatedToken.getPrincipal();

        String token = jwtService.generateToken(userDetails);

        return new LoginResponse(
            "Bearer",
            token,
            userDetails.getName(),
            userDetails.getRole()
        );
    }
}
