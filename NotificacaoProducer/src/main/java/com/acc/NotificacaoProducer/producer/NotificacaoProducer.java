package com.acc.NotificacaoProducer.producer;


import com.acc.NotificacaoProducer.models.NotificacaoPayload;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoProducer {

    private final RabbitTemplate rabbitTemplate;

    public NotificacaoProducer(RabbitTemplate rabbitTemplate, Jackson2JsonMessageConverter messageConverter) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setMessageConverter(messageConverter);
    }

    public void sendToQueue(NotificacaoPayload payload) {
        rabbitTemplate.convertAndSend("notificacao.exchange", "notificacao.routingkey", payload);
    }
}
