

---

# 📖 **Tome of Heroes**

**Tome of Heroes** é uma aplicação para o **gerenciamento de fichas de personagens de RPG**. O sistema permite aos jogadores e mestres de jogo criar, editar, listar e excluir personagens, armazenando informações essenciais como **atributos**, **raça**, **classe**, **magias** e **inventário**.

## 📌 **Tecnologias Usadas**

- **Java** - Linguagem de programação.
- **Spring Boot** - Framework para o desenvolvimento rápido de aplicações.
- **JPA (Hibernate)** - Framework ORM para o mapeamento objeto-relacional.
- **PostgreSQL** - Sistema de gerenciamento de banco de dados.
- **Lombok** - Biblioteca para redução de código repetitivo.
- **Spring Security** (futuro) - Para proteção de endpoints.
- Docker - Containers
  

## 📋 **Pré-requisitos**

Antes de rodar o projeto, você precisará ter o seguinte instalado:

- [Java 17+](https://adoptopenjdk.net/)
- [Maven](https://maven.apache.org/install.html)
- [PostgreSQL](https://www.postgresql.org/download/)

## 🚀 **Como Rodar o Projeto**

1. Clone este repositório:
   ```bash
   git clone https://github.com/Isaac-code-maker/Tome-of-Heroes
   cd tome-of-heroes
   ```

   ```properties
      # Configuração do banco de dados PostgreSQL
   spring.datasource.url=jdbc:postgresql://localhost:5432/tome-of-heroes
   spring.datasource.username=postgres
   spring.datasource.password=root
   spring.datasource.driver-class-name=org.postgresql.Driver

   # Configuração do JPA/Hibernate
   spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.format_sql=true

   # Configurações do JWT
   jwt.secret=your_jwt_secret_key
   jwt.expiration=86400000

   spring.devtools.livereload.enabled=true
   spring.devtools.restart.enabled=true
   spring.devtools.restart.exclude=static/**,public/**,resources/**
   spring.devtools.restart.additional-paths=/app/src
   ```

3. Compile e rode a aplicação:

   ```bash
   mvn spring-boot:run

   docker compose up --build
   ```

4. O projeto estará rodando em [http://localhost:8080](http://localhost:8080).

## 📄 **Endpoints da API**

- **POST /personagem** - Cria um novo personagem.
- **GET /personagem** - Lista todos os personagens.
- **GET /personagem/{id}** - Retorna detalhes de um personagem específico.
- **PUT /personagem/{id}** - Atualiza um personagem existente.
- **DELETE /personagem/{id}** - Exclui um personagem.

## 🛠️ **Funcionalidades Futuras**

- **Autenticação e Autorização** com Spring Security. (FEITO)
- **Validação de dados** nos endpoints (usando annotations do JSR-303).
- **Documentação da API** com Swagger UI (futuramente).

## 👨‍💻 **Desenvolvedor**

- **Isaac Aires** - [LinkedIn](https://www.linkedin.com/in/isaac-aires-4601a728b/)

---
