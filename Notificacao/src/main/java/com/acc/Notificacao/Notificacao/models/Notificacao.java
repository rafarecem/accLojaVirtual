package com.acc.Notificacao.Notificacao.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "notificacao")
@AllArgsConstructor
@NoArgsConstructor
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 160)
    private String mensagem;

    @Column(nullable = false)
    private ZonedDateTime dataEnvio = ZonedDateTime.now();

    public Notificacao(String mensagem, ZonedDateTime dataEnvio) {
        this.mensagem = mensagem;
        this.dataEnvio = dataEnvio;
    }
}