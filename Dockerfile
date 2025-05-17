# Imagen base con Java 17
FROM eclipse-temurin:17-jdk-alpine

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar todo el proyecto dentro del contenedor
COPY . .

# Dar permiso de ejecución a mvnw
RUN chmod +x mvnw

# Construir el proyecto con Maven, sin ejecutar tests
RUN ./mvnw clean install -DskipTests

# Ejecutar el .jar generado (ajusta el nombre si es diferente)
CMD ["java", "-jar", "target/restapi-0.0.1-SNAPSHOT.jar"]

