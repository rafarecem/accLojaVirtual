package com.acc.Pedido.Pedido.servicetest;

import com.acc.Pedido.Pedido.Enum.StatusPedido;
import com.acc.Pedido.Pedido.dto.PedidoDTO;
import com.acc.Pedido.Pedido.model.Pedido;
import com.acc.Pedido.Pedido.model.PedidoHistoricoStatus;
import com.acc.Pedido.Pedido.repository.PedidoHistoricoStatusRepository;
import com.acc.Pedido.Pedido.repository.PedidoRepository;
import com.acc.Pedido.Pedido.service.PedidoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {
    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private PedidoHistoricoStatusRepository historicoRepository;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Test
    void criarPedido_DeveSalvarPedidoEEnviarMensagem() {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setPedidoDescricao("Produto Teste");
        pedidoDTO.setPedidoValor(100.0);
        pedidoDTO.setPedidoQuantidade(2);
        pedidoDTO.setVendedor_idVendedor(1L);

        Pedido pedido = new Pedido();
        pedido.setId(1L); // Certifique-se que o ID está correto
        pedido.setPedidoDescricao("Produto Teste");
        pedido.setProdutoNome("Produto Teste"); // Adicione o valor corretamente
        pedido.setPedidoValor(100.0);
        pedido.setPedidoQuantidade(2);
        pedido.setVendedor_idVendedor(1L);

        Mockito.when(pedidoRepository.save(Mockito.any(Pedido.class))).thenAnswer(invocation -> {
            Pedido p = invocation.getArgument(0);
            p.setId(1L); // Simula o comportamento de salvar e gerar ID
            return p;
        });

        // Act
        PedidoDTO resultado = pedidoService.criarPedido(pedidoDTO);

        // Assert
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("Produto Teste", resultado.getPedidoDescricao());
        Assertions.assertEquals(100.0, resultado.getPedidoValor());
        Assertions.assertEquals(2, resultado.getPedidoQuantidade());

        // Verifica se o pedido foi salvo corretamente
        Mockito.verify(pedidoRepository, Mockito.times(1)).save(Mockito.any(Pedido.class));

        // Verifica se o histórico foi salvo corretamente
        Mockito.verify(historicoRepository, Mockito.times(1)).save(Mockito.any(PedidoHistoricoStatus.class));

        // Verifica se a mensagem foi enviada para RabbitMQ com a string esperada
        Mockito.verify(rabbitTemplate, Mockito.times(1))
                .convertAndSend("pedido-exchange", "pedido-routing-key", "1;Produto Teste;2");
    }


    @Test
    void criarPedido_DeveLancarExcecao_SeDescricaoEstiverVazia() {
        // Arrange
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setPedidoDescricao("");
        pedidoDTO.setPedidoQuantidade(1);

        // Act & Assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            pedidoService.criarPedido(pedidoDTO);
        });

        Assertions.assertEquals("A descrição do pedido não pode estar vazia.", exception.getMessage());
        Mockito.verifyNoInteractions(pedidoRepository, historicoRepository, rabbitTemplate);
    }

    @Test
    void atualizarStatusPedido_DeveAtualizarStatus() {
        // Arrange
        Pedido pedido = new Pedido();
        pedido.setId(1L);

        Mockito.when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        // Act
        pedidoService.atualizarStatusPedido(1L, StatusPedido.CONFIRMADO);

        // Assert
        Mockito.verify(pedidoRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(historicoRepository, Mockito.times(1)).save(Mockito.any(PedidoHistoricoStatus.class));
    }

    @Test
    void buscarStatusAtualPedido_DeveRetornarStatusAtual_SeEncontrado() {
        // Arrange
        PedidoHistoricoStatus historico = new PedidoHistoricoStatus();
        historico.setStatus(StatusPedido.CONFIRMADO);

        Mockito.when(historicoRepository.findFirstByPedidoIdOrderByDataHoraStatusPedidoDesc(1L))
                .thenReturn(Optional.of(historico));

        // Act
        StatusPedido resultado = pedidoService.buscarStatusAtualPedido(1L);

        // Assert
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(StatusPedido.CONFIRMADO, resultado);

        Mockito.verify(historicoRepository, Mockito.times(1))
                .findFirstByPedidoIdOrderByDataHoraStatusPedidoDesc(1L);
    }

    @Test
    void buscarStatusAtualPedido_DeveLancarExcecao_SeNaoEncontrado() {
        // Arrange
        Mockito.when(historicoRepository.findFirstByPedidoIdOrderByDataHoraStatusPedidoDesc(1L))
                .thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            pedidoService.buscarStatusAtualPedido(1L);
        });

        Assertions.assertEquals("Pedido não encontrado", exception.getMessage());
        Mockito.verify(historicoRepository, Mockito.times(1))
                .findFirstByPedidoIdOrderByDataHoraStatusPedidoDesc(1L);
    }

    @Test
    void buscarPedido_DeveRetornarPedidoDTO_SeEncontrado() {
        // Arrange
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setPedidoDescricao("Produto Teste");

        Mockito.when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        // Act
        PedidoDTO resultado = pedidoService.buscarPedido(1L);

        // Assert
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("Produto Teste", resultado.getPedidoDescricao());
        Mockito.verify(pedidoRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void deletarPedido_DeveDeletarPedido_SeEncontrado() {
        // Arrange
        Pedido pedido = new Pedido();
        pedido.setId(1L);

        Mockito.when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        // Act
        pedidoService.deletarPedido(1L);

        // Assert
        Mockito.verify(pedidoRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(pedidoRepository, Mockito.times(1)).delete(pedido);
    }

    @Test
    void deletarPedido_DeveLancarExcecao_SePedidoNaoEncontrado() {
        // Arrange
        Mockito.when(pedidoRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            pedidoService.deletarPedido(1L);
        });

        Assertions.assertEquals("Pedido não encontrado.", exception.getMessage());
        Mockito.verify(pedidoRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(pedidoRepository, Mockito.never()).delete(Mockito.any(Pedido.class));
    }
}
