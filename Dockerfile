FROM maven:3.8.8-amazoncorretto-21 AS build

# Copia tudo de src para dentro de app/src
COPY src app/src
COPY pom.xml /app

# Entra em /app
WORKDIR /app

# Faz a instalação das dependencias e build do projeto
RUN mvn clean install

# Pega o jdk
FROM openjdk:21-ea-21-jdk-slim

# Copia do build gerado o jar que foi criado dentro de target e passa para app/app.jar
COPY --from=build ./app/target/*.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

# Executa o comando para rodar o app
CMD ["java", "-jar", "app.jar"]