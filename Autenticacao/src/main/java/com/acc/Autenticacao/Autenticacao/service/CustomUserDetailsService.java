package com.acc.Autenticacao.Autenticacao.service;

import com.acc.Autenticacao.Autenticacao.model.TipoUser;
import com.acc.Autenticacao.Autenticacao.model.User;
import com.acc.Autenticacao.Autenticacao.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(String.valueOf(user.getRole()))
                .build();
    }

    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public void saveUser(String username, String password, TipoUser role) {
        User user = new User(null, username, password, role);
        userRepository.save(user);
    }
}
