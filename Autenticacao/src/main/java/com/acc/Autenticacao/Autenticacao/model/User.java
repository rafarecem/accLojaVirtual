package com.acc.Autenticacao.Autenticacao.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=45)
    private String username;

    @Column(nullable=false)
    private String password;

    @Column(nullable = false)
    private TipoUser role;


}
