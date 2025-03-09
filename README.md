```markdown
# 📖 **Tome of Heroes**

**Tome of Heroes** é uma aplicação para o **gerenciamento de fichas de personagens de RPG**. O sistema permite aos jogadores e mestres de jogo criar, editar, listar e excluir personagens, armazenando informações essenciais como **atributos**, **raça**, **classe**, **magias** e **inventário**.

## 📌 **Tecnologias Usadas**

- **Java** - Linguagem de programação.
- **Spring Boot** - Framework para o desenvolvimento rápido de aplicações.
- **JPA (Hibernate)** - Framework ORM para o mapeamento objeto-relacional.
- **PostgreSQL** - Sistema de gerenciamento de banco de dados.
- **Lombok** - Biblioteca para redução de código repetitivo.
- **Spring Security** (futuro) - Para proteção de endpoints.

## 📋 **Pré-requisitos**

Antes de rodar o projeto, você precisará ter o seguinte instalado:

- [Java 17+](https://adoptopenjdk.net/)
- [Maven](https://maven.apache.org/install.html)
- [PostgreSQL](https://www.postgresql.org/download/)

## 🚀 **Como Rodar o Projeto**

1. Clone este repositório:
   ```bash
   git clone https://github.com/SEU-USUARIO/tome-of-heroes.git
   cd tome-of-heroes


2. Configure o banco de dados PostgreSQL:
   - Crie um banco de dados chamado `rpgmanager`.
   - Altere as configurações de acesso no arquivo `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/tome-of-heroes
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.jpa.hibernate.ddl-auto=update


3. Compile e rode a aplicação:

   ```bash
   mvn spring-boot:run


4. O projeto estará rodando em [http://localhost:8080](http://localhost:8080).

## 📄 **Endpoints da API**

- **POST /personagem** - Cria um novo personagem.
- **GET /personagem** - Lista todos os personagens.
- **GET /personagem/{id}** - Retorna detalhes de um personagem específico.
- **PUT /personagem/{id}** - Atualiza um personagem existente.
- **DELETE /personagem/{id}** - Exclui um personagem.

## 🛠️ **Funcionalidades Futuras**

- **Autenticação e Autorização** com Spring Security.
- **Validação de dados** nos endpoints (usando annotations do JSR-303).
- **Documentação da API** com Swagger UI (futuramente).

## 👨‍💻 **Desenvolvedor**

- **Isaac Aires** - [LinkedIn](https://www.linkedin.com/in/isaac-aires-4601a728b/)
```
