import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String FILA_PEDIDOS = "filaPedidos";
    public static final String FILA_STATUS = "filaStatus";
    public static final String EXCHANGE_PAGAMENTOS = "exchangePagamentos";


    @Bean
    public Queue filaPedidos() {
        return new Queue("filaPedidos", true); // 'true' indica que a fila é durável
    }

    @Bean
    public Queue filaStatus() {
        return new Queue(FILA_STATUS, true); // Fila durável
    }


    @Bean
    public DirectExchange exchangePagamentos() {
        return new DirectExchange(EXCHANGE_PAGAMENTOS);
    }


    @Bean
    public Binding bindingFilaPedidos(Queue filaPedidos, DirectExchange exchangePagamentos) {
        return BindingBuilder.bind(filaPedidos).to(exchangePagamentos).with("rotaPedidos");
    }

    @Bean
    public Binding bindingFilaStatus(Queue filaStatus, DirectExchange exchangePagamentos) {
        return BindingBuilder.bind(filaStatus).to(exchangePagamentos).with("rotaStatus");
    }
}
