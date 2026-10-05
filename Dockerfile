# Stage 1: Construção (Build)
FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /app
COPY . .
# Limitamos a memória do Gradle para tentar não estourar o limite de build gratuito do Render
ENV GRADLE_OPTS="-Xmx1g"
RUN ./gradlew :server:shadowJar --no-daemon

# Stage 2: Execução Leve (Runtime)
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copia apenas o arquivo .jar final (sem o WebUI e as outras coisas que removemos)
COPY --from=build /app/server/build/libs/*-all.jar /app/suwayomi-lite.jar

EXPOSE 4567

# Cria a pasta de dados do Tachidesk para o Render montar o disco persistente
RUN mkdir -p /home/suwayomi/.local/share/Tachidesk

# Inicia o servidor
CMD ["java", "-Xmx400m", "-jar", "/app/suwayomi-lite.jar"]

