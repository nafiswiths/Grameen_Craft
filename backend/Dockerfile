FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY backend/pom.xml ./backend/
COPY backend/src ./backend/src

WORKDIR /app/backend

RUN mvn -q dependency:go-offline -B
RUN mvn -q package -DskipTests -B

EXPOSE 8080

CMD ["java", "-jar", "target/backend-1.0.0.jar"]
