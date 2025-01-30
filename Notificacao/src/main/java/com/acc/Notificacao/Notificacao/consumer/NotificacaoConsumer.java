package com.acc.Notificacao.Notificacao.consumer;

import com.acc.Notificacao.Notificacao.models.NotificacaoPayload;
import com.acc.Notificacao.Notificacao.service.NotificacaoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoConsumer {

    private final NotificacaoService notificacaoService;

    public NotificacaoConsumer(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @RabbitListener(queues = "notificacao.queue")
    public void consume(NotificacaoPayload payload) {
        notificacaoService.save(payload);
    }
}
