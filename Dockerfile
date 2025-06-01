# Usar uma imagem oficial do Java 17 e Maven
FROM maven:3.9.6-eclipse-temurin-17

# Definir o diretório de trabalho
WORKDIR /app

# Copiar os arquivos do projeto para o container
COPY . .

# Compilar o projeto (pode demorar na primeira vez)
RUN mvn clean package -DskipTests

# Definir o comando para rodar a aplicação
CMD ["java", "-jar", "target/stormeye-0.0.1-SNAPSHOT.jar"]

# Expôr a porta da aplicação
EXPOSE 8080

