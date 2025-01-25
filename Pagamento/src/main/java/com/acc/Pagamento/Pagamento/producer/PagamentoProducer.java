package com.acc.Pagamento.Pagamento.producer;

import com.acc.Pagamento.Pagamento.dto.PagamentoDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class PagamentoProducer {

    private final RabbitTemplate rabbitTemplate;

    public PagamentoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarMensagemParaFilaStatus(PagamentoDTO pagamentoDTO) {

        System.out.println("Enviando mensagem para a fila de status: " + pagamentoDTO);
        rabbitTemplate.convertAndSend("exchangePagamentos", "rotaStatus", pagamentoDTO);
    }
}
