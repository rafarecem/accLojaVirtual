# Serviço de Pedido

## Visão Geral
Este microsserviço é responsável pelo gerenciamento de pedidos dentro da plataforma. Ele permite a criação, consulta e atualização de pedidos realizados pelos usuários. Além disso, o serviço interage com os microsserviços de Produto e Estoque para garantir que o pedido seja processado de forma correta, verificando a disponibilidade do produto no estoque e realizando as atualizações necessárias.

## Endpoints Disponíveis
### 1. Criar Pedido
**Endpoint:**  
`POST /pedidos`

**Descrição:**  
Este endpoint cria um novo pedido no sistema com os produtos solicitados.


**Requisição:**
```json
{
 "idPedido": 12345,
  "idProduto": 67890,
  "quantidade": 3,
  "status": "PENDENTE",
  "dataHoraPedido": "2025-01-30T14:30:00",
  "idVendedor": 54321
}
```

**Respostas:**
- **201 CREATE:** Retorna o pedido criado com as informações do pedido e status.
- **400 BAD REQUEST:** Se a quantidade solicitada de um produto for maior que a disponível em estoque ou se houver algum problema de validação.
- **404 NOT FOUND:** Se algum produto não for encontrado.

**Exemplo de Resposta:**
```json
{
  "idPedido": 12345,
  "idProduto": 67890,
  "quantidade": 3,
  "status": "PENDENTE",
  "dataHoraPedido": "2025-01-30T14:30:00",
  "idVendedor": 54321,
}
```

### 1. Consultar pedido
**Endpoint:**  
`GET /pedidos/{id}s`

**Descrição:**  
Este endpoint permite consultar o status e os detalhes de um pedido específico.

**Requisição:**

```json
{
 "idPedido": 1,
}
```

**Respostas:**
- **200 CREATE:** Retorna os detalhes do pedido.
- **404 NOT FOUND:** Se o pedido não for encontrado
  

**Exemplo de Resposta:**
```json
{
  "idPedido": 1,
  "idProduto": 67890,
  "quantidade": 3,
  "status": "PENDENTE",
  "dataHoraPedido": "2025-01-30T14:30:00",
  "idVendedor": 54321,
}
```


**Resiliência e Fallbacks**
Este serviço **não requer autenticação direta.** No entanto, outros microsserviços que interagem com ele podem precisar validar tokens JWT para garantir a segurança da comunicação.

Para garantir resiliência, o serviço utiliza mensagens assíncronas via RabbitMQ para comunicação com outros microsserviços, o que ajuda a manter o sistema funcional mesmo em caso de falha em algum dos microsserviços dependentes.

**Tecnologias Utilizadas**
- **Spring Boot**
- **JWT**
- **MY-SQL**
- **Eureka**
- **Swagger**
-**RABBITMQ**
