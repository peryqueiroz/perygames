# Build stage
FROM eclipse-temurin:17-jdk-alpine as builder
WORKDIR /app

COPY . .

RUN chmod +x ./gradlew

# ✅ Definir nome do JAR explicitamente
RUN ./gradlew clean bootJar -x test \
    && find /app/build/libs -name "*.jar" -exec echo "JAR encontrado: {}" \; \
    && cp /app/build/libs/*.jar /app/app.jar

# Runtime stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# ✅ Copiar JAR com nome fixo
COPY --from=builder /app/app.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]