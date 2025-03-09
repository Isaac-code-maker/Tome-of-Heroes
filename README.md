project:
  name: "Tome of Heroes"
  description: "Aplicação para gerenciamento de fichas de personagens de RPG."
  features:
    - Criação de personagens
    - Edição de personagens
    - Listagem de personagens
    - Exclusão de personagens
  technologies:
    - Java
    - Spring Boot
    - JPA (Hibernate)
    - PostgreSQL
    - Lombok
    - Spring Security (futuro)
  prerequisites:
    - "Java 17+"
    - "Maven"
    - "PostgreSQL"
  how_to_run:
    steps:
      - "Clone este repositório: git clone https://github.com/SEU-USUARIO/tome-of-heroes.git"
      - "Configure o banco de dados PostgreSQL"
      - "Altere as configurações de acesso no arquivo src/main/resources/application.properties"
      - "Compile e rode a aplicação com mvn spring-boot:run"
      - "Acesse o projeto em http://localhost:8080"
  api_endpoints:
    - method: "POST"
      endpoint: "/personagem"
      description: "Cria um novo personagem."
    - method: "GET"
      endpoint: "/personagem"
      description: "Lista todos os personagens."
    - method: "GET"
      endpoint: "/personagem/{id}"
      description: "Retorna detalhes de um personagem específico."
    - method: "PUT"
      endpoint: "/personagem/{id}"
      description: "Atualiza um personagem existente."
    - method: "DELETE"
      endpoint: "/personagem/{id}"
      description: "Exclui um personagem."
  future_features:
    - "Autenticação e Autorização com Spring Security"
    - "Validação de dados nos endpoints"
    - "Documentação da API com Swagger UI"
  developer:
    name: "Isaac Aires"
    linkedin: "https://www.linkedin.com/in/isaac-aires-4601a728b/"
