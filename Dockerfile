FROM maven:3.9-eclipse-temurin-25

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn -q -DskipTests package

ENTRYPOINT ["java", "-cp", "target/classes", "app.Main"]