package com.acc.Pagamento.Pagamento.testes;

import com.acc.Pagamento.Pagamento.controller.PagamentoController;
import com.acc.Pagamento.Pagamento.dto.PagamentoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class PagamentoControllerTeste {


    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private PagamentoController pagamentoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    @Test
    void testEnviarParaFilaPedidos() {

        PagamentoDTO pagamentoDTO = new PagamentoDTO();
        pagamentoDTO.setIdPedido("123333");
        pagamentoDTO.setStatus("PENDENT");

        ResponseEntity<String> response = pagamentoController.enviarParaFilaPedidos(pagamentoDTO);

        verify(rabbitTemplate, times(1)).convertAndSend("exchangePagamentos", "rotaPedidos", pagamentoDTO); // Verifica se o método foi chamado corretamente
        assertEquals("Pagamento enviado para a fila de pedidos com sucesso!", response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }
}
