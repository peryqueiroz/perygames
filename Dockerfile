# Build stage
FROM eclipse-temurin:17-jdk-alpine as builder

# Instalar dependências básicas
RUN apk add --no-cache bash

WORKDIR /app

# Copiar arquivos do gradle primeiro (para melhor cache)
COPY gradle gradle
COPY gradlew .
COPY build.gradle.kts .
COPY settings.gradle.kts .

# Dar permissão e baixar dependências
RUN chmod +x gradlew
RUN ./gradlew dependencies --no-daemon

# Copiar código e build
COPY src src
RUN ./gradlew clean bootJar -x test --no-daemon

# Runtime stage
FROM eclipse-temurin:17-jre-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]