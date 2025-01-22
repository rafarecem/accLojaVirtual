package com.acc.Autenticacao.Autenticacao.service;

import com.acc.Autenticacao.Autenticacao.exceptions.AuthenticationException;
import com.acc.Autenticacao.Autenticacao.exceptions.UserAlreadyExistsException;
import com.acc.Autenticacao.Autenticacao.model.AuthenticationRequest;
import com.acc.Autenticacao.Autenticacao.model.AuthenticationResponse;
import com.acc.Autenticacao.Autenticacao.model.TipoUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticatorManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthenticationManager authenticatorManager, JwtService jwtService, CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder) {
        this.authenticatorManager = authenticatorManager;
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(AuthenticationRequest authenticationRequest) {
        if (customUserDetailsService.userExists(authenticationRequest.getUsername())) {
            throw new UserAlreadyExistsException();
        }

        String encryptedPassword = passwordEncoder.encode(authenticationRequest.getPassword());

        customUserDetailsService.saveUser(
                authenticationRequest.getUsername(), encryptedPassword, TipoUser.USER);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(), authenticationRequest.getPassword()
                    );

            authenticatorManager.authenticate(authenticationToken);

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

            String jwt = jwtService.generateToken(userDetails);

            return new AuthenticationResponse(jwt);
        }catch (Exception e) {
            throw new AuthenticationException(authenticationRequest.getUsername());
        }
    }

}

