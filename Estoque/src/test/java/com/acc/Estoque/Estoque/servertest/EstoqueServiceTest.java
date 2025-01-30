package com.acc.Estoque.Estoque.servertest;
import static org.mockito.Mockito.*;

import com.acc.Estoque.Estoque.dto.PedidoMessageDTO;
import com.acc.Estoque.Estoque.dto.ProdutoMessageDTO;
import com.acc.Estoque.Estoque.model.Estoque;
import com.acc.Estoque.Estoque.repository.EstoqueRepository;
import com.acc.Estoque.Estoque.service.EstoqueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
public class EstoqueServiceTest {
    @Mock
    private EstoqueRepository estoqueRepository;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private EstoqueService estoqueService;

    private ProdutoMessageDTO produtoMessageDTO;
    private PedidoMessageDTO pedidoMessageDTO;
    private Estoque estoque;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        produtoMessageDTO = new ProdutoMessageDTO();
        produtoMessageDTO.setId(1L);
        produtoMessageDTO.setDescricao("Produto Teste");
        produtoMessageDTO.setQuantidade(100);

        pedidoMessageDTO = new PedidoMessageDTO();
        pedidoMessageDTO.setIdPedido(1L);
        pedidoMessageDTO.setIdProduto(1L);
        pedidoMessageDTO.setQuantidade(50);

        estoque = new Estoque();
        estoque.setProdutoId(1L);
        estoque.setDescricaoProduto("Produto Teste");
        estoque.setQuantidadeDisponivel(100);
        estoque.setDataAtualizacao(LocalDateTime.now());
    }

    @Test
    void testarReceberProduto() {
        estoqueService.receberProduto(produtoMessageDTO);
        verify(estoqueRepository, times(1)).save(any(Estoque.class));
    }

    @Test
    void testarVerificarEstoqueComEstoqueSuficiente() {
        when(estoqueRepository.findByProdutoId(anyLong())).thenReturn(Optional.of(estoque));
        estoqueService.verificarEstoque(pedidoMessageDTO);
        verify(estoqueRepository, times(1)).save(any(Estoque.class));
        verify(rabbitTemplate, times(1)).convertAndSend(eq("estoqueQueue"), anyString());
    }

    @Test
    void testarVerificarEstoqueComEstoqueInsuficiente() {
        estoque.setQuantidadeDisponivel(30);
        when(estoqueRepository.findByProdutoId(anyLong())).thenReturn(Optional.of(estoque));
        estoqueService.verificarEstoque(pedidoMessageDTO);
        verify(estoqueRepository, times(0)).save(any(Estoque.class)); // Não deve salvar porque o estoque é insuficiente
        verify(rabbitTemplate, times(1)).convertAndSend(eq("estoqueQueue"), anyString());
    }
}
