package com.acc.Notificacao.Notificacao.controller;

import com.acc.Notificacao.Notificacao.models.Notificacao;
import com.acc.Notificacao.Notificacao.service.NotificacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.acc.Notificacao.Notificacao.docs.DocStrings.ControllerDocs.*;

@RestController
@RequestMapping("/v1")
@Tag(name = TAG_NAME, description = TAG_DESC)
public class NotificacaoController {

    private final NotificacaoService notificacaoService;

    public NotificacaoController(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @Operation(
            summary = GET_SUMM,
            description = GET_DESC
    )
    @GetMapping("/get")
    public ResponseEntity<List<Notificacao>> getNotificacoes() {
        return ResponseEntity.ok(notificacaoService.findAll());
    }


}
