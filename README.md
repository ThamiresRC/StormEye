# 🌪️ StormEye API - Back-end

> “Nós não temos um produto. Nós temos segundas chances.”

StormEye é um sistema completo para **alertas de desastres naturais** e **educação em situações de sobrevivência**, desenvolvido com foco em **salvar vidas**.  
Com esta API, **administradores** podem cadastrar alertas em tempo real, enquanto **usuários** acessam informações claras e organizadas sobre como agir em diferentes catástrofes.

---

## 🧠 Motivação Global Solution

> “A cada segundo, vidas são afetadas por desastres. O StormEye surge como um farol, guiando pessoas com informação clara, rápida e que pode salvar vidas.”

### Principais Destaques
- ⛈️ Alertas em tempo real sobre desastres naturais  
- 📚 Cartilhas de sobrevivencialismo disponíveis offline  
- 📱 Aplicativo mobile com interface intuitiva  
- 🧭 Informações essenciais para aventuras, trilhas e situações de risco  

---

## 👨‍💻 Integrantes do Projeto

| Nome | RM |
|------|----|
| Thamires Ribeiro Cruz | RM558128 |
| Adonay Rodrigues da Rocha | RM558782 |
| Pedro Henrique Martins dos Reis | RM555306 |

---

## 🔧 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Security + JWT**
- **MySQL / Oracle**
- **Swagger (OpenAPI)**
- **Lombok**

---

## 🛠️ Funcionalidades da API

- Cadastro de logins (usuários admin ou cliente)  
- Autenticação com JWT (login e geração de token)  
- CRUD de Administradores  
- CRUD de Clientes  
- CRUD de Cidades  
- CRUD de Catástrofes  
- CRUD de Alertas (relacionando todos os itens acima)  
- Filtro de alertas por cidade, tipo, gravidade e período  
- Documentação via Swagger  

---

## 🔐 Autenticação

- **Endpoint de login:** `POST /auth/login`  
- **Token:** deve ser enviado via header  
  `Authorization: Bearer {token}`  

Usuários do tipo **admin** podem realizar todas as operações.  
O token já garante as permissões de acesso.

---

## 🔗 Documentação (Swagger)

Após rodar o projeto, acesse:  
👉 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## 📦 Como Rodar Localmente

```bash
# 1. Clone o repositório
git clone https://github.com/ThamiresRC/StormEye.git
cd StormEye

# 2. Configure o banco de dados (MySQL)
# Crie um banco chamado stormeye e edite o arquivo application-prod.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/stormeye
spring.datasource.username=root
spring.datasource.password=admin

# ⚠️ Você também pode usar Oracle ou H2, ajustando a URL e o dialeto.
```

Após iniciar o projeto, acesse novamente o Swagger em:  
👉 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## 💡 Exemplos de JSON para Testes

### Criar Login
## 🌪️ Entidade: Catástrofe

### 🟢 POST `/catastrofes`
Cria uma nova catástrofe.

**Exemplo de Requisição**
```json
{
  "nome": "Tempestade Elétrica",
  "descricao": "Descargas elétricas intensas e risco de alagamento.",
  "nivelGravidade": 3,
  "localizacao": "Campinas - SP"
}
```

**Resposta (201 Created)**
```json
{
  "id_catastrofe": 1,
  "nome": "Tempestade Elétrica",
  "descricao": "Descargas elétricas intensas e risco de alagamento.",
  "nivelGravidade": 3,
  "localizacao": "Campinas - SP"
}
```

---

### 🔵 GET `/catastrofes`
Retorna todas as catástrofes cadastradas.

**Resposta (200 OK)**
```json
[
  {
    "id_catastrofe": 1,
    "nome": "Tempestade Elétrica",
    "descricao": "Descargas elétricas intensas e risco de alagamento.",
    "nivelGravidade": 3,
    "localizacao": "Campinas - SP"
  },
  {
    "id_catastrofe": 2,
    "nome": "Tornado Litoral Norte",
    "descricao": "Ventos fortes e destruição parcial de casas.",
    "nivelGravidade": 4,
    "localizacao": "Ubatuba - SP"
  }
]
```

---

### 🟡 GET `/catastrofes/{id}`
Retorna uma catástrofe específica pelo ID.

**Exemplo**
```
GET /catastrofes/1
```

