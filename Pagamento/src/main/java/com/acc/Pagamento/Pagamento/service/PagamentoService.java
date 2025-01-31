package com.acc.Pagamento.Pagamento.service;


import com.acc.Pagamento.Pagamento.dto.PagamentoDTO;
import com.acc.Pagamento.Pagamento.producer.PagamentoProducer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {

    private final PagamentoProducer pagamentoProducer;


    private final List<PagamentoDTO> pagamentos = new ArrayList<>();

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

    public List<PagamentoDTO> obterTodosPagamentos() {
        return pagamentos;
    }

    public Optional<PagamentoDTO> obterPagamentoPorId(Long idPedido) {
        return pagamentos.stream()
                .filter(pagamento -> pagamento.getIdPedido().equals(idPedido))
                .findFirst();
    }

    public boolean deletarPagamentoPorId(Long idPedido) {
        return pagamentos.removeIf(pagamento -> pagamento.getIdPedido().equals(idPedido));
    }
}

