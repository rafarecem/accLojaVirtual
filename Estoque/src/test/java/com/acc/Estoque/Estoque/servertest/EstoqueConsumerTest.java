package com.acc.Estoque.Estoque.servertest;
import com.acc.Estoque.Estoque.service.EstoqueConsumer;
import com.acc.Estoque.Estoque.service.EstoqueService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EstoqueConsumerTest {

    @Autowired
    private EstoqueConsumer estoqueConsumer;

    @MockBean
    private EstoqueService estoqueService;

    @Test
    public void testProcessarMensagem() {
        // ProdutoId simulado
        Long produtoId = 1L;

        // Chama o método de consumo da mensagem
        estoqueConsumer.processarMensagem(produtoId);

        // Verifica se o método atualizarEstoque foi chamado com o produtoId correto
        verify(estoqueService, times(1)).atualizarEstoque(produtoId);
    }
}
