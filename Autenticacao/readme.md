# Serviço de Autenticação

## Visão Geral
Este microsserviço é responsável pela autenticação de usuários utilizando JSON Web Token (JWT). Ele centraliza a gestão de autenticação para outros microsserviços dentro da arquitetura.

## Endpoints Disponíveis
### 1. Login
**Endpoint:**
```http
POST /v1/auth/login
```
**Descrição:**
Autentica um usuário e retorna um token JWT.

**Requisição:**
```json
{
  "username": "usuario",
  "password": "senha"
}
```

**Respostas:**
- **200 OK:** Retorna o token JWT no corpo da resposta e no cabeçalho `Authorization`.
- **401 Unauthorized:** Credenciais inválidas.

**Exemplo de Resposta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### 2. Cadastro
**Endpoint:**
```http
POST /v1/auth/cadastro
```
**Descrição:**
Registra um novo usuário na plataforma.

**Requisição:**
```json
{
  "username": "novo_usuario",
  "password": "senha_segura"
}
```

**Respostas:**
- **204 No Content:** Cadastro realizado com sucesso.
- **400 Bad Request:** Usuário já existe.

## Autenticação JWT
Após realizar login, o cliente deve incluir o token JWT em todas as requisições subsequentes no cabeçalho `Authorization`:
```http
Authorization: Bearer {token}
```

## Códigos de Erro
O serviço utiliza os seguintes códigos de erro padronizados:

| Código  | Descrição                     |
|---------|--------------------------------|
| 4011    | Falha na autenticação         |
| 40011   | Usuário já existe             |
| 4040    | Usuário não encontrado        |
| 40021   | Token inválido                |
| 5001    | Erro interno do servidor      |
| 5002    | Falha ao ler chave privada    |
| 5003    | Falha ao ler chave pública    |
| 5004    | Chave privada mal formatada   |

## Resiliência e Fallbacks
Esse serviço futuramente deve ter implementado mecanismos para fallback, garantindo disponibilidade e segurança mesmo em caso de falha segue algumas ideias:
- **Cache de tokens válidos** para autenticação temporária caso o serviço principal esteja indisponível.
- **Implementacao de segurança nos outros serviços** para assegurar que estejam seguros mesmo que este caia.

## Tecnologias Utilizadas
- **Spring Boot**
- **JWT**
- **OPEN-SSL**
- **MY-SQL**
- **Eureka**
- **Swagger**



