# Usar una imagen base de Java 21 (Temurin)
FROM eclipse-temurin:21-jdk-jammy

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Al estar el Dockerfile en la raíz, copiamos directo desde target/
COPY target/api-orders-*.jar app.jar

# Puerto que expone la aplicación de órdenes
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]