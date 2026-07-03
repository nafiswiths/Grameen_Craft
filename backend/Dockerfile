FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /build

# Copy only the files needed for building to leverage Docker cache
COPY backend/pom.xml ./backend/pom.xml
COPY backend/src ./backend/src

WORKDIR /build/backend

# Package the Spring Boot app (skip tests to speed up CI builds)
RUN mvn -q package -DskipTests -B

FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Copy the built jar from the builder stage. Use wildcard in case the final
# artifact name includes a classifier or timestamp.
COPY --from=builder /build/backend/target/*.jar /app/app.jar

EXPOSE 8080

ENV JAVA_OPTS=""

CMD ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
