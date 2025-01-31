# Microserviço de Pagamento

Este é um microserviço responsável pelo processamento de pagamentos. Ele oferece uma API para processar pagamentos, consultar status de pagamento, gerenciar dados de pagamento associados a pedidos e integrar com sistemas de mensageria (RabbitMQ) para notificações de status.

## Funcionalidades

- Processamento de pagamentos.
- Consulta de pagamentos por ID de pedido.
- Exibição de todos os pagamentos realizados.
- Deleção de pagamentos por ID de pedido.
- Envio de mensagens para filas do RabbitMQ sobre o status do pagamento.
- Gerenciamento de pagamentos em um banco de dados MySQL.

## Tecnologias Utilizadas

- **Spring Boot** - Framework para desenvolvimento de microsserviços.
- **Spring Web** - Para criação da API REST.
- **Spring Data JPA** - Para integração com o banco de dados MySQL.
- **RabbitMQ** - Para comunicação com filas de mensagens.
- **MySQL** - Banco de dados utilizado para persistência de informações.
- **JUnit 5** - Framework de testes unitários.
- **Java 17** - Linguagem utilizada no backend.
- **Maven** - Gerenciador de dependências.

## Endpoints

### 1. Processar Pagamento

- **POST** `/v1/pagamentos`
- Processa um pagamento e envia uma mensagem para a fila de status do RabbitMQ.

**Request Body**:
```json
{
  "idPedido": 123,
  "valor": 150.00,
  "status": "PENDENTE"
}
