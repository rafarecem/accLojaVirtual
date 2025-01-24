package com.acc.Notificacao.Notificacao.controller;

import com.acc.Notificacao.Notificacao.models.Notificacao;
import com.acc.Notificacao.Notificacao.service.NotificacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class NotificacaoController {

    private final NotificacaoService notificacaoService;

    public NotificacaoController(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Notificacao>> getNotificacoes() {
        return ResponseEntity.ok(notificacaoService.findAll());
    }


}
