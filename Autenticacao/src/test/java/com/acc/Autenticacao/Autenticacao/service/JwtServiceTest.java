package com.acc.Autenticacao.Autenticacao.service;

import com.acc.Autenticacao.Autenticacao.exceptions.AuthenticationException;
import com.acc.Autenticacao.Autenticacao.exceptions.UserAlreadyExistsException;
import com.acc.Autenticacao.Autenticacao.model.AuthenticationRequest;
import com.acc.Autenticacao.Autenticacao.model.AuthenticationResponse;
import com.acc.Autenticacao.Autenticacao.model.TipoUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtService jwtService;
    @Mock
    private CustomUserDetailsService customUserDetailsService;
    @Mock
    private PasswordEncoder passwordEncoder;

    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authService = new AuthService(authenticationManager, jwtService, customUserDetailsService, passwordEncoder);
    }

    @Test
    void testRegisterUserAlreadyExists() {
        AuthenticationRequest request = new AuthenticationRequest("user", "password");
        when(customUserDetailsService.userExists("user")).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () -> authService.register(request));
    }

    @Test
    void testRegisterSuccess() {
        AuthenticationRequest request = new AuthenticationRequest("user", "password");
        when(customUserDetailsService.userExists("user")).thenReturn(false);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        authService.register(request);

        verify(customUserDetailsService, times(1)).saveUser("user", "encodedPassword", TipoUser.USER);
    }

    @Test
    void testAuthenticateInvalidCredentials() {
        AuthenticationRequest request = new AuthenticationRequest("user", "wrongPassword");
        when(authenticationManager.authenticate(any())).thenThrow(new RuntimeException("Authentication failed"));

        assertThrows(AuthenticationException.class, () -> authService.authenticate(request));
    }

    @Test
    void testAuthenticateSuccess() {
        AuthenticationRequest request = new AuthenticationRequest("user", "password");
        UserDetails userDetails = mock(UserDetails.class);
        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(customUserDetailsService.loadUserByUsername("user")).thenReturn(userDetails);
        when(jwtService.generateToken(userDetails)).thenReturn("jwtToken");

        AuthenticationResponse response = authService.authenticate(request);

        assertNotNull(response);
        assertEquals("jwtToken", response.getToken());
    }
}