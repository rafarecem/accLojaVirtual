# Serviço de Estoque

## Visão Geral
Este microsserviço é responsável pelo gerenciamento de estoque de produtos dentro da plataforma. Ele lida com as operações relacionadas ao controle de quantidade de produtos em estoque. Além disso, o serviço pode interagir com outros microsserviços, como o de Produto e Pedido, para garantir a consistência dos dados.

## Endpoints Disponíveis
### 1. Atualizar Estoque
**Endpoint:**  
`POST /estoque/atualizar`

**Descrição:**
Este endpoint atualiza a quantidade de um produto no estoque. A operação pode ser usada tanto para adicionar quanto para remover produtos do estoque.


**Requisição:**
```json
{
  "produtoId": 1,
  "quantidade": 5
}
```

**Respostas:**
- **200 OK:** Retorna a quantidade atualizada do produto no estoque.
- **400 BAD REQUEST:** Se a quantidade fornecida for inválida (menor que zero ou quantidade insuficiente no estoque)..
- **404 NOT FOUND:** Se o produto não for encontrado

**Exemplo de Resposta:**
```json
{
 "produtoId": 1,
"quantidadeAtualizada": 15
}
```
##Resiliência e Fallbacks
Este serviço **não requer autenticação direta.** No entanto, outros microsserviços que interagem com ele podem precisar validar tokens JWT para garantir a segurança da comunicação.

Para garantir resiliência, o serviço utiliza mensagens assíncronas via RabbitMQ para comunicação com outros microsserviços, o que ajuda a manter o sistema funcional mesmo em caso de falha em algum dos microsserviços dependentes.

##Tecnologias Utilizadas
- **Spring Boot**
- **JWT**
- **MY-SQL**
- **Eureka**
- **Swagger**
-**RABBITMQ**


