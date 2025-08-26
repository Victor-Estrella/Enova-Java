FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
USER 1000:1000
CMD ["java", "-jar", "app.jar"]
