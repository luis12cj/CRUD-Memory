# Etapa 1: Construcción (Build)
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
# Copiamos los archivos de tu proyecto
COPY pom.xml .
COPY src ./src
# Compilamos el proyecto omitiendo los tests para que sea rápido
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Run)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Copiamos el archivo .jar generado en la etapa anterior
COPY --from=build /app/target/*.jar app.jar
# Exponemos el puerto 8080
EXPOSE 8080
# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
