package com.acc.NotificacaoProducer.controller;

import com.acc.NotificacaoProducer.models.NotificacaoPayload;
import com.acc.NotificacaoProducer.models.NotificacaoSendDTO;
import com.acc.NotificacaoProducer.producer.NotificacaoProducer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

import static com.acc.NotificacaoProducer.docs.DocStrings.ControllerDocs.*;
import static com.acc.NotificacaoProducer.docs.DocStrings.Response.NO_CONTENT;

@RestController
@RequestMapping("/v1")
@Tag(name = TAG_NAME, description = TAG_DESC)
public class NotificacaoController {

    private final NotificacaoProducer notificacaoProducer;

    public NotificacaoController(NotificacaoProducer notificacaoProducer) {
        this.notificacaoProducer = notificacaoProducer;
    }

    @PostMapping("/send")
    @Operation(
            summary = SEND_SUMM,
            description = SEND_DESC,
            responses = {
                    @ApiResponse(
                            responseCode = NO_CONTENT
                    )
            }
    )
    public ResponseEntity<Void> send(@RequestBody NotificacaoSendDTO dto) {
        notificacaoProducer.sendToQueue(new NotificacaoPayload(
                dto.mensagem(), ZonedDateTime.now()
        ));
        return ResponseEntity.noContent().build();
    }
}
