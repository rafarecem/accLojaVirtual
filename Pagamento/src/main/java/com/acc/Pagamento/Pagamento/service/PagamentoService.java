package com.acc.Pagamento.Pagamento.service;


import com.acc.Pagamento.Pagamento.dto.PagamentoDTO;
import com.acc.Pagamento.Pagamento.producer.PagamentoProducer;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    private final PagamentoProducer pagamentoProducer;

    public PagamentoService(PagamentoProducer pagamentoProducer) {
        this.pagamentoProducer = pagamentoProducer;
    }

    public void processarPagamento(PagamentoDTO pagamentoDTO) {
        System.out.println("Processando pagamento para o pedido: " + pagamentoDTO.getIdPedido());

        pagamentoDTO.setStatus("PAGO");

        // Envia mensagem para a fila de status
        pagamentoProducer.enviarMensagemParaFilaStatus(pagamentoDTO);

        System.out.println("Pagamento processado e mensagem enviada para a fila de status.");
    }
}

