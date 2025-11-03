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
```json
POST /auth/register
{
  "usuario": "admin",
  "senha": "123",
  "tipoUsuario": "admin"
}
```

### Login
```json
POST /auth/login
{
  "usuario": "admin",
  "senha": "123"
}
```

### Criar Catástrofe
```json
POST /catastrofes
{
  "nome": "Tornado",
  "descricao": "Ventos muito fortes em espiral.",
  "nivelGravidade": 4
}
```

### Criar Cidade
```json
POST /cidades
{
  "nome": "São Paulo",
  "estado": "SP"
}
```

### Criar Administrador
```json
POST /administradores
{
  "nome": "Fernanda",
  "loginId": 1
}
```

### Criar Alerta
```json
POST /alertas
{
  "headline": "Tornado a caminho",
  "descricao": "Evacuar área central imediatamente.",
  "dataAlerta": "2025-06-03T10:00:00",
  "fimAlerta": "2025-06-03T20:00:00",
  "nivelGravidade": 4,
  "cidade": { "id": 1 },
  "catastrofe": { "id": 1 },
  "administrador": { "id": 1 }
}
```

---

📘 **StormEye API — Desenvolvido com propósito, tecnologia e empatia.**
