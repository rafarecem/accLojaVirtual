# Serviço de Produto

## Visão Geral

Este microsserviço é responsável pelo gerenciamento de produtos dentro da plataforma. Ele permite o cadastro de novos produtos, incluindo informações como descrição, preço, quantidade e data de entrada. O serviço também envia dados sobre os produtos cadastrados para uma fila RabbitMQ para comunicação com outros microsserviços.

## Endpoints Disponíveis

### 1. Criar Produto

**Endpoint:**  
`POST /produtos`

**Descrição:**  
Este endpoint cria um novo produto no sistema com as informações fornecidas.

**Requisição:**

```json
{
  "descricao": "Produto Exemplo",
  "preco": 100.00,
  "quantidade": 10
}
```

**Respostas:**
- **201 CREATE:** Retorna o produto criado.
- **400 BAD REQUEST:** Se o preço ou a quantidade forem inválidos (preço negativo ou quantidade menor ou igual a zero).

**Exemplo de Resposta:**
```json
{
  "id": 1,
  "descricao": "Produto Exemplo",
  "preco": 100.00,
  "quantidade": 10,
  "dataHoraEntrada": "2025-01-30T12:34:56"
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
