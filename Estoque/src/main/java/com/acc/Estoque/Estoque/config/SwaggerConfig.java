package com.acc.Estoque.Estoque.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(List.of(
                        new Server().url("http://localhost:8090").description("Microserviço pedido"),
                        new Server().url("http://localhost:8084").description("Microserviço Produto"),
                        new Server().url("http://localhost:8085").description("Microserviço estoque")
                ));
    }
}
