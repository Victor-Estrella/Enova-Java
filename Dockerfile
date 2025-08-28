# Etapa 1: Build do projeto
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/GSJAVA2-1.0-SNAPSHOT.jar app.jar
COPY src/main/resources/application.properties /app/application.properties
EXPOSE 8080
USER 1000:1000
CMD ["java", "-jar", "app.jar"]
