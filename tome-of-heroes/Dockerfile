# Usa uma imagem base do Maven para compilar e rodar a aplicação no desenvolvimento
FROM maven:3.8.5-openjdk-17

# Define o diretório de trabalho
WORKDIR /app

# Copia o código-fonte para dentro do container
COPY . .

# Expõe a porta 8080
EXPOSE 8080

# Habilita o Live Reload e roda a aplicação diretamente no modo de desenvolvimento
ENTRYPOINT ["mvn", "spring-boot:run"]
