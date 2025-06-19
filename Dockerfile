FROM openjdk:8-jdk-alpine AS builder

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

#

FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]