# Etapa de construção do container

#caso o servidor VPS use o ubunto
# FROM ubunto:latest AS build

#os comentados não funcionaram
# FROM maven:3.4.3-openjdk-21 AS build
#FROM openjdk:21-slim AS build
#RUN apt-get update && apt-get-install -y maven && apt-get clean &&rm -rf/var/lib/apt/lists/*
FROM maven:3.9.6-eclipse-temurin-21 AS build


# Definir o diretório dentro do container para a aplicação
WORKDIR /app

# Copiar o pom.xml para o container
#os comentados não funcionaram
# COPY pom.xml .
# COPY src ./src
COPY mvcapp/pom.xml /app/
COPY mvcapp/src /app/src/

# Executar o Maven para compilar o projet e gerar o JAR File
RUN mvn clean package -DskipTests

# Etapa de execução
FROM openjdk:21-slim

# Definir o diretório de trabalho para a applicação
WORKDIR /app

# Copiar o JAR construído na etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Definir a porta que será utilizada na aplicação
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT [ "java", "-jar", "app.jar" ]

# Depois usar o "git add ."
# Depois o "git commit -m "Adiciona o docker para deployment no Render.com"
# E por ultimo o "git push origin main"