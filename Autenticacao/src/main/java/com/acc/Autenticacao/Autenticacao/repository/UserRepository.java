package com.acc.Autenticacao.Autenticacao.repository;

import com.acc.Autenticacao.Autenticacao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
