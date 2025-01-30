package com.acc.NotificacaoProducer.producer;


import com.acc.NotificacaoProducer.models.NotificacaoPayload;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoProducer {

    private final RabbitTemplate rabbitTemplate;

    public NotificacaoProducer(RabbitTemplate rabbitTemplate, Jackson2JsonMessageConverter messageConverter) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setMessageConverter(messageConverter);
    }

    @Retryable(
            value = { Exception.class },
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000, multiplier = 2)
    )
    public void sendToQueue(NotificacaoPayload payload) {
        rabbitTemplate.convertAndSend("notificacao.exchange", "notificacao.routingkey", payload);
    }

    @Recover
    public void fallbackSendToQueue(Exception e, NotificacaoPayload payload) {
        System.err.println("Falha ao enviar mensagem após múltiplas tentativas: " + e.getMessage());

        // Aqui poderia ficar armazenado em um banco como redis
    }
}