**Resposta (200 OK)**
```json
{
  "id_catastrofe": 1,
  "nome": "Tempestade Elétrica",
  "descricao": "Descargas elétricas intensas e risco de alagamento.",
  "nivelGravidade": 3,
  "localizacao": "Campinas - SP"
}
```

---

### 🟠 PUT `/catastrofes/{id}`
Atualiza uma catástrofe existente.

**Exemplo**
```
PUT /catastrofes/1
```

**Corpo da Requisição**
```json
{
  "nome": "Tempestade Elétrica (Atualizada)",
  "descricao": "Descargas elétricas moderadas, situação controlada.",
  "nivelGravidade": 2,
  "localizacao": "Campinas - SP"
}
```

**Resposta (200 OK)**
```json
{
  "mensagem": "Catástrofe atualizada com sucesso."
}
```

---

### 🔴 DELETE `/catastrofes/{id}`
Remove uma catástrofe pelo ID.

**Exemplo**
```
DELETE /catastrofes/1
```

**Resposta (200 OK)**
```json
{
  "mensagem": "Catástrofe removida com sucesso."
}
```

---

## ⚠️ Entidade: Alerta

### 🟢 POST `/alertas`
Cria um novo alerta vinculado a uma catástrofe.

**Exemplo de Requisição**
```json
{
  "headline": "Alerta de Enchente",
  "descricao": "Ruas alagadas e risco de transbordamento de rios.",
  "nivelGravidade": 4,
  "dataAlerta": "2025-11-04T03:00:00Z",
  "fimAlerta": "2025-11-04T09:00:00Z",
  "catastrofeId": 1,
  "cidadeId": 1,
  "adminId": 1
}
```

**Resposta (201 Created)**
```json
{
  "id": 1,
  "headline": "Alerta de Enchente",
  "descricao": "Ruas alagadas e risco de transbordamento de rios.",
  "nivelGravidade": 4,
  "dataAlerta": "2025-11-04T03:00:00Z",
  "fimAlerta": "2025-11-04T09:00:00Z",
  "catastrofeId": 1,
  "cidadeId": 1,
  "adminId": 1
}
```

---

### 🔵 GET `/alertas`
Retorna todos os alertas cadastrados.

**Resposta (200 OK)**
```json
[
  {
    "id": 1,
    "headline": "Alerta de Enchente",
    "descricao": "Ruas alagadas e risco de transbordamento de rios.",
    "nivelGravidade": 4,
    "dataAlerta": "2025-11-04T03:00:00Z",
    "fimAlerta": "2025-11-04T09:00:00Z",
    "catastrofeId": 1,
    "cidadeId": 1,
    "adminId": 1
  }
]
```

---

### 🟡 GET `/alertas/{id}`
Retorna um alerta específico pelo ID.

**Exemplo**
```
GET /alertas/1
```

**Resposta (200 OK)**
```json
{
  "id": 1,
  "headline": "Alerta de Enchente",
  "descricao": "Ruas alagadas e risco de transbordamento de rios.",
  "nivelGravidade": 4,
  "dataAlerta": "2025-11-04T03:00:00Z",
  "fimAlerta": "2025-11-04T09:00:00Z",
  "catastrofeId": 1,
  "cidadeId": 1,
  "adminId": 1
}
```

---

### 🟠 PUT `/alertas/{id}`
Atualiza um alerta existente.

**Exemplo**
```
PUT /alertas/1
```

**Corpo da Requisição**
```json
{
  "headline": "Alerta de Enchente (Atualizado)",
  "descricao": "Situação controlada, risco reduzido.",
  "nivelGravidade": 2,
  "dataAlerta": "2025-11-04T03:00:00Z",
  "fimAlerta": "2025-11-04T12:00:00Z",
  "catastrofeId": 1,
  "cidadeId": 1,
  "adminId": 1
}
```

**Resposta (200 OK)**
```json
{
  "mensagem": "Alerta atualizado com sucesso."
}
```

---

### 🔴 DELETE `/alertas/{id}`
Remove um alerta existente pelo ID.

**Exemplo**
```
DELETE /alertas/1
```

**Resposta (200 OK)**
```json
{
  "mensagem": "Alerta removido com sucesso."
}
```

---

📘 **StormEye API — Desenvolvido com propósito, tecnologia e empatia.**
