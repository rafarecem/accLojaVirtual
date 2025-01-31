package com.acc.Pagamento.Pagamento.testes;

import com.acc.Pagamento.Pagamento.dto.PagamentoDTO;
import com.acc.Pagamento.Pagamento.producer.PagamentoProducer;
import com.acc.Pagamento.Pagamento.service.PagamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class PagamentoServiceTeste {

    @Mock
    private PagamentoProducer pagamentoProducer;

    @InjectMocks
    private PagamentoService pagamentoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessarPagamento() {
        PagamentoDTO pagamentoDTO = new PagamentoDTO();
        pagamentoDTO.setIdPedido("12345");
        pagamentoDTO.setStatus("PENDENTE");

        pagamentoService.processarPagamento(pagamentoDTO);


        verify(pagamentoProducer).enviarMensagemParaFilaStatus(pagamentoDTO);
        assert "PAGO".equals(pagamentoDTO.getStatus());
    }
}
