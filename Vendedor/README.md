# 🚀 Serviço de Vendedor

## 📚 Visão Geral
Este microsserviço é responsável pela gestão de vendedores dentro da plataforma. Ele permite criar, editar, excluir e obter informações sobre vendedores. Esse serviço centraliza a gestão de dados de vendedores para outros microsserviços na arquitetura.

---

## 🛠️ Endpoints Disponíveis

### 1. **Criar Vendedor**

**Endpoint:**
```http
POST /v1/vendedores
```

**Descrição:**  
Cria um novo vendedor na plataforma.

**Requisição:**
```json
{
  "vendedorNome": "Pedro",
  "vendedorSetor": "Vendas",
  "setorVendedor": "A"
}
```

**Respostas:**
- **201 Created:** Vendedor criado com sucesso.
- **400 Bad Request:** Requisição inválida (exemplo: campos obrigatórios ausentes).
- **500 Internal Server Error:** Erro ao salvar o vendedor.

**Exemplo de Resposta de Sucesso:**
```json
{
  "idVendedor": 1,
  "vendedorNome": "Pedro",
  "vendedorSetor": "Vendas",
  "setorVendedor": "A"
}
```

---

### 2. **Obter Vendedor por ID**

**Endpoint:**
```http
GET /v1/vendedores/{idVendedor}
```

**Descrição:**  
Obtém os detalhes de um vendedor específico, dado seu ID.

**Respostas:**
- **200 OK:** Retorna os dados do vendedor.
- **404 Not Found:** Vendedor não encontrado.
- **500 Internal Server Error:** Erro ao buscar o vendedor.

**Exemplo de Resposta de Sucesso:**
```json
{
  "idVendedor": 1,
  "vendedorNome": "Pedro",
  "vendedorSetor": "Vendas",
  "setorVendedor": "A"
}
```

---

### 3. **Atualizar Vendedor**

**Endpoint:**
```http
PUT /v1/vendedores/{idVendedor}
```

**Descrição:**  
Atualiza as informações de um vendedor específico, dado seu ID.

**Requisição:**
```json
{
  "vendedorNome": "João Atualizado",
  "vendedorSetor": "Vendas",
  "setorVendedor": "B"
}
```

**Respostas:**
- **200 OK:** Vendedor atualizado com sucesso.
- **404 Not Found:** Vendedor não encontrado.
- **400 Bad Request:** Requisição inválida.
- **500 Internal Server Error:** Erro ao atualizar o vendedor.

**Exemplo de Resposta de Sucesso:**
```json
{
  "idVendedor": 1,
  "vendedorNome": "João Atualizado",
  "vendedorSetor": "Vendas",
  "setorVendedor": "B"
}
```

---

### 4. **Deletar Vendedor**

**Endpoint:**
```http
DELETE /v1/vendedores/{idVendedor}
```

**Descrição:**  
Exclui um vendedor da plataforma.

**Respostas:**
- **204 No Content:** Vendedor excluído com sucesso.
- **404 Not Found:** Vendedor não encontrado.
- **500 Internal Server Error:** Erro ao excluir o vendedor.

---

## ⚠️ Códigos de Erro

O serviço utiliza os seguintes códigos de erro padronizados:

| Código  | Descrição                          |
|---------|-------------------------------------|
| 4001    | Requisição inválida                |
| 4041    | Vendedor não encontrado            |
| 5001    | Erro ao salvar/atualizar vendedor  |
| 5002    | Erro ao excluir vendedor           |
| 5003    | Erro interno do servidor           |

---




## 💻 Tecnologias Utilizadas

- **Spring Boot**
- **Jakarta Persistence API (JPA)**
- **Hibernate**
- **MySQL**
- **Swagger** para documentação
- **JUnit** para testes unitários

