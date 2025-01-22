package com.acc.Autenticacao.Autenticacao.service;

import com.acc.Autenticacao.Autenticacao.model.TipoUser;
import com.acc.Autenticacao.Autenticacao.model.User;
import com.acc.Autenticacao.Autenticacao.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customUserDetailsService = new CustomUserDetailsService(userRepository);
    }

    @Test
    void testLoadUserByUsernameUserNotFound() {
        when(userRepository.findByUsername("nonExistentUser")).thenReturn(java.util.Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> customUserDetailsService.loadUserByUsername("nonExistentUser"));
    }

    @Test
    void testLoadUserByUsernameSuccess() {
        User user = new User(1L, "user", "password", TipoUser.USER);
        when(userRepository.findByUsername("user")).thenReturn(java.util.Optional.of(user));

        var userDetails = customUserDetailsService.loadUserByUsername("user");

        assertEquals("user", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
    }

    @Test
    void testUserExists() {
        when(userRepository.existsByUsername("existingUser")).thenReturn(true);

        boolean exists = customUserDetailsService.userExists("existingUser");

        assertTrue(exists);
    }

    @Test
    void testSaveUser() {
        customUserDetailsService.saveUser("user", "password", TipoUser.USER);

        verify(userRepository, times(1)).save(any(User.class));
    }
}