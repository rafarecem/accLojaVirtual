package com.acc.Estoque.Estoque.configtest;
import com.acc.Estoque.Estoque.config.RabbitMQConfigEstoque;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.amqp.core.Queue;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RabbitMQConfigEstoqueTest {
    @Autowired
    private Queue filaEstoque;

    @Test
    public void testFilaEstoqueBean() {
        // Verifica se o bean filaEstoque foi criado corretamente
        assertThat(filaEstoque).isNotNull();
  //      assertThat(filaEstoque.getName()).isEqualTo(RabbitMQConfigEstoque.FILA_ESTOQUE);
    }
}
