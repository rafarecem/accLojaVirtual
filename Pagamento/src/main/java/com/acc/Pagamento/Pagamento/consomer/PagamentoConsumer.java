package com.acc.Pagamento.Pagamento.consomer;



import com.acc.Pagamento.Pagamento.dto.PagamentoDTO;
import com.acc.Pagamento.Pagamento.service.PagamentoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PagamentoConsumer {

    private final PagamentoService pagamentoService;

    public PagamentoConsumer(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @RabbitListener(queues = "filaPedidos")
    public void consumirMensagem(PagamentoDTO pagamentoDTO) {

        System.out.println("Mensagem recebida da fila de pedidos: " + pagamentoDTO);
        pagamentoService.processarPagamento(pagamentoDTO);
    }
}
