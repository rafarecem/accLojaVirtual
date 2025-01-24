package com.acc.NotificacaoProducer.controller;

import com.acc.NotificacaoProducer.models.NotificacaoPayload;
import com.acc.NotificacaoProducer.models.NotificacaoSendDTO;
import com.acc.NotificacaoProducer.producer.NotificacaoProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("/v1")
public class NotificacaoController {

    private final NotificacaoProducer notificacaoProducer;

    public NotificacaoController(NotificacaoProducer notificacaoProducer) {
        this.notificacaoProducer = notificacaoProducer;
    }

    @PostMapping("/send")
    public ResponseEntity<Void> send(@RequestBody NotificacaoSendDTO dto) {
        notificacaoProducer.sendToQueue(new NotificacaoPayload(
                dto.mensagem(), ZonedDateTime.now()
        ));
        return ResponseEntity.noContent().build();
    }
}
